var app = angular.module("App", ["ngSanitize", "ngRoute", 'ui.bootstrap', 'ui.select', 'ngTagsInput'])

app.run(function($rootScope, $http, $timeout, $route, $location) {
    toastr.options.closeButton = true;
    toastr.options.positionClass = "toast-bottom-right"

});

app.controller("appCtrl", ["$scope", function ($scope) {

}]);
