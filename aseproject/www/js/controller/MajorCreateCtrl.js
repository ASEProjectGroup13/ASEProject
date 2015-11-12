/**
 * Created by Lema Chowdary on 11/11/2015.
 */

myapp.controller('MajorCreateCtrl', function($scope,$http,$state) {

  $scope.retrieveMajors = function () {

    var urlTestString = URLRetriever.getInstance();
    var base = urlTestString.urlString();
    var request = {

      method: 'GET',
      url: base + '/api/mongo/majors'
    }

    $scope.createMajor = function (data) {
      var urlTesting = URLRetriever.getInstance();
      var base = urlTesting.urlString();
      var req = {
        method: 'POST',
        url: base + '/api/majorinfo/insertmajor',
        data: data
      };

      $http(req).then(function (response) {
        console.log(response + "response received from console");

        var statusing = response.data.status;
        console.log(statusing)

        if (statusing == 'success') {
          console.log("in success state")
          $state.go('majorhome')
        } else {
          console.log("in failure state")
          //$state.go('signup')
        }
      })
    }

  }
  $scope.listOfSelectedCourses = [];
  $scope.updateSettings = function(data) {
    console.log('setting list of courses')
    if ($scope.listOfSelectedCourses.indexOf(data) == -1) {
      $scope.listOfSelectedCourses.push(data);
    }
    else {
      console.log('array element is already presented ')
    }
  }

})

