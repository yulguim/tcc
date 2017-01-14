app.config(function($routeProvider) {
	$routeProvider.when('/home', {templateUrl: '/home.html', controller: 'homeCtrl'});
	$routeProvider.when('/contatos', {templateUrl: '/contatos.html', controller: 'contatosCtrl'});
	$routeProvider.when('/mensagens', {templateUrl: '/mensagens.html', controller: 'mensagensCtrl'});
	
	//other
	$routeProvider.otherwise({redirectTo: '/home'});
});