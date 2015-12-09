/**
 * Created by Venu on 10/18/15.
 */

myapp.service('CourseData', function ($scope) {

  var sharedData = {};

  $scope.courses = function($http) {

    var req = {
      method: 'GET',
      url: 'http://localhost:8080/com.umkc.rest/api/mongo/courses'

    };

    $http(req).then(function (response) {
      console.log(response);
      this.sharedData = response.data;
     })
  }

  function set(data){
    this.sharedData=data;
  }
  function get(){
    return this.sharedData;
  }
  return{
    set: set,
    get: get
  }
})
