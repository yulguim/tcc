app.factory('loginService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "";
	
	var _doLogin = function(view) {
		return ajaxService.post(_pageUrl+"/login", view);
	}

	var _cadastrar = function(view) {
		return ajaxService.post(_pageUrl+"/signin", view);
	}
 	
	return {
		doLogin: _doLogin,
		cadastrar: _cadastrar
	}
	
}]);