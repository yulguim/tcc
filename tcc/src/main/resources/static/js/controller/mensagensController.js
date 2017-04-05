app.controller("mensagensCtrl", ['usuarioLogadoService', "mensagensService", function (usuarioLogadoService, mensagensService) {
	var vm = this;

	vm.usuarioLogado = {};
	vm.chats = [];
	vm.chatLoaded = {};

	//functions
	vm.loadChat = loadChat;

	function loadChat(chat) {
		mensagensService.load(chat.id).success(function(view) {
			console.log(view);
			vm.chatLoaded = view;
		});
	}

	var iniciarTela = function() {
		vm.usuarioLogado = usuarioLogadoService.getUsuario();
		mensagensService.list().success(function(data) {
			console.log(data);
			vm.chats = data;
		});
	};
	
    iniciarTela(); 
}]);