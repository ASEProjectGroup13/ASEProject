/**
 * Created by Lema Chowdary on 11/10/2015.
 */

myapp.controller('MajorHomeCtrl', function($scope,$http,$state,$rootScope){

    $scope.data={
      showDelete: false,
      showReorder: false
    };

    $scope.retrieveMajors= function () {
      var urlTesting= URLRetriever.getInstance()
      var base= urlTesting.urlString()
      console.log("inside MajorCtrl");

      var req= {
        method: 'GET',
        url: base+'/majorinfo/majors'
      };

      $scope.tttt=[];
      $http(req).then(function (response){
        $scope.tttt=response.data;
        console.log($scope.tttt);
      })
    };

    $scope.moveItemMajor= function(item,fromIndex,toIndex){
$scope.items.splice(fromIndex,1);
      $scope.items.splice(toIndex,0,item);
    };

    $scope.onItemMajorDelete= function(item,index){

      var urlTesting=URLRetriever.getInstance()
      var base=urlTesting.urlString()
      console.log("inside Major delete")

      var req = {
        method:'POST',
        url:base+'/api/majorinfo/deletemajor',
        data:item
      }

      $http(req).then(function(response){
        $scope.tttt.splice($scope.tttt.indexOf(item),1);
      })

      console.log(item);
    };

    $scope.$on('$ionicView.afterEnter', function(){
      //anything you can think of
      console.log("After opening majors page");
      $scope.retrieveMajors();
    });

    $scope.createMajors= function(){
      console.log("inside create majors");
      $state.go('majorcreate')
    };

    $scope.viewMajorDetails= function(data){
      $rootScope.items=data;
      $state.go('majoredit');
    }
  })
