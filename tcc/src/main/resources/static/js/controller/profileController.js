app.controller("profileCtrl", ['$routeParams', "profileService", "contatosService", 'mensagensService', function ($routeParams, profileService, contatosService, mensagensService) {
	var vm = this;

	vm.view = {};
    vm.mensagem = {};
	//functions
	vm.requestFriendship = requestFriendship;
	vm.removeFriendship = removeFriendship;
	vm.acceptFriendship = acceptFriendship;
	vm.denyFriendship = denyFriendship;
	vm.cancelFriendshipRequest = cancelFriendshipRequest;
	vm.sendMensagem = sendMensagem;

	function requestFriendship() {
        var key = $routeParams.key;
        contatosService.request(key).success(function() {
        	vm.view.isRequestedByMe = true;
		});
	}

    function removeFriendship() {
        var key = $routeParams.key;
        contatosService.remove(key).success(function(view) {
            console.log(view);
        });
    }

    function acceptFriendship() {
        var key = $routeParams.key;
        contatosService.accept(key).success(function() {
            vm.view.isFriend = true;
        });
    }

    function denyFriendship() {
        var key = $routeParams.key;
        contatosService.ignore(key).success(function() {
            vm.view.isRequestedByUser = false;
        });
    }

    function cancelFriendshipRequest() {
        var key = $routeParams.key;
        contatosService.cancelRequest(key).success(function() {
            vm.view.isRequestedByMe = false;
        });
    }

    function sendMensagem() {
	    vm.mensagem.userKey = $routeParams.key;
        mensagensService.saveMensagem(vm.mensagem).success(function() {
	        vm.mensagem = {};
        });
    }

	var iniciarTela = function() {
		var key = $routeParams.key;
		profileService.load(key).success(function(view) {
			vm.view = view;
		})
	};

	iniciarTela();
}]);