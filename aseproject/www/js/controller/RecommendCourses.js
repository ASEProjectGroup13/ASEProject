/**
 * Created by Venu on 12/4/15.
 */

myapp.controller('RecommendCourses', function($scope, $state, $http,$rootScope){

  $scope.usersname = $rootScope.usernames;

  $scope.coursesPlannedList = [];

  $scope.coursesForPlanned = [];

  $scope.majorname ='';

  $scope.recommendedCourses = [];


    $scope.retrieveCoursesForPlanned = function(){

    var jsonData = '{"username:"'+$scope.usersname+'"}';

    console.log(jsonData);

    var req = {
      method: 'GET',
      url: 'http://mongorestase.mybluemix.net/api/courseinfo/courses'

    };

    $http(req).then(function (response) {
      console.log('inside success function of courses for updation ')
      $scope.coursesForPlanned = response.data;
    })
  }



  $scope.coursesPlannedList =[];
  $scope.updatePlanCourseList = function(value){
    console.log('Entered inside the items')
    console.log(value)

    var temporary = value.coursename;

    if($scope.coursesPlannedList.indexOf(temporary) == -1){
      console.log("creating a new one");
      $scope.coursesPlannedList.push(temporary);
    }
    else{
      console.log("already existed so leaving");
    }
    console.log('printing courses');
    console.log($scope.listOfCourses);
  }



  $scope.recommendCourses = function(){
    console.log('Inside Courses Recommendation');

    var baseURL = URLRetriever.getInstance();

    var tempArraydata = $scope.coursesPlannedList;

    var dataString = $scope.usersname;

    for( var i = 0; i < tempArraydata.length; i++){
      dataString = dataString+","+tempArraydata[i];
      console.log('printing value ');
      console.log(tempArraydata[i]);
    }

    dataString = dataString + ",Advanced Software Engineering,Formal Software Specification,Software Architecture and Design"

    var req = {
      method: 'POST',
      url: baseURL.urlString()+'/recommend/recommendcourse',
      data: dataString
    };

    $http(req).then(function (response) {
      console.log('inside success function of courses for updation ');
      $scope.coursesForPlanned = response.data;

      console.log(response.data);

      $scope.recommendedCourses = response.data.courses;
      $scope.majorsname = response.data.majorname;

      console.log(response.data.courses);

      $state.go('profile.recommendedcourses');

    })
  }

  $scope.$on('$ionicView.afterEnter', function(){
    $scope.retrieveCoursesForPlanned();
  });

});

