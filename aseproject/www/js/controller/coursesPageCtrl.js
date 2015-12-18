/**
 * Created by Venu on 10/18/15.
 */

myapp.controller('coursespagectrl', function($scope, $http, $state, $rootScope){

  console.log("inside coursedatactrl")
 // console.log($scope.receivedData)

  $scope.fetchCourses = function () {
    console.log("inside homectrl");

    var req = {
      method: 'GET',
      url: 'http://mongorestase.mybluemix.net/api/courseinfo/courses'

    };

    $scope.courses = [];
    var resData = '';

    $http(req).then(function (response) {
      $scope.courses = response.data;

      //SharedData.set(response.data);
      console.log($scope.courses);

      //$state.go('homepage.coursespage');
    })
  };

  $scope.displayDeatils = function(functionalData){

    console.log('displaying details of settings');

    console.log(functionalData);

    $rootScope.ratingItem = functionalData;

    $state.go('studentcoursedetails');

  }
  $scope.$on('$ionicView.afterEnter', function(){
    // Any thing you can think of
    console.log("After enter..");
    $scope.fetchCourses();
  });
});
