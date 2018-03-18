/**
 * Login controller
 */
var mySpringApp = angular.module('mySpringApp', [ 'ngRoute','oc.lazyLoad' ]);

mySpringApp.controller('loginController', function($rootScope, $scope, $http,$location) {

    /*
     * The authenticate() function is called when the controller is loaded to see
     * if the user is actually already authenticated (e.g. if he had refreshed
     * the browser in the middle of a session). We need the authenticate()
     * function to make a remote call because the actual authentication is done
     * by the server, and we don’t want to trust the browser to keep track of
     * it.
     */
    var authenticate = function(callback) {
	
	// alert('Inside authenticate()');
	
	$http.get('user').then(function(response) {
	    
	    if (response.data.name) {
		$rootScope.authenticated = true;
	    } else {
		$rootScope.authenticated = false;
	    }
	    
	    // If call back exists, then call the function
	    callback && callback();
	    
	}),function (error){
	    
	    $rootScope.authenticated = false;
	    
	    callback && callback();
	};
    }

    authenticate();

    $scope.credentials = {};

    $scope.login = function() {

	//alert('Inside Login method : username : ' + $scope.credentials.username + " : " + $scope.credentials.password);
	
	$http.post('login', $.param($scope.credentials), {
	    headers : {
		"content-type" : "application/x-www-form-urlencoded"
	    }
	}).then(function(response) {
	    
	    // alert('login response = ' + username);
	    
	    authenticate(function() {
		if ($rootScope.authenticated) {
		    $location.path("/");
		    $scope.error = false;
		} else {
		    $location.path("/login");
		    $scope.error = true;
		}
	    });
	}),function (error){
	    
	    $location.path("/login");
	    $scope.error = true;
	    $rootScope.authenticated = false;
	};
    };
});