app.factory('contatosService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/contato";

    var _request = function(key) {
        return ajaxService.get(_pageUrl+"/request/" + key);
    };

    var _accept = function(key) {
        return ajaxService.get(_pageUrl+"/accept/" + key);
    };

    var _ignore = function(key) {
        return ajaxService.get(_pageUrl+"/ignore/" + key);
    };

    var _cancelRequest = function(key) {
        return ajaxService.get(_pageUrl+"/cancel/" + key);
    };

    var _remove = function(key) {
        return ajaxService.remove(_pageUrl+"/" + key);
    };
 	
	return {
		request : _request,
		accept : _accept,
		ignore : _ignore,
        cancelRequest : _cancelRequest,
        remove : _remove
	}
	
}]);