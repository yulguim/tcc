app.controller("profileEditCtrl", ["$scope", '$routeParams', "profileEditService", function ($scope, $routeParams, profileEditService) {
    var vm = this;

    vm.view = {};

    //functions
    vm.salvarPerfil = salvarPerfil;

    function salvarPerfil() {
        profileEditService.save(vm.view).success(function(view) {
            console.log(view);
        });
    }

	var iniciarTela = function() {
        profileEditService.initialData().success(function(data) {
            vm.view = data.formData.meuPerfil;
            if (vm.view.hasNoProfile) {
                console.log("nao tem perfil");
            }
        });
	};
     
	iniciarTela();
}]);