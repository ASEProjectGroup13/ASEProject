/**
 * Created by Venu on 10/11/15.
 */

myapp.controller('LoginCtrl', function($scope ,$http, $state){

  $scope.login = function(data){

    console.log(data)

    var req = {
      method: 'POST',
      url: 'http://localhost:8080/com.umkc.rest/api/mongo/login',
      data: data
    };

    $http(req).then(function(response) {
      console.log(response+"response received from console");

      var status = response.data.statusmessage;

      if(status == 'success'){
        console.log("in success state")
        $state.go('home')
      }else{
        console.log("in failure state")
        $state.go('login')
      }


    }, function(data) {
      console.log(data);
    });

  };


});

