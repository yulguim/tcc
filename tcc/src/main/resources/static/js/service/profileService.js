app.factory('profileService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/profile"
	
	var _initialData = function() {
		return ajaxService.get(_pageUrl+"/initial-data");
	}

    var _load = function(key) {
        return ajaxService.get(_pageUrl+"/" + key);
    }
 	
	return {
		initialData : _initialData,
		load : _load
	}
	
}]);