/**
 * Controller for CRUD operations
 */
mySpringApp.controller('dataController',function($scope, $http) {

	    $scope.message = 'Data Page! We are maintaining the Person information!';

	    var urlBase = "";
	    $scope.selection = [];
	    $scope.statuses = [ 'Male', 'Female' ];
	    $http.defaults.headers.post["Content-Type"] = "application/json";

	    function listAllEmployees() {
			$http.post(urlBase + '/getData', {
			    empName : 'Vignesh',
			    empGender : 'Male'
			}).success(function(data, status, headers) {
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
				var empDetail = {
					empId : $scope.empId,
					empName : $scope.empName,
					empDesc : $scope.empDesc,
					empGender : $scope.empGender
				};
			    $http.post(urlBase + '/save', empDetail).success( function(data, status, headers) {
			    		//alert('Employee details added');
			    		listAllEmployees();
			    });
			}
	    };

	    // add a new task
	    $scope.clear = function() {
	    		$scope.empId = "";
			$scope.empName = "";
			$scope.empDesc = "";
			$scope.empGender = "";
	    };
	    
	    $scope.remove = function(employee) {
	    		var empDetail = {
					empId : employee.empId,
					empName : employee.empName,
					empDesc : employee.empDesc,
					empGender : employee.empGender
				};
			    $http.post(urlBase + '/delete', empDetail).success( function(data, status, headers) {
			    		$scope.dataList = data;
			    });
	    };
	    $scope.update = function(employee) {
	    		$scope.empId = employee.empId;
	    		$scope.empName = employee.empName;
	    		$scope.empDesc = employee.empDesc;
	    		$scope.empGender = employee.empGender;
	    };

	});