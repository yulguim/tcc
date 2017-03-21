app.controller("profileEditCtrl", ["$scope", '$routeParams', "profileEditService", function ($scope, $routeParams, profileEditService) {
    var vm = this;

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