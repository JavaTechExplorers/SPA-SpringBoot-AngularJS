/**
 * Login controller
 */
var mySpringApp = angular.module('mySpringApp', [ 'ngRoute','oc.lazyLoad' ]);
mySpringApp.controller('signupController', function($rootScope, $scope, $http,$location) {
    
    var urlBase = "";

    $http.defaults.headers.post["Content-Type"] = "application/json";

    $scope.createAccount = function() {
	$http.post(urlBase + '/createAccount', {
	    userName : $scope.userName,
	    userPassword : $scope.userPassword,
	    firstName : $scope.firstName,
	    lastName : $scope.lastName,
	    mailId : $scope.mailId,
	    phoneNum : $scope.phoneNum
	}).success(
		function(data, status, headers) {
		    alert('User account created successfully');
		    // $location.path("/");
		});
    }
    //TODO: how to handle validations 
    
});