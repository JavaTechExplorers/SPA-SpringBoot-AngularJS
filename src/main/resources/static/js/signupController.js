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

			$scope.open(data.errorMessagesMap);
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

    $scope.open = function(errorDetails) {
	var modalInstance = $uibModal.open({
	    templateUrl : '/templates/errors.html',
	    controller : 'errorController',
	    resolve : {
		errorDetailsTxt : function() {
		    return errorDetails;
		}
	    }
	});
    }
});

mySpringApp.controller('errorController', function ($scope, $uibModalInstance, errorDetailsTxt) {

    /*
     * Sample data:
     * 
     * errorDetailsTxt = {"firstName":"First name cannot be empty","mailId":"Mail id cannot be empty","username":"Username already exist"}
     */
    var errorDetailsList = [];
    for (var key in errorDetailsTxt) {
	if (errorDetailsTxt.hasOwnProperty(key)) {
	    // alert(key + " -> " + p[key]);

	    errorDetailsList.push(errorDetailsTxt[key]);
	}
    }    

    $scope.errorDetailsList = errorDetailsList;

    $scope.close = function () {
	$uibModalInstance.dismiss('cancel');
    };
});