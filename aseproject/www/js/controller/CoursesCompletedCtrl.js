/**
 * Created by Venu on 11/11/15.
 */

myapp.controller('CoursesCompletedController', function($scope, $state, $http,$rootScope){

  $scope.usname = $rootScope.usernames;

  $scope.coursesCompltedList = [];

  $scope.coursesForUpdation = [];




  $scope.retrieveCoursesForCompletion = function(){

    console.log("inside homectrl");

    var jsonData = '{"username:"'+$scope.usname+'"}';

    var req = {
      method: 'GET',
      url: 'http://mongorestserviceapi.mybluemix.net/api/courseinfo/courses'

    };

    $http(req).then(function (response) {
      console.log('inside success function of courses for updation ')
      $scope.coursesForUpdation = response.data;
    })
  }







  $scope.coursesCompltedList =[];
  $scope.updateList = function(value){
    console.log('Entered inside the items')
    console.log(value)

    var temporary = value.coursename;

    if($scope.coursesCompltedList.indexOf(temporary) == -1){
      console.log("creating a new one")
      $scope.coursesCompltedList.push(temporary)
    }
    else{
      console.log("already existed so leaving")
    }
    console.log('printing courses')
    console.log($scope.listOfCourses)
  }








  $scope.updateSettings = function(){

    angular.element(document.getElementById('selectoptions'))[0].disabled = false;

    $scope.retrieveCoursesForCompletion();

    console.log('printing completed courses in update settings button')
    console.log($scope.coursesCompltedList)

  }





  $scope.retrieveCompletedCourses = function(){

    var jsonData = '{"username":"'+$scope.usname+'"}'
    var req = {
      method: 'POST',
      url: 'http://mongorestserviceapi.mybluemix.net/api/profile/retrievecompletedcourses',
      data: jsonData
    };

    $http(req).then(function (response) {
      console.log('inside success function of completedCourses retrieved from database')
      $scope.coursesCompltedList = response.data;
      console.log($scope.items);
    })

}









  $scope.$on('$ionicView.afterEnter', function(){
    // Any thing you can think of
    console.log("After enter in CoursesCompleted page..");

   // $scope.retrieveCoursesForCompletion();

    $scope.retrieveCompletedCourses();

    console.log('hi')
    console.log($scope.coursesCompltedList)
    console.log('hello')

    angular.element(document.getElementById('selectoptions'))[0].disabled = true;
  });



  $scope.saveSettings = function(){

    angular.element(document.getElementById('selectoptions'))[0].disabled = true;

    var jsonDATA = JSON.stringify($scope.coursesCompltedList)

    var jsonData = '{"username:"'+$scope.usname+'","completedcourses":"'+jsonDATA+'"}';

    console.log('printing json data')

    console.log(jsonData)

    var req = {
      method: 'POST',
      url: 'http://mongorestserviceapi.mybluemix.net/api/profile/completedcourses',
      data: jsonData
    };

    $http(req).then(function (response) {
      console.log('inside success function of completedCourses retrieved from database')
      $scope.coursesCompltedList = response.data;
      console.log($scope.items);
    })

  }

});
