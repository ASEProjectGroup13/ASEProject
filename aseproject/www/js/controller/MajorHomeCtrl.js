/**
 * Created by Lema Chowdary on 11/10/2015.
 */

myapp.controller('MajorHomeCtrl', function($scope,$http,$state,$rootScope){

    $scope.data={
      showDelete: false,
      showReorder: false
    };

<<<<<<< HEAD
    $scope.retrieveMajorsHome= function () {
=======
    $scope.retrieveMajors= function () {
>>>>>>> origin/master
      var urlTesting= URLRetriever.getInstance()
      var base= urlTesting.urlString()
      console.log("inside MajorCtrl");

      var req= {
        method: 'GET',
        url: base+'/majorinfo/majors'
      };

<<<<<<< HEAD
      $scope.ttttt=[];
      $http(req).then(function (response){
        $scope.ttttt=response.data;
        console.log($scope.ttttt);
=======
      $scope.tttt=[];
      $http(req).then(function (response){
        $scope.tttt=response.data;
        console.log($scope.tttt);
>>>>>>> origin/master
      })
    };

    $scope.moveItemMajor= function(item,fromIndex,toIndex){
<<<<<<< HEAD
    $scope.ttttt.splice(fromIndex,1);
      $scope.ttttt.splice(toIndex,0,item);
=======
$scope.items.splice(fromIndex,1);
      $scope.items.splice(toIndex,0,item);
>>>>>>> origin/master
    };

    $scope.onItemMajorDelete= function(item,index){

      var urlTesting=URLRetriever.getInstance()
      var base=urlTesting.urlString()
      console.log("inside Major delete")

      var req = {
        method:'POST',
<<<<<<< HEAD
        url:base+'/majorinfo/deletemajor',
=======
        url:base+'/api/majorinfo/deletemajor',
>>>>>>> origin/master
        data:item
      }

      $http(req).then(function(response){
<<<<<<< HEAD
        $scope.ttttt.splice($scope.ttttt.indexOf(item),1);
      })
=======
        $scope.tttt.splice($scope.tttt.indexOf(item),1);
      })

>>>>>>> origin/master
      console.log(item);
    };

    $scope.$on('$ionicView.afterEnter', function(){
      //anything you can think of
      console.log("After opening majors page");
<<<<<<< HEAD
      $scope.retrieveMajorsHome();
=======
      $scope.retrieveMajors();
>>>>>>> origin/master
    });

    $scope.createMajors= function(){
      console.log("inside create majors");
      $state.go('majorcreate')
    };

    $scope.viewMajorDetails= function(data){
<<<<<<< HEAD
      $rootScope.itemsMajor=data;
      $state.go('majoredit');
    }

  $scope.navigatingToHome = function(){
    $state.go('adminhome')
  }
=======
      $rootScope.items=data;
      $state.go('majoredit');
    }
>>>>>>> origin/master
  })
