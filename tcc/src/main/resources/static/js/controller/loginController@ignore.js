app.controller("loginCtrl", ["$scope", function ($scope) {
	
	$scope.view = {login: "yulle@tcc.io", password: "yulle"};
	
	$scope.login = function() {
		console.log("entrou");
		var view = $scope.view;
//		loginService.doLogin(view).success(function(view) {
//			console.log("Deu bom!");
//			console.log(view);
//		});
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