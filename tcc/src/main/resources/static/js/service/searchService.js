app.factory('searchService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/search"
	
	var _search = function(view) {
		return ajaxService.post(_pageUrl, view);
	}
 	
	return {
		search : _search
	}
	
}]);