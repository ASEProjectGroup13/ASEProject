/**
 * Created by Venu on 10/20/15.
 */

myapp.controller('AdminEditCtrl', function($scope, $http, $state,$rootScope){

  $scope.itemSelected = [];

  $scope.retrievedProfMajor = [];

  $scope.retrieveAdminMajors = function () {

    var baseURLString = URLRetriever.getInstance()

    var request = {
      method: 'GET',
      url:baseURLString.urlString()+'/majorinfo/majors'
    }
    $http(request).then(function(response){

      console.log(response.data);

      $scope.retrievedProfMajor = response.data;
    });
  }

  $scope.retrieveAdminProfs = function () {


    var request = {

      method: 'GET',
      url: 'http://mongorestase.mybluemix.net/api/professors/retrieve'
    }



    $http(request).then(function (response) {
      console.log(response.data);
      console.log("response received from data");

      $scope.professData = response.data;

      console.log("After entered into Admin edit Controller..");

    })
  }

  $scope.updateCourse = function(data){

    var req = {
      method: 'POST',
      url: 'http://mongorestase.mybluemix.net/api/courseinfo/updatecourse',
      data: data
    };

    $http(req).then(function (response) {
      console.log(response + "response received from admin edit controller");

      var statusing = response.data.status;
      console.log(statusing)

      if (statusing == 'success') {
        console.log("in success state")
        $state.go('adminhome')
      } else {
        console.log("in failure state")
        //$state.go('signup')
      }
    })
  }

  console.log("items selected is printed above");

  //$scope.retrieveSelectedCourse = function(itemSelected){
  //
  //}
  $scope.$on('$ionicView.afterEnter', function(){
    // Any thing you can think of
    console.log("After enter in admin home page..");

    $scope.itemSelected  = $rootScope.items;

    $scope.retrieveAdminProfs();

    $scope.retrieveAdminMajors();

    console.log('printing specialization');
    console.log($scope.itemSelected);
    console.log($scope.itemSelected.professor);

    $scope.coursedata ={
      courseid:$scope.itemSelected.courseid,
      coursename:$scope.itemSelected.coursename,
      cdescription:$scope.itemSelected.cdescription,


      specialization:$scope.itemSelected.specialization,
      professor:$scope.itemSelected.professor.name,
      cdescription:$scope.itemSelected.description
    }
    console.log($scope.itemSelected);
   // $scope.retrieveSelectedCourse(itemSelected);


  });

  $scope.navigateToHome = function(){
    $state.go('adminhome');
  }
})
