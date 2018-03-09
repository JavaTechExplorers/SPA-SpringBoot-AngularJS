/**
 * 
 */
mySpringApp.controller('mainController', function($rootScope, $scope, $http,$location) {

    $scope.message = 'Sample Single Page application development using Angular 1.x and Spring Boot';

    $scope.logout = function() {

	$location.path("/");

	$http.post('logout', {}).success(function() {
	    $rootScope.authenticated = false;
	    $location.path("/");
	}).error(function(data) {
	    $rootScope.authenticated = false;
	});
    }

    $scope.isLinkActive = function(url) {
	return url === $location.path();
    }

});