/**
 * Created by Venu on 10/19/15.
 */

myapp.controller('ProfessorsCtrl', function($scope,$http,$state){

  console.log("inside Professors Ctrl")

  $scope.fetchProfessors= function(){

    var request = {

      method:'GET',
      url: 'http://mongorestase.mybluemix.net/api/professors/retrieve'
    }

    $scope.professors = [];

    $http(request).then(function(response){
      console.log(response);
      console.log("response received from data");

      $scope.professors = response.data;

      $state.go('homepage.professors');
    })
  }

  $scope.$on('$ionicView.afterEnter', function(){
    // Any thing you can think of
    console.log("After enter..");
    $scope.fetchProfessors();
  });
})
