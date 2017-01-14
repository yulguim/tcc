app.factory('contatosService', ['ajaxService', 'mockerService', function(ajaxService, mockerService) {
	var _pageUrl = "/contatos";
	
	var _initialData = function() {
		//return ajaxService.get(_pageUrl+"/initial-data");
		return mockerService.get('contatos.json');
	};
 	
	return {
		initialData : _initialData
	}
	
}]);