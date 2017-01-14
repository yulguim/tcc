app.factory("mockerService", ["$http", function($http) {

    var _get = function(mockFile) {
    	return $http.get('/js/mock_data/' + mockFile);
    };
   
    return {
        get : _get
    }
}]);