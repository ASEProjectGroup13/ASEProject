/**
 * Created by Venu on 12/15/15.
 */

myapp.controller('ProfCoursesPageCtrl', function($http, $scope,$rootScope,$state){

  $scope.fetchingCourses = function () {
    console.log("inside homectrl");

    var urlFactory = URLRetriever.getInstance();
    var req = {
      method: 'GET',
      url: urlFactory.urlString()+'/courseinfo/courses'

    };

    $scope.profcourses = [];
    var resData = '';

    $http(req).then(function (response) {
      $scope.profcourses = response.data;


      //SharedData.set(response.data);
      console.log($scope.profcourses);

      //$state.go('homepage.coursespage');
    })
  };

  $scope.$on('$ionicView.afterEnter', function(){
    // Any thing you can think of
    console.log("After enter..");
    $scope.fetchingCourses();
  });
});
