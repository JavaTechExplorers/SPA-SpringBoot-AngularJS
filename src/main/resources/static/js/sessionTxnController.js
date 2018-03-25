/**
 * Session Txn for search operation
 */
var mySpringApp = angular.module('mySpringApp', [ 'ngRoute','oc.lazyLoad' ]);

mySpringApp.controller('sessionTxnController',function($scope, $http) {

    $scope.message = 'Session Transaction Details';

    var urlBase = "";
    $http.defaults.headers.post["Content-Type"] = "application/json";

    function listAllTransactions() {
	$http.post(urlBase + '/getTxnData', {}).then(function(response) {
	    $scope.dataList = response.data;
	});
    }

    listAllTransactions();

});
