app.controller("projetoCtrl", ['$routeParams', "projetoService", function ($routeParams, projetoService) {
	var vm = this;
	vm.tab = 'timeline';

	vm.view = {};
    vm.participantes = null;
    vm.chat = null;

	vm.mensagem = {};

	//functions
	vm.request = request;
	vm.accept = accept;
	vm.cancelRequest = cancelRequest;
	vm.leave = leave;

	vm.sendMensagem = sendMensagem;

	function request() {
		projetoService.request(createView()).success(function() {
			vm.view.isRequested = true;
		});
	}

    function accept() {
		projetoService.accept(createView()).success(function() {

		});
    }
	
	function cancelRequest() {
		projetoService.leave(createView()).success(function () {
			vm.view.isRequested = false;
        });
    }
    
    function leave() {
        projetoService.leave(createView()).success(function () {

        });
    }

    function sendMensagem() {

	}

	function createView() {
        var key = $routeParams.key;
        var view = {};
        view.projetoKey = key;

        return view;
	}

	var iniciarTela = function() {
        var key = $routeParams.key;
        projetoService.load(key).success(function(data) {
			vm.view = data.projeto;
			vm.participantes = data.participantes;
			vm.chat = data.chat;
		});
	};
	
    iniciarTela(); 
}]);