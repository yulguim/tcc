app.controller("contatosCtrl", ["contatosService", function (contatosService) {
	var vm = this;

	vm.contatos = [];
	
	var iniciarTela = function() {
		contatosService.list().success(function(view) {
			vm.contatos = view;
			vm.contatos.push(angular.copy(vm.contatos[0]));
			vm.contatos.push(angular.copy(vm.contatos[0]));
			vm.contatos.push(angular.copy(vm.contatos[0]));
		});
	};
	
    iniciarTela(); 
}]);