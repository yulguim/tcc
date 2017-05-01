app.factory('loginService', ['ajaxService', function(ajaxService) {
	var _pageUrl = "";
	
	var _doLogin = function(view) {
		return ajaxService.post(_pageUrl+"/login", view);
	}

    var _facebookLogin = function(view) {
        return ajaxService.post(_pageUrl+"/facebook-login", view);
    }

    var _linkedinLogin = function(view) {
        return ajaxService.post(_pageUrl+"/linkedin-login", view);
    }

	var _cadastrar = function(view) {
		return ajaxService.post(_pageUrl+"/signin", view);
	}
 	
	return {
		doLogin: _doLogin,
		facebookLogin: _facebookLogin,
        linkedinLogin : _linkedinLogin,
		cadastrar: _cadastrar
	}
	
}]);