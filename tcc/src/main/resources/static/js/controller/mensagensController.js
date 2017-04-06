app.controller("mensagensCtrl", ['usuarioLogadoService', "mensagensService", function (usuarioLogadoService, mensagensService) {
	var vm = this;

	vm.usuarioLogado = {};
	vm.chats = [];
	vm.chatLoaded = {};

	//functions
	vm.loadChat = loadChat;
	vm.saveMensagem = saveMensagem;
	vm.removeMensagem = removeMensagem;

	function loadChat(chat) {
		mensagensService.load(chat.id).success(function(view) {
			console.log(view);
			vm.chatLoaded = view;
		});
	};

	function saveMensagem(mensagem) {
		mensagem.chatId = vm.chatLoaded.id;
		mensagensService.saveMensagem(mensagem).success(function(view) {
            mensagem = undefined;
            vm.chatLoaded.mensagens.push(view);
        });
	};

    function removeMensagem(mensagem) {
		var index = vm.chatLoaded.mensagens.indexOf(mensagem);
        mensagem.chatId = vm.chatLoaded.id;
        mensagensService.removeMensagem(mensagem).success(function() {
            vm.chatLoaded.mensagens.splice(index, 1);
        });
    };

	var iniciarTela = function() {
		vm.usuarioLogado = usuarioLogadoService.getUsuario();
		mensagensService.list().success(function(data) {
			console.log(data);
			vm.chats = data;
		});
	};
	
    iniciarTela(); 
}]);