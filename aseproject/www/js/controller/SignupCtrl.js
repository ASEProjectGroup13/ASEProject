/**
 * Created by Venu on 10/11/15.
 */

myapp.controller('SignupCtrl', function ($scope, $http, $state) {

  $scope.register = function (data) {

    var baseURL = URLRetriever.getInstance().urlString();


    console.log(data);

    var req = {
      method: 'POST',
      url: baseURL+'/mongo/create',
      headers: {
        'Access-Control-Allow-Headers':'*'
      },
      data: data
    };

    $http(req).then(function(response) {
      console.log(response+"response received from console");

      var status = response.data.statusmessage;

      if(status == 'success'){
        console.log("in success state")
        $state.go('login')
      }else{
        console.log("in failure state")
        $state.go('signup')
      }


    }, function(data) {
      console.log(data);
    });
  }
});
