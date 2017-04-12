app.controller("myAccountCtrl", ['usuarioLogadoService', "myAccountService", function (usuarioLogadoService, myAccountService) {
	var vm = this;

	vm.view = {};

	//functions
	vm.salvarConta = salvarConta;

	function salvarConta() {
		myAccountService.update(vm.view).success(function() {
			delete vm.view.password;
		});
	}

	var iniciarTela = function() {
		myAccountService.load().success(function(view) {
			vm.view = view;
		});
	};
	
    iniciarTela(); 
}]);