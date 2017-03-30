app.controller("profileCtrl", ["$scope", '$routeParams', "profileService", "contatosService", function ($scope, $routeParams, profileService, contatosService) {
	var vm = this;

	vm.view = {};
	//functions
	vm.requestFriendship = requestFriendship;

	function requestFriendship() {
        var key = $routeParams.key;
        console.log(key);
        contatosService.request(key).success(function(view) {
        	console.log(view);
		});
	}

	var iniciarTela = function() {
		var key = $routeParams.key;
		console.log(key);
		profileService.load(key).success(function(view) {
			vm.view = view;
		})
	};
     
	iniciarTela();
}]);