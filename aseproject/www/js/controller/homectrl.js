/**
 * Created by Venu on 10/17/15.
 */

myapp.controller('homectrl', function ($scope, $http, $state ) {

  $scope.professors = function() {

    console.log("inside professors");

    var req = {

      method: 'GET',
      url: 'http://mongorestserviceapi.mybluemix.net/api/professors/retrieve'
    };

    $scope.professorsResponseData = [];

    $http(req).then(function (response) {

      console.log("inside response received");
      console.log(response);

      $scope.professorsResponseData = response.data;

      $state.go('homepage.professorHome');
    });
  }

  $scope.navigatePage = function(){

    console.log('trying to naviage to another page of profile')
    $state.go('profile.home')
  }
})


