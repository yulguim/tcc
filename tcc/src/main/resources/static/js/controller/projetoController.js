app.controller("projetoCtrl", ['$routeParams', "projetoService", function ($routeParams, projetoService) {
	var vm = this;

	vm.view = {};

	var iniciarTela = function() {
        var key = $routeParams.key;
        projetoService.load(key).success(function(view) {
			vm.view = view;
		});
	};
	
    iniciarTela(); 
}]);