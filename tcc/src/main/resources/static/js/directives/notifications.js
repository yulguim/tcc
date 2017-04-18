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

      //functions
      scope.readAll = readAll;

      function readAll() {
        var ids = [];
        angular.forEach(scope.notifications, function(obj) {
          obj.read = true;
          ids.push(obj.id);
        });

        var view = {ids: ids};
        notificationsService.read(view);
      };

      //Notificacoes
      var getNotifications = function() {
          notificationsService.list().success(function(notifications) {
              scope.notifications = notifications;
              scope.notificationsCount = 0;
              angular.forEach(scope.notifications, function(obj) {
                  if (!obj.read) {
                      scope.notificationsCount = scope.notificationsCount++;
                  }
              });
          });
      };

      getNotifications(); //Pega no load da pagina
      $interval(getNotifications, 5000); //Refresh a cada 5 segundos
    }
  };
}]);