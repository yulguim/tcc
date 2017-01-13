var app = angular.module("App", ["ngSanitize", "ngRoute"])

app.run(function($rootScope, $http, $timeout, $route, $location) {
	
});

app.controller("appCtrl", ["$scope", function ($scope) {

	console.log("aqui eh o app controller");
    
}]);
