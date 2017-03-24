app.controller("profileEditCtrl", ["$scope", '$routeParams', "profileEditService", "localizacaoService", function ($scope, $routeParams, profileEditService, localizacaoService) {
    var vm = this;

    vm.view = {};
    vm.cidades = [];
    vm.cidadesNome = {};

    //functions
    vm.procurarCidade = procurarCidade;
    vm.salvarPerfil = salvarPerfil;

    function procurarCidade(str) {
        if (str === undefined || str === null || str.length < 3) {
            return;
        }

        localizacaoService.searchCidade(str).success(function (data) {
            vm.cidades = data.cidades;
            vm.cidadesNome = data.cidadesNome;
            console.log(vm.cidades);
            console.log(vm.cidadesNome);

        });
    }

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