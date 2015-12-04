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

    console.log(jsonData);

    var req = {
      method: 'GET',
      url: 'http://mongorestase.mybluemix.net/api/courseinfo/courses'

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
    angular.element(document.getElementById('saveButton'))[0].disabled = false;

    $scope.retrieveCoursesForCompletion();

    console.log('printing completed courses in update settings button')
    console.log($scope.coursesCompltedList)

  }





  $scope.retrieveCompletedCourses = function(){

    var jsonData = '{"username":"'+$scope.usname+'"}'

    console.log(jsonData);

    console.log("hello json data");
    var req = {
      method: 'POST',
      url: 'http://mongorestase.mybluemix.net/api/profile/retrievecompletedcourses',
      data: jsonData
    };

    $http(req).then(function (response) {
      console.log('inside success function of completedCourses retrieved from database')
      console.log(response);
      $scope.coursesCompltedList = response.data.completedcourses;

      console.log(response.data.completedcourses);

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
    angular.element(document.getElementById('saveButton'))[0].disabled = true;
  });



  $scope.saveSettings = function(){

    angular.element(document.getElementById('selectoptions'))[0].disabled = true;
    angular.element(document.getElementById('saveButton'))[0].disabled = true;
    angular.element(document.getElementById('updateButton'))[0].disabled = false;



    var tempArraydata = $scope.coursesCompltedList;

    var jsonData = '{"username:"'+$scope.usname+'","completedcourses":"{}"}';

    var dataString = $scope.usname;

    //var ts = '';
    //for( var i=0; i< tempArraydata.length;i++){
    //  ts = ts + 'coursename:"'+
    //}

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
      url: 'http://mongorestase.mybluemix.net/api/profile/enrolledcourses',
      data: dataString
    };

    console.log("printing request");
    console.log(req);
    $http(req).then(function (response) {
      console.log('inside success function of completedCourses retrieved from database')
      console.log(response.data.completedcourses);
      $scope.coursesCompltedList = response.data.completedcourses;
      console.log($scope.items);
    })

  }

});
