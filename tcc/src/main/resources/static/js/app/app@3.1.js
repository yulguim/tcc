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

app.controller("appCtrl", ["$scope", '$translate', '$interval', 'usuarioLogadoService', 'notificationsService', function ($scope, $translate, $interval, usuarioLogadoService, notificationsService) {
    $scope.usuarioLogado = {};
    $scope.notifications = [];
    $scope.notificationsCount = 0;

    usuarioLogadoService.loadUsuario().success(function(data) {
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
        window.location = '/#/search/' + encodeURIComponent(search);
    }

    //Notificacoes
    var getNotifications = function() {
        notificationsService.list().success(function(notifications) {
           $scope.notifications = notifications;
           $scope.notificationsCount = notifications.length;
        });
    };

    getNotifications(); //Pega no load da pagina
    $interval(getNotifications, 5000); //Refresh a cada 5 segundos
}]);
