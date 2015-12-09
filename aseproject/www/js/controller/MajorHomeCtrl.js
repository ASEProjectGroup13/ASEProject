/**
 * Created by Lema Chowdary on 11/10/2015.
 */

myapp.controller('MajorHomeCtrl', function($scope,$http,$state,$rootScope){

    $scope.data={
      showDelete: false,
      showReorder: false
    };

    $scope.retrieveMajorsHome= function () {
      var urlTesting= URLRetriever.getInstance()
      var base= urlTesting.urlString()
      console.log("inside MajorCtrl");

      var req= {
        method: 'GET',
        url: base+'/majorinfo/majors'
      };

      $scope.ttttt=[];
      $http(req).then(function (response){
        $scope.ttttt=response.data;
        console.log($scope.ttttt);
      })
    };

    $scope.moveItemMajor= function(item,fromIndex,toIndex){
    $scope.ttttt.splice(fromIndex,1);
      $scope.ttttt.splice(toIndex,0,item);
    };

    $scope.onItemMajorDelete= function(item,index){

      var urlTesting=URLRetriever.getInstance()
      var base=urlTesting.urlString()
      console.log("inside Major delete")

      var req = {
        method:'POST',
        url:base+'/majorinfo/deletemajor',
        data:item
      }

      $http(req).then(function(response){
        $scope.ttttt.splice($scope.ttttt.indexOf(item),1);
      })
      console.log(item);
    };

    $scope.$on('$ionicView.afterEnter', function(){
      //anything you can think of
      console.log("After opening majors page");
      $scope.retrieveMajorsHome();
    });

    $scope.createMajors= function(){
      console.log("inside create majors");
      $state.go('majorcreate')
    };

    $scope.viewMajorDetails= function(data){
      $rootScope.itemsMajor=data;
      $state.go('majoredit');
    }

  $scope.navigatingToHome = function(){
    $state.go('adminhome')
  }
  })
