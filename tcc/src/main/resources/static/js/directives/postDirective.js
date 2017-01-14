app.directive('postEditor', function() {
  return {
    restrict: 'E',
    scope: {
    	post: '='
    },
    templateUrl: '/templates/post-editor.html',
    link: function (scope) {
    	console.log(scope.post);
    }
  };
});