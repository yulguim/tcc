app.factory('postService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/post"
	
	var _save = function(view) {
		return ajaxService.post(_pageUrl, view);
	}

    var _saveComment = function(view) {
        return ajaxService.post(_pageUrl + "/save-comment", view);
    }

    var _removeComment = function(view) {
        return ajaxService.post(_pageUrl + "/delete-comment", view);
    }
 	
	return {
		save : _save,
		saveComment : _saveComment,
		removeComment : _removeComment
	}
	
}]);