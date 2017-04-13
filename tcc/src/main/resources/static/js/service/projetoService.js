app.factory('projetoService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/projeto";

	var _load = function(view) {
		return ajaxService.get(_pageUrl + "/" + view.key);
	}
	
	var _save = function(view) {
		return ajaxService.post(_pageUrl, view);
	}
 	
	return {
		load : _load,
		save : _save
	}
	
}]);