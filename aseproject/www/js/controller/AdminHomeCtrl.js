/**
 * Created by Venu on 10/19/15.
 */
myapp.controller('AdminHomeCtrl', function($scope, $http, $state, $rootScope){

  /** $scope.shouldShowDelete = true;
  $scope.shouldShowReorder = true;
  $scope.listCanSwipe = true; **/

  $scope.data = {
    showDelete: false,
    showReorder: false
  };
  $scope.retrieveCourses = function(){

    console.log("inside homectrl");

    var req = {
      method: 'GET',
      url: 'http://mongorestserviceapi.mybluemix.net/api/courseinfo/courses'

    };

    $scope.items = [];

    $http(req).then(function (response) {
      $scope.items = response.data;
      console.log($scope.items);
    })
  }

  $scope.share = function(data){
    alert("trying to share item"+ data.coursename);
  }

  $scope.edit = function(data){
    alert("trying to edit course item"+data.coursename);
  }

  $scope.moveItem = function(item, fromIndex, toIndex) {
    $scope.items.splice(fromIndex, 1);
    $scope.items.splice(toIndex, 0, item);
  };

  $scope.onItemDelete = function(item,index) {


    console.log("inside delete")

    var req = {
      method:'POST',
      url:'http://mongorestserviceapitest.mybluemix.net/api/courseinfo/deletecourse',
      data:item
    }

    $http(req).then(function(response){

      $scope.items.splice($scope.items.indexOf(item), 1);
    })

    console.log(item);

  };


  $scope.$on('$ionicView.afterEnter', function(){
    // Any thing you can think of
    console.log("After enter in admin home page..");
    $scope.retrieveCourses();
  });

  $scope.createCourses = function(){
    console.log("inside creation of course contrl");
    $state.go('admincreate')
  }
  $scope.viewDetails = function(data) {
    console.log("inside creation of course")
    $rootScope.items = data;
    console.log("inside view details")
    console.log($rootScope.items)
    $state.go('adminedit');

  }
})
