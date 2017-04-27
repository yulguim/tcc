app.factory('projetoService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/projeto";

	var _load = function(key) {
		return ajaxService.get(_pageUrl + "/" + key);
	}
	
	var _save = function(view) {
		return ajaxService.post(_pageUrl, view);
	}

    var _request = function(view) {
        return ajaxService.post(_pageUrl + "/request", view);
    }

    var _accept = function(view) {
        return ajaxService.post(_pageUrl + "/accept", view);
    }

    var _leave = function(view) {
        return ajaxService.post(_pageUrl + "/delete-participante", view);
    }


 	
	return {
		load : _load,
		save : _save,
		request : _request,
		accept : _accept,
		leave : _leave
	}
	
}]);