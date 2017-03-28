app.directive('timeline', ['timelineService', 'postService', function(timelineService, postService) {
  return {
    restrict: 'E',
    scope: {
    	//posts: '='
    },
    templateUrl: '/templates/timeline.html',
    link: function (scope) {
      scope.posts = [];

      //functions
      scope.comment = comment;

      function comment(post) {
        var view = {};
        view.postKey = post.key;
        view.comentario = post.newComment;
        postService.saveComment(view).success(function(data) {
            post.commentList.push(data);
            post.newComment = null;
        });
      }

      function iniciar() {
        timelineService.initialData().success(function(data) {
          scope.posts = data.posts;
        });
      }

      iniciar();
    }
  };
}]);