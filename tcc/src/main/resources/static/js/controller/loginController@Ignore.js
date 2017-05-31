app.controller("loginCtrl", ['$location', 'loginService', function ($location, loginService) {
	var vm = this;

	vm.view = {};
	//vm.view = {email: 'yulguim@gmail.com', password: '123mudar'};

	//functions
	vm.doLogin = doLogin;
	vm.googleLogin = googleLogin;
	vm.facebookLogin = facebookLogin;
	vm.linkedinLogin = linkedinLogin;
	vm.cadastrar = cadastrar;

	function doLogin(view) {
		loginService.doLogin(view).success(function(view) {
			if (view.hasNoProfile) {
                window.location = '/#/profile-edit';
			} else {
                //Redirect para home
                window.location = '/#/my-projects';
			}
		});
	};
	
	function googleLogin() {
		console.log("Google!");
	};

    function facebookLogin() {
        FB.login(function (response) {
            if (response.status === 'connected') {
				var view = {facebookToken: response.authResponse.accessToken};
				loginService.facebookLogin(view).success(function() {
                    if (view.hasNoProfile) {
                        window.location = '/#/profile-edit';
                    } else {
                        //Redirect para home
                        window.location = '/#/my-projects';
                    }
				});
            }
        }, {
            scope: 'public_profile, email, user_about_me, user_location, user_website, user_work_history'
        });
    }

    function linkedinLogin() {
        IN.User.authorize(function() {
            IN.API.Profile("me")
                .fields("id", "firstName", "lastName", "email-address", "location", "summary", "specialties", "picture-url")
                .result(function(data) {
                	var data = data.values[0];
                	var loginView = {
						linkedinId: data.id,
                        email: data.emailAddress,
                        firstName: data.firstName,
                        lastName: data.lastName,
                        pictureUrl: data.pictureUrl
					};
                	loginService.linkedinLogin(loginView).success(function(view) {
                        if (view.hasNoProfile) {
                            window.location = '/#/profile-edit';
                        } else {
                            //Redirect para home
                            window.location = '/#/my-projects';
                        }
					});
				});
		}, null);
    };

	function cadastrar(view) {
		loginService.cadastrar(view).success(function(view) {
			//Redirect para home
			window.location = '/#/profile-edit/';
		});
	}
     
}]);