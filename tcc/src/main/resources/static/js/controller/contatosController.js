app.controller("contatosCtrl", ["$scope", "contatosService", function ($scope, contatosService) {
	
	$scope.contatos = [];
	
	var iniciarTela = function() {
		contatosService.initialData().success(function(view) {
			$scope.contatos = view;
		});
	};
	
    iniciarTela(); 
}]);