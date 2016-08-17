app.controller("loginCtrl", ["$scope", "sessionService", "ajaxService", function ($scope, sessionService, ajaxService) {

	var iniciarTela = function() {
		console.log("Entrou!");
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
     
	iniciarTela();
}]);