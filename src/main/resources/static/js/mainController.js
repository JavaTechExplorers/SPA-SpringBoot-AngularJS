/**
 * Main controller for Home page
 */
mySpringApp.controller('mainController', function($rootScope, $scope, $http,$location) {

    $scope.message = 'Sample Single Page application development using Angular 1.x and Spring Boot';

    $scope.logout = function() {

	$location.path("/");

	$http.post('logout', {}).then(function() {
	    $rootScope.authenticated = false;
	    $location.path("/");
	}),function (error){
	    $rootScope.authenticated = false;
	};
    }

    $scope.isLinkActive = function(url) {
	return url === $location.path();
    }

});