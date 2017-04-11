app.factory('notificationsService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/notifications";

    var _list = function() {
        return ajaxService.get(_pageUrl);
    }
 	
	return {
		list : _list
	}
	
}]);