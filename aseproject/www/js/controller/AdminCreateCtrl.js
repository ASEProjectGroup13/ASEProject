/**
 * Created by Venu on 10/19/15.
 */
myapp.controller('AdminCreateCtrl', function($scope,$http,$state) {

  $scope.professorsData = [];
  $scope.$on('$ionicView.afterEnter', function() {
    // Any thing you can think of
    $scope.retrieveProfs();
  })
    $scope.retrieveProfs = function () {


      var request = {

        method: 'GET',
        url: 'http://mongorestserviceapi.mybluemix.net/api/professors/retrieve'
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
      url: 'http://mongorestserviceapi.mybluemix.net/api/courseinfo/insertcourse',
      data: data
    };

    $http(req).then(function (response) {
      console.log(response + "response received from console");

      var statusing = response.data.status;
      console.log(statusing)

      if (statusing == 'success') {
        console.log("in success state")
        $state.go('adminhome')
      } else {
        console.log("in failure state")
        //$state.go('signup')
      }
    })
  }

  });

