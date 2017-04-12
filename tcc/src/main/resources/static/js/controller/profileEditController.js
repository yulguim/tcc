app.controller("profileEditCtrl", ["$scope", '$routeParams', '$timeout', 'Upload', "profileEditService", "localizacaoService", function ($scope, $routeParams, $timeout, Upload, profileEditService, localizacaoService) {
    var vm = this;
    var ocupacaoDatabase = [];

    vm.view = {};
    vm.cidades = [];
    vm.habilidades = [];

    //functions
    vm.procurarCidade = procurarCidade;
    vm.procurarOcupacao = procurarOcupacao;
    vm.salvarPerfil = salvarPerfil;
    vm.addLink = addLink;
    vm.removeLink = removeLink;
    vm.onSelectLocalizacao = onSelectLocalizacao;
    vm.onSelectOcupacao = onSelectOcupacao;
    vm.uploadAvatar = uploadAvatar;

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

    function procurarOcupacao(str) {
        if (str === undefined || str === null || str.length < 3) {
            return;
        }

        vm.ocupacoes = ocupacaoDatabase.filter(function(o) {
            return o.label.indexOf(str) != -1;
        });
    }

    function onSelectLocalizacao($item, $model, $label) {
        vm.view.localizacao = $model;
    }

    function onSelectOcupacao($item, $model, $label) {
        vm.view.ocupacao = $model;
    }

    function salvarPerfil() {
        console.log(vm.view);
        profileEditService.save(vm.view).success(function(view) {
            console.log(view);
        });
    }

    function uploadAvatar(dataUrl, name) {
        console.log(Upload.dataUrltoBlob(dataUrl, name));
        Upload.upload({
            url: '/account/save-avatar',
            data: {
                file: Upload.dataUrltoBlob(dataUrl, name)
            },
        }).then(function (response) {
            $timeout(function () {
                $scope.result = response.data;
            });
        }, function (response) {
            if (response.status > 0) $scope.errorMsg = response.status
                + ': ' + response.data;
        }, function (evt) {
            $scope.progress = parseInt(100.0 * evt.loaded / evt.total);
        });
    }

	var iniciarTela = function() {
        profileEditService.initialData().success(function(data) {
            vm.habilidades = data.formData.habilidades;
            ocupacaoDatabase = data.formData.ocupacoes;

            vm.view = data.formData.meuPerfil;
        });
	};
     
	iniciarTela();
}]);