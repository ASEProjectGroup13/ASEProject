/**
 * Created by Venu on 11/9/15.
 */
myapp.controller('ProfProfileUpdateCtrl', function($state,$scope,$http, $rootScope){

  $scope.profUserUpdatedData ='';

  var username;

  $scope.updateProfLoadData =function(){

    $scope.basicprofuserprofile ={
      username:$scope.profUserUpdatedData.username,
      name:$scope.profUserUpdatedData.name,
      email:$scope.profUserUpdatedData.email,
      phonenumber:$scope.profUserUpdatedData.phonenumber,
      password:$scope.profUserUpdatedData.password,
      statuses:false
    }

    angular.element(document.getElementById('saveButton'))[0].disabled = false;
  }

  $scope.retrieveProfessorProfile = function(){

    console.log('Hello i am in basic profile control');
    console.log($rootScope.usernames)

    var usname = $rootScope.usernames;

    username = usname;

    var dataString = '{"username":"'+usname+'"}'

    var request = {
      method: 'POST',
      url: 'http://mongorestase.mybluemix.net/api/profile/retrieveProfile',
      data: dataString
    }

    $http(request).then(function(response){

      console.log(response.data);

      $scope.profUserUpdatedData = response.data;

      $scope.basicprofuserprofile ={
        username:$scope.profUserUpdatedData.username,
        name:$scope.profUserUpdatedData.name,
        email:$scope.profUserUpdatedData.email,
        phonenumber:$scope.profUserUpdatedData.phonenumber,
        password:$scope.profUserUpdatedData.password,
        statuses:true
      }

    }),function(data){
      console.log(data)
    }
  }

  $scope.saveBasicProfInformation = function(data){
    console.log(data);
    //var usname = $rootScope.usernames;
    //
    var request = {
      method:'POST',
      url: 'http://mongorestase.mybluemix.net/api/profile/updateProfile',
      data : data
    }

    $http(request).then(function(response){

      console.log(response.data)

      $scope.profUserUpdatedData = response.data;

      $scope.basicprofuserprofile ={
        username:$scope.profUserUpdatedData.username,
        name:$scope.profUserUpdatedData.name,
        email:$scope.profUserUpdatedData.email,
        phonenumber:$scope.profUserUpdatedData.phonenumber,
        password:$scope.profUserUpdatedData.password,
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

    $scope.retrieveProfessorProfile();

    console.log('hi')
    console.log($scope.profUserUpdatedData)
    console.log('hello')

    angular.element(document.getElementById('saveButton'))[0].disabled = true;

  });

  $scope.navigateToHome = function(){
    $rootScope.usernames = username;
    $state.go('homepage.coursespage');
  }
})
