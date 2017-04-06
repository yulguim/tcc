app.controller("myAccountCtrl", ['usuarioLogadoService', "myAccountService", function (usuarioLogadoService, myAccountService) {
	var vm = this;

	vm.view = {};

	var iniciarTela = function() {
		vm.view.hello = 'hello';
	};
	
    iniciarTela(); 
}]);