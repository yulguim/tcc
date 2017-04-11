app.controller("profileEditCtrl", ["$scope", '$routeParams', '$timeout', 'Upload', "profileEditService", "localizacaoService", function ($scope, $routeParams, $timeout, Upload, profileEditService, localizacaoService) {
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

    function onSelectLocalizacao($item, $model, $label) {
        vm.view.localizacao = $model;
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

            vm.view = data.formData.meuPerfil;
            if (!vm.view.links) vm.view.links = [{}];

            console.log(vm.view);
        });
	};
     
	iniciarTela();
}]);