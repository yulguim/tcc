app.controller("homeCtrl", ["homeService", function (homeService) {
	var vm = this;

	function iniciarTela() {
		homeService.initialData().success(function(data) {
			vm.projects = data;
        });
	}

	iniciarTela();
}]);