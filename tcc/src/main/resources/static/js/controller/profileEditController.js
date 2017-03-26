app.controller("profileEditCtrl", ["$scope", '$routeParams', "profileEditService", "localizacaoService", function ($scope, $routeParams, profileEditService, localizacaoService) {
    var vm = this;

    vm.view = {};
    vm.cidades = [];
    vm.habilidades = [];

    //functions
    vm.procurarCidade = procurarCidade;
    vm.salvarPerfil = salvarPerfil;
    vm.addLink = addLink;
    vm.removeLink = removeLink;
    vm.onSelectLocalizacao = onSelectLocalizacao;

    function addLink() {
        vm.view.links.push({});
    }

    function removeLink(link) {
        var index = vm.view.links.indexOf(link);
        vm.view.links.splice(index, 1);
    }

    function procurarCidade(str) {
        if (str === undefined || str === null || str.length < 3) {
            return;
        }

        localizacaoService.searchCidade(str).success(function (data) {
            vm.cidades = data.cidades;
        });
    }

    function onSelectLocalizacao($item, $model, $label) {
        vm.view.localizacao = $model;
    }

    function salvarPerfil() {
        profileEditService.save(vm.view).success(function(view) {
            console.log(view);
        });
    }

	var iniciarTela = function() {
        profileEditService.initialData().success(function(data) {
            vm.habilidades = data.formData.habilidades;

            vm.view = data.formData.meuPerfil;
            if (!vm.view.links) vm.view.links = [{}];
        });
	};
     
	iniciarTela();
}]);