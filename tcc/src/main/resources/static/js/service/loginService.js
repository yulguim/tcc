app.factory('loginService', ['ajaxService', function(ajaxService) {
	var _pageUrl = ""
	
	var _doLogin = function(view) {
		return ajaxService.post(_pageUrl+"/login", view);
	}
 	
	return {
		doLogin : _doLogin
	}
	
}]);