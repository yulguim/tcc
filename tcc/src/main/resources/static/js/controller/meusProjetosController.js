app.controller("meusProjetosCtrl", ["meusProjetosService", function (meusProjetosService) {
	var vm = this;

	vm.projects = [];

	var iniciarTela = function() {
        meusProjetosService.list().success(function(view) {
			vm.projects = view;
		})
	};

	iniciarTela();
}]);