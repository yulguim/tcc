app.controller("loginCtrl", ["$scope", "loginService", function ($scope, loginService) {
	
	$scope.view = {};
	
	$scope.login = function() {
		var view = $scope.view;
		loginService.doLogin(view).success(function() {
			console.log("Deu bom!");
		});
	};
	
	$scope.googleLogin = function() {
		console.log("Google!");
	};
	
	$scope.facebookLogin = function() {
		console.log("Facebook!");
	};
	
	$scope.linkedinLogin = function() {
		console.log("LinkedIn!");
	};
     
}]);