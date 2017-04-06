app.factory('myAccountService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/account";

    var _load = function(id) {
        return ajaxService.get(_pageUrl + "/" + id);
    };

    var _update = function(view) {
        return ajaxService.post(_pageUrl, view);
    };
 	
	return {
		load : _load,
		update : _update
	}
	
}]);