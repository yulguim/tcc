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

app.factory('feedbackService', ['$timeout', function($timeout) {
	var _ajaxRequestOn = false;
	var _messages = [];
    
	var _showNotify = function() {
		_ajaxRequestOn = true;
    };
    var _hideNotify = function() {
    	_ajaxRequestOn = false;
    };
    var  _clearMessages = function() {
    	_messages = [];
    };
    var _getMessages = function() {
    	return _messages;
    };
    var _showMessages = function(messages) {
    	_clearMessages();
    	angular.forEach(messages, function(message, key) {
    		if(message.category == 'WARN') { message.category = 'warning'; }
    		if(message.category == 'INFO') { message.category = 'info'; }
    		if(message.category == 'ERROR') { message.category = 'danger'; }
    		if(message.category == 'SUCCESS') { message.category = 'success'; }
    		
    		angular.forEach(applicationMessagesi18n, function(i18n, key) {
    			if(i18n.key === message.message) {
    				message.message = i18n.value;
    				if(message.parameters) {
    					angular.forEach(message.parameters, function(param, key) {
    						message.message = message.message.replace('{'+key+'}', param.value);
    					})
    				}
    			}
    		});
    		_messages.push(message);
    		$timeout(_clearMessages, 5000);
    	});
    };
    
    return {
    	showNotify : _showNotify,
    	hideNotify : _hideNotify,
    	clearMessages : _clearMessages,
    	showMessages : _showMessages,
    	getMessages : _getMessages
    }
}]);

app.factory('httpInterceptorService', ['$q', 'feedbackService', function($q, feedbackService) {
    return{
        'request' : function (config) {
        	feedbackService.showNotify();
            return config;
        },
        'requestError' : function (rejection) {
        	feedbackService.hideNotify();
            return $q.reject();
        },
        'response' : function (response) {
        	feedbackService.hideNotify();
            if(response.data.messages) {
            	feedbackService.showMessages(response.data.messages);
            }            
            return response;
        },
        'responseError' : function(rejection) {
        	feedbackService.hideNotify();
            if(rejection.status === 401) {
            	window.location = '/login/';
            } else if(rejection.status === 402) {
            	//console.log('Pagamento Pendente - 402');
            } else if(rejection.status === 403) {
            	//console.log('Acesso Negado - 403');
            	//TODO criar mensagem de não tem permissão
            } else if(rejection.status === 409) {
            	feedbackService.showMessages(rejection.data.errors);
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
    	clearMessages(data);
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

function clearMessages(obj) {
	for (var prop in obj) {
		if(typeof obj[prop] === 'object') {
			delete obj['messages'];
			clearMessages(obj[prop]);
		}
	}
}

//Controllers
app.controller("feedbackCtrl", ["$scope", "feedbackService", function ($scope, feedbackService) {
	$scope.severity = "warning";
    $scope.tituloFeedback = "Resposta";
    $scope.messagesFeedback = [];
    
    $scope.$watch(function () { return feedbackService.getMessages(); }, function (newValue, oldValue) {
    	if(newValue) {
    		$scope.messagesFeedback = newValue;
    		if($scope.messagesFeedback.length > 0) {
    			$scope.severity = $scope.messagesFeedback[0].category;
    			
    			if($scope.severity === 'warning') { $scope.tituloFeedback = "Aviso"; }
        		if($scope.severity === 'info') { $scope.tituloFeedback = "Informação"; }
        		if($scope.severity === 'danger') { $scope.tituloFeedback = "Erro"; }
        		if($scope.severity === 'success') { $scope.tituloFeedback = "Sucesso"; }
    		} else {
    			$scope.tituloFeedback = "";
    		}
    	}
    });

    var _setTitle = function(titulo) {
        $scope.tituloFeedback = titulo;
    };
    var _addMessage = function(message) {
        $scope.messagesFeedback.push(message);
    };
    var _clearMessages = function() {
        $scope.tituloFeedback = "";
        $scope.messagesFeedback = [];
    };

    return {
        setTitle : _setTitle,
        addMessage : _addMessage,
        clearMessages : _clearMessages
    };
}]);