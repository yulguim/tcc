app.factory('profileEditService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/profile-edit"
	
	var _initialData = function() {
		return ajaxService.get(_pageUrl+"/initial-data");
	}
 	
	return {
		initialData : _initialData
	}
	
}]);