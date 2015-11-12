/**
 * Created by Lema Chowdary on 11/11/2015.
 */

myapp.controller('MajorEditCtrl', function($scope, $http, $state,$rootScope){

  $scope.itemSelected = [];




  $scope.updateMajor = function(data){

    var urlTesting=URLRetriever.getInstance()
    var base=urlTesting.urlString()
    var req = {

      method: 'POST',
      url: base+'/api/majorinfo/updatemajor',
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


  $scope.$on('$ionicView.afterEnter', function(){
    // Any thing you can think of
    console.log("After enter in admin home page..");

    $scope.itemSelected  = $rootScope.items;

    $scope.majordata ={
      majorid:$scope.itemSelected.majorid,
      majorname:$scope.itemSelected.majorname,
      department:$scope.itemSelected.department,

    }
    console.log($scope.itemSelected);


  });
})
