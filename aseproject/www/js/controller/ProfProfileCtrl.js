/**
 * Created by Venu on 12/15/15.
 */

myapp.controller('ProfProfileCtrl', function($scope,$state,$rootScope,$http){

  $scope.retrieveProfessordetails = function(username){

    console.log('inside the method');
    console.log(username);

    var urlfactory = URLRetriever.getInstance();

    var jsonData = '{"username":"'+username+'"}';

    var request  = {

      url:urlfactory.urlString()+'/profile/retrieveprofessor',
      method:'POST',
      data:jsonData
    }



    $http(request).then(function(response){
      console.log('response received from REST');
      $scope.professorsDetails = response.data;
      $scope.profcoursesteach = response.data.coursesteach;
    });

  };

  $scope.$on('$ionicView.afterEnter', function(){
    // Any thing you can think of
    console.log('inside the professors profile page');

    var username = $rootScope.usernames;

    console.log('printing username');
    console.log(username);

    $scope.retrieveProfessordetails(username);



  });


});
