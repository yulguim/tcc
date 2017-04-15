app.directive('notifications', ['$interval', 'notificationsService', function($interval, notificationsService) {
  return {
    restrict: 'E',
    scope: {

    },
    replace : true,
    templateUrl: '/templates/notifications.html',
    link: function (scope) {
      scope.notifications = [];
      scope.notificationsCount = 0;

      //Notificacoes
      var getNotifications = function() {
          notificationsService.list().success(function(notifications) {
              scope.notifications = notifications;
              scope.notificationsCount = notifications.length;
          });
      };

      getNotifications(); //Pega no load da pagina
      $interval(getNotifications, 5000); //Refresh a cada 5 segundos
    }
  };
}]);