app.config(function($routeProvider) {
	$routeProvider.when('/home', {templateUrl: '/home.html', controller: 'homeCtrl'});
	
	//other
	$routeProvider.otherwise({redirectTo: '/home'});
});