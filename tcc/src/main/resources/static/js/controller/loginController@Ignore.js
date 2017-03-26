app.controller("loginCtrl", ['$location', 'loginService', function ($location, loginService) {
	var vm = this;

	vm.view = {email: 'yulguim@gmail.com', password: '123mudar'};

	//functions
	vm.doLogin = doLogin;
	vm.googleLogin = googleLogin;
	vm.facebookLogin = facebookLogin;
	vm.linkedinLogin = linkedinLogin;
	vm.cadastrar = cadastrar;

	function doLogin(view) {
		loginService.doLogin(view).success(function(view) {
			//Redirect para home
            window.location = '/';
		});
	};
	
	function googleLogin() {
		console.log("Google!");
	};
	
	function facebookLogin() {
		console.log("Facebook!");
	};
	
	function linkedinLogin() {
		console.log("LinkedIn!");
	};

	function cadastrar(view) {
		loginService.cadastrar(view).success(function(view) {
			//Redirect para home
			window.location = '/#/profile-edit/';
		});
	}
     
}]);