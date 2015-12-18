/**
 * Created by Venu on 12/15/15.
 */
myapp.controller('RecommendCoursesPageCtrl', function ($state,$scope,$rootScope,$http) {

  $scope.naigateToRecommendations = function(){
    $scope.coursesForPlanned = [];
    $state.go('profile.plannedCourses');
  }

  $scope.$on('$ionicView.afterEnter', function(){
    // Any thing you can think of
    $scope.majorRecommended = $rootScope.majorsname;
    $scope.coursesRecommended = $rootScope.recommendedCourses;
    console.log('$scope.majorRecommended');
    console.log('$scope.coursesRecommended');
  });
});
