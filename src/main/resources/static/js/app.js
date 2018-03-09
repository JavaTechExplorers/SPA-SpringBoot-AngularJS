//create the module and name it mySpringApp

/*
 * AngularJS 1.2 and Routing The ngRoute module is no longer included in Angular after version 1.1.6. 
 * You will need to call the module and add it to the head of your document to use it.
 */

//also include ngRoute for all our routing needs
var mySpringApp = angular.module('mySpringApp', [ 'ngRoute' ]);

//configure our routes
mySpringApp.config(function($routeProvider) {
    $routeProvider

    // route for the home page
    .when('/', {
    		templateUrl : '/templates/home.html',
    		controller : 'mainController'
    })

    // route for the contact page
    .when('/login', {
    		templateUrl : '/templates/login.html',
    		controller : 'loginController'
    })

    // route for the about page
    .when('/data', {
    		templateUrl : '/templates/data.html',
    		controller : 'dataController'
    }).otherwise('/');
});

