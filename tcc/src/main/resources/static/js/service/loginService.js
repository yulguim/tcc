app.factory('loginService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "";
	
	var _doLogin = function(view) {
		return ajaxService.post(_pageUrl+"/login", view);
	}

    var _facebookLogin = function(view) {
        return ajaxService.post(_pageUrl+"/facebook-login", view);
    }

	var _cadastrar = function(view) {
		return ajaxService.post(_pageUrl+"/signin", view);
	}
 	
	return {
		doLogin: _doLogin,
		facebookLogin: _facebookLogin,
		cadastrar: _cadastrar
	}
	
}]);