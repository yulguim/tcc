app.config(function($routeProvider) {
	$routeProvider.when('/home', {templateUrl: '/home.html', controller: 'homeCtrl as vm'});
	$routeProvider.when('/contatos', {templateUrl: '/contatos.html', controller: 'contatosCtrl as vm'});
	$routeProvider.when('/mensagens', {templateUrl: '/mensagens.html', controller: 'mensagensCtrl as vm'});
    $routeProvider.when('/my-account/', {templateUrl: '/my-account.html', controller: 'myAccountCtrl as vm'});
    $routeProvider.when('/profile-edit/', {templateUrl: '/profile-edit.html', controller: 'profileEditCtrl as vm'});
    $routeProvider.when('/profile/:key', {templateUrl: '/profile.html', controller: 'profileCtrl as vm'});
    $routeProvider.when('/search/:key', {templateUrl: '/search.html', controller: 'searchCtrl as vm'});
    $routeProvider.when('/my-projects', {templateUrl: '/my-projects.html', controller: 'meusProjetosCtrl as vm'});
    $routeProvider.when('/projeto/:key', {templateUrl: '/projeto.html', controller: 'projetoCtrl as vm'});

	//other
	$routeProvider.otherwise({redirectTo: '/home'});
});