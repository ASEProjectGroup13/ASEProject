/**
 * Created by Lema Chowdary on 11/11/2015.
 */

myapp.controller('MajorEditCtrl', function($scope, $http, $state,$rootScope){

  $scope.itemSelected = [];




  $scope.updateMajor = function(data){

    var urlTesting=URLRetriever.getInstance()

    console.log(data);
    console.log('printing data');
    var base=urlTesting.urlString()
    var req = {

      method: 'POST',
      url: base+'/majorinfo/updatemajor',
      data: data
    };

    $http(req).then(function (response) {
      console.log(response + "response received from admin edit controller");

      var statusing = response.data;
      console.log(statusing)

      $state.go('majorhome');
    })
  }

  console.log("items selected is printed above");


  $scope.$on('$ionicView.afterEnter', function(){
    // Any thing you can think of
    console.log("After enter in major edit page..");

    $scope.majorSelected  = $rootScope.itemsMajor;

    $scope.majordata ={
      majorid:$scope.majorSelected.majorid,
      majorname:$scope.majorSelected.majorname
    }
    console.log($scope.majorSelected);


  });
})
