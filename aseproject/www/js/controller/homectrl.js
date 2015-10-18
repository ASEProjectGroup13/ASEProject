/**
 * Created by Venu on 10/17/15.
 */

myapp.controller('homectrl', function ($scope, $http, $state) {

  $scope.courses = function () {
    console.log("inside homectrl");

    var req = {
      method: 'GET',
      url: 'http://localhost:8080/com.umkc.rest/api/mongo/courses'

    };

    //$scope.responseData = "";

    $scope.responseData = [];
    var resData = '';

    $http(req).then(function (response) {
      console.log(response);
      $scope.responseData = response.data;

      $state.go('home');
    })
  }

  $scope.professors = function(){

    console.log("inside professors");

    var req = {

      method:'GET',
      url: 'http://localhost:8080/com.umkc.rest/api/professors/retrieve'
    };

    $scope.professorsResponseData = [];

    $http(req).then(function(response){

      console.log("inside response received");
      console.log(response);

      $scope.professorsResponseData = response.data;

      $state.go('home');
    });
  }
});


