var app = angular.module("App", ["ngSanitize", "ngRoute", 'ui.bootstrap', 'ui.select', 'ngTagsInput', 'pascalprecht.translate', 'ngFileUpload', 'ngImgCrop'])

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

app.controller("appCtrl", ["$scope", '$translate', 'usuarioLogadoService', function ($scope, $translate, usuarioLogadoService) {
    $scope.usuarioLogado = {};

    usuarioLogadoService.loadUsuario().success(function(data) {
        console.log("Usuario Logado: " + JSON.stringify(data));
        usuarioLogadoService.setUsuario(data);
        $scope.usuarioLogado = usuarioLogadoService.getUsuario();
    });

    $scope.changeLanguage = function(lang) {
        $translate.use(lang);
    }

    $scope.doSearch = function(search) {
        if (search === undefined || search.length === 0) {
            return;
        }
        console.log(search);
        window.location = '/#/search/' + encodeURIComponent(search);
    }

}]);
