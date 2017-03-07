app.controller("loginCtrl", ['$location', 'loginService', function ($location, loginService) {
	var vm = this;

	vm.view = {email: "yulle@tcc.io", password: "yulle"};

	//functions
	vm.doLogin = doLogin;
	vm.googleLogin = googleLogin;
	vm.facebookLogin = facebookLogin;
	vm.linkedinLogin = linkedinLogin;
	vm.cadastrar = cadastrar;

	function doLogin(view) {
		console.log(view);
//		loginService.doLogin(view).success(function(view) {
//			console.log("Deu bom!");
//			console.log(view);
//		});
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
		console.log(view);
		loginService.cadastrar(view).success(function(view) {
			console.log("Deu bom!");
			console.log(view);
		});
	}
     
}]);