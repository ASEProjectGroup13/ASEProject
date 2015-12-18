/**
 * Created by Lema Chowdary on 11/11/2015.
 */

myapp.controller('MajorCreateCtrl', function($scope,$http,$state) {

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
      var urlTesting = URLRetriever.getInstance();
      var base = urlTesting.urlString();
      var req = {
        method: 'POST',
        url: base + '/majorinfo/createmajor',
        data: data
      };

      $http(req).then(function (response) {
        console.log(response + "response received from console");

        console.log(response);

        var statusing = response.data.statusmessage;
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



})

