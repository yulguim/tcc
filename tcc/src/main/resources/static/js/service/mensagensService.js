app.factory('mensagensService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/chat";
	
	var _initialData = function() {
		//return ajaxService.get(_pageUrl+"/initial-data");
		//return mockerService.get('contatos.json');
	};

	var _list = function() {
		return ajaxService.get(_pageUrl);
	};

    var _load = function(id) {
        return ajaxService.get(_pageUrl + "/" + id);
    };

    var _saveMensagem = function(view) {
        return ajaxService.post(_pageUrl, view);
    };
 	
	return {
		initialData : _initialData,
		list : _list,
		load : _load,
		saveMensagem : _saveMensagem
	}
	
}]);