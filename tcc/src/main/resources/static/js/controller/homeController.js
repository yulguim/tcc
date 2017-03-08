app.controller("homeCtrl", ["homeService", function (homeService) {
	var vm = this;

	vm.mensagem = "eitaaa nóis";

	function iniciarTela() {
		console.log(vm.mensagem);
	}

	iniciarTela();
}]);