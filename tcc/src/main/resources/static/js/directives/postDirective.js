app.directive('postEditor', ['postService', function(postService) {
  return {
    restrict: 'E',
    scope: {
    	//post: '='
    },
    templateUrl: '/templates/post-editor.html',
    link: function (scope) {
      scope.view = {};

      //functions
      scope.post = post;

      function post() {
        postService.save(scope.view).success(function(view) {
            console.log(view);
        });
      }

    }
  };
}]);