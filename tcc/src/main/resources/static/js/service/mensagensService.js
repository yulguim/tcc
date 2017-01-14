app.factory('mensagensService', ['ajaxService', 'mockerService', function(ajaxService, mockerService) {
	var _pageUrl = "/mensagens";
	
	var _initialData = function() {
		//return ajaxService.get(_pageUrl+"/initial-data");
		//return mockerService.get('contatos.json');
	};
 	
	return {
		initialData : _initialData
	}
	
}]);