app.controller("meusProjetosCtrl", ["meusProjetosService", function (meusProjetosService) {
	var vm = this;

	vm.myProjects = [];
	vm.projects = [];

	var iniciarTela = function() {
        meusProjetosService.list().success(function(data) {
			vm.myProjects = data.meusProjetos;
			vm.projects = data.projetos;
		})
	};

	iniciarTela();
}]);