var app = angular.module("App", ["ngSanitize", "ngRoute", "ui.bootstrap", 'ui.select'])

.config(function(uiSelectConfig) {
	  uiSelectConfig.theme = 'bootstrap';
	  uiSelectConfig.appendToBody = true;
});

app.run(function($rootScope, $http, $timeout, $route, $location) {
	
});

app.controller("appCtrl", ["$scope", "sessionService", "ajaxService", function ($scope, sessionService, ajaxService) {

	
    
}]);
