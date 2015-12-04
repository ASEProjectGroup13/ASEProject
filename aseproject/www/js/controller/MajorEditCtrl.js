/**
 * Created by Lema Chowdary on 11/11/2015.
 */

myapp.controller('MajorEditCtrl', function($scope, $http, $state,$rootScope){

  $scope.itemSelected = [];




  $scope.updateMajor = function(data){

    var urlTesting=URLRetriever.getInstance()
<<<<<<< HEAD

    console.log(data);
    console.log('printing data');
=======
>>>>>>> origin/master
    var base=urlTesting.urlString()
    var req = {

      method: 'POST',
<<<<<<< HEAD
      url: base+'/majorinfo/updatemajor',
=======
      url: base+'/api/majorinfo/updatemajor',
>>>>>>> origin/master
      data: data
    };

    $http(req).then(function (response) {
      console.log(response + "response received from admin edit controller");

<<<<<<< HEAD
      var statusing = response.data;
      console.log(statusing)

      $state.go('majorhome');
=======
      var statusing = response.data.status;
      console.log(statusing)

      if (statusing == 'success') {
        console.log("in success state")
        $state.go('adminhome')
      } else {
        console.log("in failure state")
        //$state.go('signup')
      }
>>>>>>> origin/master
    })
  }

  console.log("items selected is printed above");


  $scope.$on('$ionicView.afterEnter', function(){
    // Any thing you can think of
<<<<<<< HEAD
    console.log("After enter in major edit page..");

    $scope.majorSelected  = $rootScope.itemsMajor;

    $scope.majordata ={
      majorid:$scope.majorSelected.majorid,
      majorname:$scope.majorSelected.majorname
    }
    console.log($scope.majorSelected);
=======
    console.log("After enter in admin home page..");

    $scope.itemSelected  = $rootScope.items;

    $scope.majordata ={
      majorid:$scope.itemSelected.majorid,
      majorname:$scope.itemSelected.majorname,
      department:$scope.itemSelected.department,

    }
    console.log($scope.itemSelected);
>>>>>>> origin/master


  });
})
