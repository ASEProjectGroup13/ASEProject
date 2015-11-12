/**
 * Created by Venu on 11/7/15.
 */
myapp.controller('ProfileController', function($scope, $state, $http, $ionicSideMenuDelegate){

  $scope.toggleLeft = function() {
    $ionicSideMenuDelegate.toggleLeft();
  };

})
