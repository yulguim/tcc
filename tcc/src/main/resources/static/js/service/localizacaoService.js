app.factory('localizacaoService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "/localizacao";

    var _getCidade = function(id) {
        return ajaxService.get(_pageUrl+"/cidade/" + id);
    }

    var _getEstado = function(id) {
        return ajaxService.get(_pageUrl+"/estado/" + id);
    }

    var _searchCidade = function(str) {
        return ajaxService.post(_pageUrl+"/cidade", str);
    }

    var _searchEstado = function(str) {
        return ajaxService.post(_pageUrl+"/estado", str);
    }
 	
	return {
		getCidade : _getCidade,
		getEstado : _getEstado,
		searchCidade : _searchCidade,
		searchEstado : _searchEstado
	}
	
}]);