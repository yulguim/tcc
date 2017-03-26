var app = angular.module("App", ["ngSanitize", "ngRoute", 'ui.bootstrap', 'ui.select', 'ngTagsInput', 'pascalprecht.translate'])

.config(function($translateProvider) {
    $translateProvider.useSanitizeValueStrategy(null);
    $translateProvider.useStaticFilesLoader({
        prefix: '/js/i18n/locale-',
        suffix: '.json'
    });
    $translateProvider.preferredLanguage('pt');
});

app.run(function($rootScope, $http, $timeout, $route, $location) {
    toastr.options.closeButton = true;
    toastr.options.positionClass = "toast-bottom-right"

});

app.controller("appCtrl", ["$scope", '$translate', function ($scope, $translate) {

    $scope.changeLanguage = function(lang) {
        $translate.use(lang);
    }

}]);
