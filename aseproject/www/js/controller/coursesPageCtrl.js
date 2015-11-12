/**
 * Created by Venu on 10/18/15.
 */

myapp.controller('coursespagectrl', function($scope, $http, $state){

  console.log("inside coursedatactrl")
 // console.log($scope.receivedData)

  $scope.fetchCourses = function () {
    console.log("inside homectrl");

    var req = {
      method: 'GET',
      url: 'http://mongorestserviceapi.mybluemix.net/api/courseinfo/courses'

    };

    $scope.courses = [];
    var resData = '';

    $http(req).then(function (response) {
      $scope.courses = response.data;

      //SharedData.set(response.data);
      console.log($scope.courses);

      $state.go('homepage.coursespage');
    })
  };

  $scope.$on('$ionicView.afterEnter', function(){
    // Any thing you can think of
    console.log("After enter..");
    $scope.fetchCourses();
  });
});
