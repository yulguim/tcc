app.controller("searchCtrl", ['$routeParams', "searchService", function ($routeParams, searchService) {
	var vm = this;

	vm.view = {};

	var iniciarTela = function() {
		var key = decodeURIComponent($routeParams.key);
		var view = {};
		view.search = key;
        searchService.search(view).success(function(view) {
			vm.view = view;
		})
	};

	iniciarTela();
}]);