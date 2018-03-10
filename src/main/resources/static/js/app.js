//create the module and name it mySpringApp

/*
 * AngularJS 1.2 and Routing The ngRoute module is no longer included in Angular after version 1.1.6. 
 * You will need to explicitly call the module and add it to the head of your document to use it.
 */

//also include ngRoute for all our routing needs
var mySpringApp = angular.module('mySpringApp', [ 'ngRoute' ]);

//configure our routes
mySpringApp.config(function($routeProvider) {
    $routeProvider

    // route for the Home page
    .when('/', {
	templateUrl : '/templates/home.html',
	controller : 'mainController'
    })

    // route for the Login page
    .when('/login', {
	templateUrl : '/templates/login.html',
	controller : 'loginController'
    })
    
     // route for the Create An Account page
    .when('/createAccount', {
	templateUrl : '/templates/createAccount.html',
	controller : 'createAccountController'
    })

    // route for the Data Capture page
    .when('/data', {
	templateUrl : '/templates/data.html',
	controller : 'dataController'
    }).otherwise('/');
});

//create the controller and inject Angular's $scope
mySpringApp.controller('mainController', function($rootScope, $scope, $http,
	$location) {

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

/*
 * Login Controller Logic
 */
mySpringApp.controller('loginController', function($rootScope, $scope, $http,
	$location) {

    /*
     * The authenticate() function is called when the controller is loaded to see
     * if the user is actually already authenticated
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
		//TODO: $rootScope.authenticated who is setting the variable
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


/*
 * Create An Account Controller Logic
 */
//TODO:how to pass data 
mySpringApp.controller('createAccountController', function($rootScope, $scope, $http,
	$location) {
	
	 var urlBase = "";

	$http.defaults.headers.post["Content-Type"] = "application/json";

    $scope.createAccount = function() {
	$http.post(urlBase + '/createAccount', {
		username : $scope.username,
		password : $scope.password
	}).success(
		function(data, status, headers) {
			 $location.path("/");
		});
    }
    //TODO: how to handle validations 
});


/*
 * Data controller where Search and CRUD operation are performed
 */
mySpringApp
.controller(
	'dataController',
	function($scope, $http) {

	    $scope.message = 'Data Page! We are maintaining the Person information!';

	    var urlBase = "";
	    $scope.selection = [];
	    $scope.statuses = [ 'Male', 'Female' ];
	    $http.defaults.headers.post["Content-Type"] = "application/json";

	    function listAllEmployees() {
		$http.post(urlBase + '/getData', {
		    empName : 'AAAA',
		    empGender : 'Male'
		}).success(
			function(data, status, headers) {
			    $scope.dataList = data;
			});
	    }

	    listAllEmployees();

	    $scope.refresh = function() {
		listAllEmployees();
	    }
	    
	    // add a new task
	    $scope.save = function() {

		if ($scope.empName == undefined
			|| $scope.empDesc == undefined
			|| $scope.empGender == undefined
			|| $scope.empName == "" || $scope.empDesc == ""
			|| $scope.empGender == "") {

		    alert('Insufficient Data! Please provide values for all Employee fields');
		} else {

		    $http.post(urlBase + '/save', {
			empName : $scope.empName,
			empDesc : $scope.empDesc,
			empGender : $scope.empGender
		    }).success(
			    function(data, status, headers) {
				alert('Employee details added');
				listAllEmployees();
			    });
		}
	    };

	    // add a new task
	    $scope.clear = function() {
		$scope.empName = "";
		$scope.empDesc = "";
		$scope.empGender = "";
	    };

	});
