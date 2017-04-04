app.controller("profileCtrl", ["$scope", '$routeParams', "profileService", "contatosService", function ($scope, $routeParams, profileService, contatosService) {
	var vm = this;

	vm.view = {};
	//functions
	vm.requestFriendship = requestFriendship;
	vm.removeFriendship = removeFriendship;
	vm.acceptFriendship = acceptFriendship;
	vm.denyFriendship = denyFriendship;

	function requestFriendship() {
        var key = $routeParams.key;
        contatosService.request(key).success(function() {
        	vm.view.isRequested = true;
		});
	}

    function removeFriendship() {
        var key = $routeParams.key;
        contatosService.remove(key).success(function(view) {
            console.log(view);
        });
    }

    function acceptFriendship() {
        var key = $routeParams.key;
        contatosService.accept(key).success(function() {
            vm.view.isFriend = true;
        });
    }

    function denyFriendship() {
        var key = $routeParams.key;
        contatosService.ignore(key).success(function(view) {
            console.log(view);
        });
    }

	var iniciarTela = function() {
		var key = $routeParams.key;
		profileService.load(key).success(function(view) {
			vm.view = view;
		})
	};
     
	iniciarTela();
}]);