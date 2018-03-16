//create the module and name it mySpringApp

/*
 * AngularJS 1.2 and Routing The ngRoute module is no longer included in Angular after version 1.1.6. 
 * You will need to explicitly call the module and add it to the head of your document to use it.
 */

//also include ngRoute for all our routing needs


var mySpringApp = angular.module('mySpringApp', [ 'ngRoute','oc.lazyLoad' ]);

//configure our routes
mySpringApp.config(function($routeProvider) {
    $routeProvider

    // route for the home page
    .when('/', {
	templateUrl : '/templates/home.html',
	controller : 'mainController'
    })

    // route for the Login page
    .when('/login', {
	templateUrl : '/templates/login.html',
	controller : 'loginController',
	resolve : {
	    js : ['$ocLazyLoad','$http',function($ocLazyLoad,$http){
		return $ocLazyLoad.load('/js/loginController.js');
	    }]
	}
    })

    // route for the Create An Account page
    .when('/createAccount', {
	templateUrl : '/templates/signup.html',
	controller : 'signupController',
	resolve : {
	    js : ['$ocLazyLoad','$http',function($ocLazyLoad,$http){
		return $ocLazyLoad.load('/js/signupController.js');
	    }]
	}
    })

    // route for the Data Capture page
    .when('/data', {
	templateUrl : '/templates/data.html',
	controller : 'dataController',
	resolve : {
	    js : ['$ocLazyLoad','$http',function($ocLazyLoad,$http){
		return $ocLazyLoad.load('/js/dataController.js');
	    }]
	}
    }).otherwise('/');
});