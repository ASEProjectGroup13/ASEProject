/**
 * Created by Venu on 10/20/15.
 */

myapp.controller('AdminEditCtrl', function($scope, $http, $state,$rootScope){

  $scope.itemSelected = [];

  $scope.retrieveProfs = function () {


    var request = {

      method: 'GET',
      url: 'http://mongorestserviceapi.mybluemix.net/api/professors/retrieve'
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
      url: 'http://localhost:8080/com.umkc.rest/api/courseinfo/updatecourse',
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

    $scope.retrieveProfs();

    $scope.coursedata ={
      courseid:$scope.itemSelected.courseid,
      coursename:$scope.itemSelected.coursename,
      department:$scope.itemSelected.department,

      specialization:$scope.itemSelected.specialization,
      professor:$scope.itemSelected.professor,
      coursedescription:$scope.itemSelected.description
    }
    console.log($scope.itemSelected);
   // $scope.retrieveSelectedCourse(itemSelected);


  });
})
