app.factory('meusProjetosService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/projeto";
	
	var _list = function() {
		return ajaxService.get(_pageUrl);
	}
 	
	return {
		list : _list
	}
	
}]);