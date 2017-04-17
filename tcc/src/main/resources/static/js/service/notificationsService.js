app.factory('notificationsService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/notifications";

    var _list = function() {
        return ajaxService.get(_pageUrl);
    }

    var _read = function(view) {
    	return ajaxService.post(_pageUrl, view);
	}
 	
	return {
		list : _list,
		read : _read
	}
	
}]);