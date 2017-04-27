app.controller("projetoCtrl", ['$routeParams', "projetoService", "postService", function ($routeParams, projetoService, postService) {
	var vm = this;
	vm.tab = 'timeline';

	vm.view = {};
	vm.posts = [];
	vm.post = {};
    vm.participantes = null;
    vm.chat = null;

	//functions
	vm.request = request;
	vm.accept = accept;
	vm.cancelRequest = cancelRequest;
	vm.leave = leave;

    vm.sendMensagemToOwner = sendMensagemToOwner;
	vm.sendMensagem = sendMensagem;

	vm.savePost = savePost;
	vm.deletePost = deletePost;
    vm.saveComentarioPost = saveComentarioPost;
    vm.deleteComentarioPost = deleteComentarioPost;

	function request() {
		projetoService.request(createView()).success(function() {
			vm.view.isRequested = true;
		});
	}

    function accept(user) {
		projetoService.accept(createView(user)).success(function() {
			user.requested = false;
			user.participante = true;
		});
    }
	
	function cancelRequest() {
		projetoService.leave(createView()).success(function () {
			vm.view.isRequested = false;
        });
    }
    
    function leave(user) {
        projetoService.leave(createView(user)).success(function () {
			if (user !== undefined) {
                vm.view.isRequested = false;
			} else {
                var index = vm.participantes.indexOf(user);
                vm.participantes.splice(index, 1);
			}
        });
    }

    function sendMensagemToOwner(mensagem) {
        mensagem.projetoKey = $routeParams.key;
        projetoService.saveMensagemToOwner(mensagem).success(function() {
            mensagem = undefined;
        });
	}

    function sendMensagem(mensagem) {
        mensagem.projetoKey = $routeParams.key;
        projetoService.saveMensagem(mensagem).success(function(view) {
            mensagem = undefined;
            vm.chat.mensagens.push(view);
        });
	}

	function createView(user) {
        var key = $routeParams.key;
        var view = {};
        view.projetoKey = key;
        if (user !== undefined) {
			view.key = user.key;
		}

        return view;
	}

	function savePost() {
		vm.post.projetoKey = $routeParams.key;
        postService.save(vm.post).success(function(view) {
        	vm.post = {};
        	vm.posts.push(view);
		});
	}

	function deletePost(post) {
		var index = vm.posts.indexOf(post);
        postService.remove().success(function() {
            vm.posts.splice(index, 1);
        });
	}

	function saveComentarioPost(post) {
		var comment = {};
		comment.postKey = post.key;
		comment.comentario = post.comment;
		postService.saveComment(comment).success(function(view) {
			post.commentList.push(view);
			delete comment;
			delete post.comment;
		});
	}

	function deleteComentarioPost(post, comment) {
		var index = post.commentList.indexOf(comment);
        comment.postId = post.id;
        postService.removeComment(comment).success(function() {
        	post.commentList.splice(index, 1);
        	delete comment;
		});
	}

	var iniciarTela = function() {
        var key = $routeParams.key;
        projetoService.load(key).success(function(data) {
			vm.view = data.projeto;
			vm.posts = data.posts;
			vm.participantes = data.participantes;
			vm.chat = data.chat;
		});
	};
	
    iniciarTela(); 
}]);