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

            //Localização
            $('input.autocomplete').autocomplete({
                data: {
                    "Apple": null,
                    "Microsoft": null,
                    "Google": 'http://placehold.it/250x250'
                },
                limit: 20, // The max amount of results that can be shown at once. Default: Infinity.
                onAutocomplete: function(val) {
                    // Callback function when value is autcompleted.
                },
                minLength: 1, // The minimum length of the input for the autocomplete to start. Default: 1.
            });

            //Tags
            $('.chips').material_chip();
            $('.chips-autocomplete').material_chip({
                autocompleteOptions: {
                    data: {
                        'Apple': null,
                        'Microsoft': null,
                        'Google': null
                    },
                    limit: Infinity,
                    minLength: 1
                }
            });
        });
	};
     
	iniciarTela();
}]);