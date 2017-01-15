app.controller("profileCtrl", ["$scope", '$routeParams', "profileService", function ($scope, $routeParams, profileService) {
	
	var iniciarTela = function() {
		var key = $routeParams.key;
		console.log(key);
	};
     
	iniciarTela();
}]);