/**
 * 
 */
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
	$http.get('user').success(function(data) {
	    if (data.name) {
		$rootScope.authenticated = true;
	    } else {
		$rootScope.authenticated = false;
	    }
	    callback && callback();
	}).error(function() {
	    $rootScope.authenticated = false;
	    callback && callback();
	});
    }

    authenticate();

    $scope.credentials = {};

    $scope.login = function() {

	$http.post('login', $.param($scope.credentials), {
	    headers : {
		"content-type" : "application/x-www-form-urlencoded"
	    }
	}).success(function(data) {

	    authenticate(function() {
		if ($rootScope.authenticated) {
		    $location.path("/");
		    $scope.error = false;
		} else {
		    $location.path("/login");
		    $scope.error = true;
		}
	    });
	}).error(function(data) {
	    $location.path("/login");
	    $scope.error = true;
	    $rootScope.authenticated = false;
	})
    };

});