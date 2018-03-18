/**
 * Login controller
 */
var mySpringApp = angular.module('mySpringApp', [ 'ngRoute', 'oc.lazyLoad' ]);

mySpringApp.controller('signupController', function($rootScope, $scope, $http, $location) {

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
                        function(data, status, headers) {
                                alert('User account created successfully');
                        // $location.path("/");
                        }),

                function(error) {
                        $location.path("/login");
                        $scope.error = true;
                        $rootScope.authenticated = false;
                };
        }
});