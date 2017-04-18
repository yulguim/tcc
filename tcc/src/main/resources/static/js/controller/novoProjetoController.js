app.controller("novoProjetoCtrl", ['$routeParams', "projetoService", function ($routeParams, projetoService) {
	var vm = this;

	vm.view = {};

	//functions
    vm.addLink = addLink;
    vm.removeLink = removeLink;
    vm.save = save;

    function addLink() {
        vm.view.links.push({});
    }

    function removeLink(link) {
        var index = vm.view.links.indexOf(link);
        vm.view.links.splice(index, 1);
    }

    function save() {
    	projetoService.save(vm.view).success(function() {
    		window.location = '/#/my-projects';
		});
	}

	var iniciarTela = function() {
        var key = $routeParams.key;
		if (key !== undefined) {
			//load de projeto para editar
			projetoService.load(key).success(function(view) {
				vm.view = view;
            });
		} else {
			vm.view.links = [{}];
		}
    };

	iniciarTela();
}]);