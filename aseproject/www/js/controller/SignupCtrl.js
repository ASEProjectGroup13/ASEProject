/**
 * Created by Venu on 10/11/15.
 */

myapp.controller('SignupCtrl', function ($scope, $http, $state) {

  $scope.register = function (data) {
    //var url = "http://192.168.0.15:8080/com.umkc.rest/api/mongo/register"

    console.log(data);

    var req = {
      method: 'POST',
      url: 'http://mongorestserviceapi.mybluemix.net/api/mongo/create',
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
