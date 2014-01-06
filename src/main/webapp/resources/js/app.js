var sampleApp = angular.module('sampleApp', []);

sampleApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/postdetails/12', {
	templateUrl: 'postdetails.html',
	controller: 'PostDetailsController'
      });
    
//      when('/ShowOrders', {
//	templateUrl: 'show_orders.html',
//	controller: 'ShowOrdersController'
//      }).
//      otherwise({
//	redirectTo: '/AddNewOrder'
//      });
}]);


sampleApp.controller('PostDetailsController', function($scope) {
	alert("controller");
	$scope.message = {'type':'succes', 'message':'This is Add new order screen'};
	//$scope.Post = 
});

//
//sampleApp.controller('ShowOrdersController', function($scope) {
//
//	$scope.message = 'This is Show orders screen';
//
//});

