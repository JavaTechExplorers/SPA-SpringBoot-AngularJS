/**
 * Login controller
 */
var mySpringApp = angular.module('mySpringApp', [ 'ngRoute', 'oc.lazyLoad', 'ui.bootstrap' ]);

mySpringApp.controller('signupController', function($rootScope, $scope, $http, $location, $uibModal) {

    var urlBase = "";

    $http.defaults.headers.post["Content-Type"] = "application/json";

    $scope.createAccount = function() {
	$http.post(urlBase + '/createAccount', {
	    username : $scope.username,
	    password : $scope.password,
	    firstName : $scope.firstName,
	    lastName : $scope.lastName,
	    mailId : $scope.mailId,
	    phoneNum : $scope.phoneNum
	}).then(
		function(response) {
		    
		    var data = response.data;
		    if (data.errorsFound != undefined && data.errorsFound === 'Y') {
			
			$scope.open();
		    } else {
			
			alert('User account created successfully. Please sign-in.');
			$location.path("/login");
		    }
		}),
		function(error) {
	    		$location.path("/login");
	    		$scope.error = true;
	    		$rootScope.authenticated = false;
		};
    }

    $scope.open = function () {
	var modalInstance = $uibModal.open({
	    templateUrl: '/templates/errors.html',
	    controller: 'errorController',
	});
    }
});

mySpringApp.controller('errorController', function ($scope, $uibModalInstance) {
    $scope.close = function () {
	$uibModalInstance.dismiss('cancel');
    };
});