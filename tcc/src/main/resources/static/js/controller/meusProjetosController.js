app.controller("meusProjetosCtrl", ["meusProjetosService", function (meusProjetosService) {
	var vm = this;

	vm.view = {};

	var iniciarTela = function() {
        meusProjetosService.list().success(function(view) {
			vm.view = view;
		})
	};

	iniciarTela();
}]);