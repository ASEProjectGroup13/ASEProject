/**
 * Created by Venu on 11/11/15.
 */

myapp.controller('CoursesEnrolledCtrl', function($scope, $state, $http,$rootScope){

  $scope.usrname = $rootScope.usernames;

  $scope.coursesEnrolledList = [];

  $scope.coursesForEnrolled = [];




  $scope.retrieveCoursesForEnrollment = function(){

    console.log("inside homectrl");

    var jsonData = '{"username:"'+$scope.usrname+'"}';

    console.log(jsonData);

    var req = {
      method: 'GET',
      url: 'http://mongorestase.mybluemix.net/api/courseinfo/courses'

    };

    $http(req).then(function (response) {
      console.log('inside success function of courses for updation ')
      $scope.coursesForEnrolled = response.data;
    })
  }







  $scope.coursesEnrolledList =[];
  $scope.updateEnrolledList = function(value){
    console.log('Entered inside the items')
    console.log(value)

    var temporary = value.coursename;

    if($scope.coursesEnrolledList.indexOf(temporary) == -1){
      console.log("creating a new one")
      $scope.coursesEnrolledList.push(temporary)
    }
    else{
      console.log("already existed so leaving")
    }
    console.log('printing courses')

  }








  $scope.updateEnrollSettings = function(){

    angular.element(document.getElementById('selectoptions'))[0].disabled = false;
    angular.element(document.getElementById('saveButton'))[0].disabled = false;

    $scope.retrieveCoursesForEnrollment();

    console.log('printing completed courses in update settings button')
    console.log($scope.coursesEnrolledList)

  }





  $scope.retrieveEnrolledCourses = function(){

    var jsonData = '{"username":"'+$scope.usrname+'"}'

    console.log(jsonData);

    console.log("hello json data");
    var req = {
      method: 'POST',
      url: 'http://mongorestase.mybluemix.net/api/profile/retrieveenrolledcourses',
      data: jsonData
    };

    $http(req).then(function (response) {
      console.log('inside success function of completedCourses retrieved from database')
      console.log(response);
      $scope.coursesEnrolledList = response.data.enrolledcourses;

      console.log(response.data.enrolledcourses);
      $scope.enrolledcourses = {
        username:$scope.usrname
      }


    })

  }









  $scope.$on('$ionicView.afterEnter', function(){
    // Any thing you can think of
    console.log("After enter in Courses Enrolled page..");

    // $scope.retrieveCoursesForCompletion();

    $scope.retrieveEnrolledCourses();

    console.log('hi')
    console.log($scope.coursesEnrolledList)
    console.log('hello')

    angular.element(document.getElementById('selectoptions'))[0].disabled = true;
    angular.element(document.getElementById('saveButton'))[0].disabled = true;
  });



  $scope.savingSettings = function(){

    angular.element(document.getElementById('selectoptions'))[0].disabled = true;
    angular.element(document.getElementById('saveButton'))[0].disabled = true;
    angular.element(document.getElementById('updateButton'))[0].disabled = false;

    var tempArraydata = $scope.coursesEnrolledList;

    var jsonData = '{"username:"'+$scope.usrname+'","completedcourses":"{}"}';

    var dataString = $scope.usrname;


    for( var i = 0; i < tempArraydata.length; i++){

      dataString = dataString+","+tempArraydata[i];
      console.log('printing value ');
      console.log(tempArraydata[i]);
    }

    console.log('printing json data')

    console.log(jsonData)

    console.log(dataString);

    var req = {
      method: 'POST',
      url: 'http://mongorestase.mybluemix.net/api/profile/completedcourses',
      data: dataString
    };

    console.log("printing request");
    console.log(req);
    $http(req).then(function (response) {
      console.log('inside success function of completedCourses retrieved from database')
      console.log(response.data.enrolledcourses);
      $scope.coursesEnrolledList = response.data.enrolledcourses;

    })

  }

});

