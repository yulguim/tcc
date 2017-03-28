app.factory('timelineService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/timeline"
	
	var _initialData = function() {
		return ajaxService.get(_pageUrl + "/initial-data");
	}
 	
	return {
		initialData : _initialData
	}
	
}]);