app.factory('homeService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/home"
	
	var _initialData = function() {
		return ajaxService.get(_pageUrl+"/initial-data");
	}
 	
	return {
		initialData : _initialData
	}
	
}]);