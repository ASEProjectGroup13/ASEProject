/**
 * Created by Venu on 12/10/15.
 */

myapp.controller('StudentCourseDetailsCtrl', function($state, $http, $rootScope, $scope){

  var temporaryData = $rootScope.ratingItem;

  var personName = $rootScope.usernames;

  var status = '';

  $scope.retrieveCourseCompletionStatus = function (testdata) {

    var TestURL = URLRetriever.getInstance();

    var jsonData = '{"username":"'+personName+'","coursename":"'+testdata.coursename+'"}';

    console.log('json data');
    console.log(jsonData);

    var request ={

      url: TestURL.urlString()+'/courseinfo/coursestatus',
      method:'POST',
      data:jsonData
    }

    $http(request).then(function (response) {
      status = response.data.status;

      var ratingsprovided = response.data.ratingstatus;

      console.log('rating status');
      console.log(ratingsprovided);

      console.log('inside status printing methods');
      console.log(response.data);
      console.log(status);

      $scope.coursesdetails={

        courseid:testdata.courseid,
        coursename: testdata.coursename,
        description: testdata.description,
        professor:testdata.professor,
        specialization:testdata.specialization.majorname,
        statusCompletion:status

      }

      if((status=='completed' || status== 'enrolled') && ratingsprovided == 'notprovided'){
        angular.element(document.getElementById('feedback'))[0].disabled = false;
      }else{
        angular.element(document.getElementById('feedback'))[0].disabled = true;
      }
    })

  }


  $scope.$on('$ionicView.afterEnter', function(){

    var temporaryData = $rootScope.ratingItem;

    var personName = $rootScope.usernames;


    console.log('inside Student Course Details');

    $scope.retrieveCourseCompletionStatus(temporaryData);

    //$scope.coursesdetails={
    //
    //  courseid:temporaryData.courseid,
    //  coursename: temporaryData.coursename,
    //  description: temporaryData.description,
    //  professor:temporaryData.professor,
    //  specialization:temporaryData.specialization.majorname,
    //  statusCompletion:status
    //
    //}

    console.log(temporaryData);
    //if(status=='completed' || status== 'enrolled'){
    //angular.element(document.getElementById('feedback'))[0].disabled = false;
    //}else{
    //  angular.element(document.getElementById('feedback'))[0].disabled = true;
    //}


  });

  $scope.navigateToFeedback = function(){

    console.log('navigating to another page');
    $state.go('professorRatings');
  }

});
