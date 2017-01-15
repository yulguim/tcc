app.directive('timeline', function() {
  return {
    restrict: 'E',
    scope: {
    	posts: '='
    },
    templateUrl: '/templates/timeline.html',
    link: function (scope) {

    }
  };
});