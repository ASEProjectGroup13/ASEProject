/**
 * Created by Venu on 10/19/15.
 */
myapp.controller('AdminCreateCtrl', function($scope,$http,$state) {

  $scope.professorsData = [];
  $scope.$on('$ionicView.afterEnter', function() {
    // Any thing you can think of
    $scope.retrieveProfs();

    $scope.retrieveMajors();
  })



  $scope.retrievedMajor = [];

  $scope.retrieveMajors = function () {

    var baseURLString = URLRetriever.getInstance()

    var request = {
      method: 'GET',
      url:baseURLString.urlString()+'/majorinfo/majors'
    }
    $http(request).then(function(response){

      console.log(response.data);

      $scope.retrievedMajor = response.data;
    });
  }



    $scope.retrieveProfs = function () {

      var request = {
        method: 'GET',
        url: 'http://mongorestase.mybluemix.net/api/professors/retrieve'
      }

      $http(request).then(function (response) {
        console.log(response.data);
        console.log("response received from data");

        $scope.professorsData = response.data;

        console.log("After entered into Admin Create Controller..");

      })
    }

  $scope.createCourse = function(data) {
    var req = {
      method: 'POST',
      url: 'http://mongorestase.mybluemix.net/api/courseinfo/insertcourse',
      data: data
    };

    $http(req).then(function (response) {
      console.log(response + "response received from console");

      var statusing = response.data.status;
      console.log(statusing)


        $state.go('adminhome')

    })
  }

  });
