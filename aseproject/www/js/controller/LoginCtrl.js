/**
 * Created by Venu on 10/11/15.
 */

myapp.controller('LoginCtrl',function($scope ,$http, $state,$cordovaNetwork,$rootScope){


  $scope.setUserName = function(uname){
    $rootScope.usernames = uname;
  }


  $scope.login = function(data){

    var urlTestString = URLRetriever.getInstance();

    console.log("hiii")

    console.log(urlTestString.urlString())

    console.log(data)

   // var isOnline = $cordovaNetwork.isOnline()

   // alert(isOnline)
    var req = {
      method: 'POST',
      url: urlTestString.urlString()+'/mongo/login',
      data: data
      //contentType: "application/json; charset=utf-8"
    };

    $http(req).then(function(response) {
      console.log(response+"response received from console");

      var status = response.data.statusmessage;

      //$cordovaSms.send('8164622782',status).then(function(){
      //  console.log("message success")
      //})

      if(status == 'success'){
        console.log("in success state")
       // alert("hello")

        $scope.setUserName(data.username)

        if(data.usertype =='admin'){
          $state.go('adminhome');
        }else if(data.usertype == 'student'){
          $state.go('homepage.coursespage')
        }


      }else{
        console.log("in failure state")
        $state.go('login')
      }


    }, function(data) {
      console.log(data);
    });

  };


});

