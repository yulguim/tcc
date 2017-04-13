//Config
app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptorService');
}]);

//Services
app.factory('sessionService', function() {
    var _user = null;

    var _getUser = function() {
        return _user;
    };
    var _setUser = function(newUser) {
        _user = newUser;
    };
    
    return {
        getUser : _getUser,
        setUser : _setUser,
    }
});

app.factory('feedbackService', [function() {
    var _showMessages = function(messages) {
    	angular.forEach(messages, function(message, key) {
    		if(message.category == 'WARN') {
    		    message.category = 'warning';
                toastr.warning(message.message);
            } else if(message.category == 'INFO') {
    		    message.category = 'info';
                toastr.info(message.message);
            } else if(message.category == 'ERROR') {
    		    message.category = 'danger';
                toastr.error(message.message);
            } else if(message.category == 'SUCCESS') {
    		    message.category = 'success';
                toastr.success(message.message);
            }
    	});
    };
    
    return {
    	showMessages : _showMessages
    }
}]);

app.factory('httpInterceptorService', ['$q', 'feedbackService', function($q, feedbackService) {
    return {
        'request' : function (config) {
            return config;
        },
        'requestError' : function (rejection) {
            return $q.reject();
        },
        'response' : function (response) {
            if(response.data.messages) {
            	feedbackService.showMessages(response.data.messages);
            }            
            return response;
        },
        'responseError' : function(rejection) {
            if(rejection.status === 401) {
            	window.location = '/login/';
            } else if(rejection.status === 402) {
            	//console.log('Pagamento Pendente - 402');
            } else if(rejection.status === 403) {
            	//console.log('Acesso Negado - 403');
            } else if(rejection.status === 409) {
            	feedbackService.showMessages(rejection.data);
            }
            return $q.reject(rejection); 
        }
    }
}]);

//Usar URLS com barra antes (ex.: /login)
app.factory("ajaxService", ["$http", function($http) {
	var _baseUrl = "";

    var _get = function(url) {
        return $http.get(_baseUrl+url);
    };
    var _post = function(url, data) {
        delete data.messages;
    	return $http.post(_baseUrl+url, data);
    };
    var _remove = function(url) {
    	return $http.delete(_baseUrl+url);
    };
    
    return {
        get : _get,
        post : _post,
        remove : _remove
    }
}]);