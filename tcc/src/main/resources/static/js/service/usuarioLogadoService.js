app.factory('usuarioLogadoService', ['ajaxService', function(ajaxService) {
	var url = "/account";
	var usuario = null;

    var _loadUsuario = function() {
        return ajaxService.get(url);
    };

	var _setUsuario = function(data) {
		usuario = data;
	};

    var _getUsuario = function() {
        return usuario;
    };
 	
	return {
		loadUsuario : _loadUsuario,
		setUsuario : _setUsuario,
		getUsuario : _getUsuario
	}
	
}]);