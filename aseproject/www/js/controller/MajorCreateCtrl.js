/**
 * Created by Lema Chowdary on 11/11/2015.
 */

myapp.controller('MajorCreateCtrl', function($scope,$http,$state) {

<<<<<<< HEAD
  //$scope.retrieveMajors = function () {
  //
  //  var urlTestString = URLRetriever.getInstance();
  //  var base = urlTestString.urlString();
  //  var request = {
  //
  //    method: 'GET',
  //    url: base + '/api/mongo/majors'
  //  }

    $scope.creatingMajor = function (data) {

      console.log('inside creation of major');
      console.log(data);
=======
  $scope.retrieveMajors = function () {

    var urlTestString = URLRetriever.getInstance();
    var base = urlTestString.urlString();
    var request = {

      method: 'GET',
      url: base + '/api/mongo/majors'
    }

    $scope.createMajor = function (data) {
>>>>>>> origin/master
      var urlTesting = URLRetriever.getInstance();
      var base = urlTesting.urlString();
      var req = {
        method: 'POST',
<<<<<<< HEAD
        url: base + '/majorinfo/createmajor',
=======
        url: base + '/api/majorinfo/insertmajor',
>>>>>>> origin/master
        data: data
      };

      $http(req).then(function (response) {
        console.log(response + "response received from console");

<<<<<<< HEAD
        console.log(response);

        var statusing = response.data.statusmessage;
=======
        var statusing = response.data.status;
>>>>>>> origin/master
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

<<<<<<< HEAD

=======
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
>>>>>>> origin/master

})

