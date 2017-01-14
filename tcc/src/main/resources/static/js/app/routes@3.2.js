app.config(function($routeProvider) {
	$routeProvider.when('/home', {templateUrl: '/home.html', controller: 'homeCtrl'});
	$routeProvider.when('/contatos', {templateUrl: '/contatos.html', controller: 'contatosCtrl'});
	$routeProvider.when('/mensagens', {templateUrl: '/mensagens.html', controller: 'mensagensCtrl'});
	$routeProvider.when('/profile/:key', {templateUrl: '/profile.html', controller: 'profileCtrl'});
	
	//other
	$routeProvider.otherwise({redirectTo: '/home'});
});