app.factory('profileEditService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/profile-edit"
	
	var _initialData = function() {
		return ajaxService.get(_pageUrl+"/initial-data");
	}

    var _save = function(view) {
        return ajaxService.post(_pageUrl, view);
    }
 	
	return {
		initialData : _initialData,
		save : _save
	}
	
}]);