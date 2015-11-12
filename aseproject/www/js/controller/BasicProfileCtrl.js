/**
 * Created by Venu on 11/9/15.
 */
myapp.controller('BasicProfileCtrl', function($state,$scope,$http, $rootScope){

  $scope.userUpdatedData ='';

  $scope.updateLoadData =function(){

    $scope.basicuserprofile ={
      username:$scope.userUpdatedData.username,
      name:$scope.userUpdatedData.name,
      email:$scope.userUpdatedData.email,
      phonenumber:$scope.userUpdatedData.phonenumber,
      password:$scope.userUpdatedData.password,
      statuses:false
    }

    angular.element(document.getElementById('saveButton'))[0].disabled = false;
  }

  $scope.retrieveProfile = function(){

    console.log('Hello i am in basic profile control');
    console.log($rootScope.usernames)

    var usname = $rootScope.usernames;

    var dataString = '{"username":"'+usname+'"}'

    var request = {
      method: 'POST',
      url: 'http://mongorestserviceapi.mybluemix.net/api/profile/retrieveProfile',
      data: dataString
    }

    $http(request).then(function(response){

      $scope.userUpdatedData = response.data;

      $scope.basicuserprofile ={
        username:$scope.userUpdatedData.username,
        name:$scope.userUpdatedData.name,
        email:$scope.userUpdatedData.email,
        phonenumber:$scope.userUpdatedData.phonenumber,
        password:$scope.userUpdatedData.password,
        statuses:true
      }

    }),function(data){
      console.log(data)
    }
  }

  $scope.saveBasicInformation = function(data){
    console.log(data);
    //var usname = $rootScope.usernames;
    //
    var request = {
      method:'POST',
      url: 'http://mongorestserviceapi.mybluemix.net/api/profile/updateProfile',
      data : data
    }

    $http(request).then(function(response){

      console.log(response.data)

      $scope.userUpdatedData = response.data;

      $scope.basicuserprofile ={
        username:$scope.userUpdatedData.username,
        name:$scope.userUpdatedData.name,
        email:$scope.userUpdatedData.email,
        phonenumber:$scope.userUpdatedData.phonenumber,
        password:$scope.userUpdatedData.password,
        statuses:true
      }

      angular.element(document.getElementById('saveButton'))[0].disabled = true;
    }),function(data){
      console.log(data)
    }
  }

  $scope.$on('$ionicView.afterEnter', function(){
    // Any thing you can think of
    console.log("After enter in Basic profile entered home page..");

    $scope.retrieveProfile();

    console.log('hi')
    console.log($scope.userUpdatedData)
    console.log('hello')

    angular.element(document.getElementById('saveButton'))[0].disabled = true;

  });
})
