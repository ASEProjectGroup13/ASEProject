/**
 * Created by Bannu on 11/14/2015.
 */
myapp.controller('professorRatingsCtrl', function($state,$scope,$http, $rootScope){

    $scope.rate1=[
      {text: "Strongly Agree", value:5
      },
      {text: "Agree", value:4
      },
      {text: "Neutral", value: 3
      },
      {text: "Disagree", value:2
      },
      {text: "Strongly Disagree", value: 1
      }
    ];
    $scope.rate2=[
      {text: "Strongly Agree", value:5
      },
      {text: "Agree", value:4
      },
      {text: "Neutral", value: 3
      },
      {text: "Disagree", value:2
      },
      {text: "Strongly Disagree", value: 1
      }
    ];
    $scope.rate3=[
      {text: "Strongly Agree", value:5
      },
      {text: "Agree", value:4
      },
      {text: "Neutral", value: 3
      },
      {text: "Disagree", value:2
      },
      {text: "Strongly Disagree", value: 1
      }
    ];
    $scope.rate4=[
      {text: "Strongly Agree", value:5
      },
      {text: "Agree", value:4
      },
      {text: "Neutral", value: 3
      },
      {text: "Disagree", value:2
      },
      {text: "Strongly Disagree", value: 1
      }
    ];
    $scope.rate5=[
      {text: "Strongly Agree", value:5
      },
      {text: "Agree", value:4
      },
      {text: "Neutral", value: 3
      },
      {text: "Disagree", value:2
      },
      {text: "Strongly Disagree", value: 1
      }
    ];
    $scope.rate6=[
      {text: "Strongly Agree", value:5
      },
      {text: "Agree", value:4
      },
      {text: "Neutral", value: 3
      },
      {text: "Disagree", value:2
      },
      {text: "Strongly Disagree", value: 1
      }
    ];
    $scope.rate7=[
      {text: "Strongly Agree", value:5
      },
      {text: "Agree", value:4
      },
      {text: "Neutral", value: 3
      },
      {text: "Disagree", value:2
      },
      {text: "Strongly Disagree", value: 1
      }
    ];
    $scope.rate8=[
      {text: "Strongly Agree", value:5
      },
      {text: "Agree", value:4
      },
      {text: "Neutral", value: 3
      },
      {text: "Disagree", value:2
      },
      {text: "Strongly Disagree", value: 1
      }
    ];

  var tempArray = [];
  $scope.rating1 = function(item) {
    var ratings1 = item.value;
    tempArray.push(ratings1);
    console.log(ratings1);
  };

  $scope.rating2 = function(item) {
    var ratings2 = item.value;
    tempArray.push(ratings2);
    console.log(ratings2);
  };

  $scope.rating3 = function(item) {
    var ratings3 = item.value;
    tempArray.push(ratings3);
    console.log(ratings3);
  };

  $scope.rating4 = function(item) {
    var ratings4 = item.value;
    tempArray.push(ratings4);
    console.log(ratings4);
  };

  $scope.rating5 = function(item) {
    var ratings5 = item.value;
    tempArray.push(ratings5);
    console.log(ratings5);
  };

  $scope.rating6 = function(item) {
    var ratings6 = item.value;
    tempArray.push(ratings6);
    console.log(ratings6);
  };

  $scope.rating7 = function(item) {
    var ratings7 = item.value;
    tempArray.push(ratings7);
    console.log(ratings7);
  };

  $scope.rating8 = function(item) {
    var ratings8 = item.value;
    tempArray.push(ratings8);
    console.log(ratings8);
  };

  var tempvar = $rootScope.usernames;
  $scope.receiveFeedback = function(data) {

    var averagerating;
    var i =0;
    var total =0;
    for(i;i<tempArray.length;i++)
    {
      total = total + tempArray[i];
    }
    console.log(tempArray.length);
    console.log(total);
    averagerating = (total/8);
    var jsondata = '{"rating":"'+averagerating+'","username":"'+tempvar+'","courseid":"'+$scope.sendingCourseId+'"}';
    var req = {
    method: 'POST',
    url: 'http://mongorestase.mybluemix.net/api/courseinfo/updateratings',
    data: jsondata
    };
    console.log(jsondata);
    $http(req).then(function (response) {
      console.log(response + "Feedback recorded. Thanks for your response");

      var statusing = response.data.status;
      console.log(statusing)

      if (statusing == 'success') {
        console.log("in success state")
        //$state.go('studentCourseDetails')
      } else {
        console.log("in failure state")
        //$state.go('signup')
      }
    })
  }
  $scope.$on('$ionicView.afterEnter', function(){

    var temporaryData = $rootScope.ratingItem;

    $scope.sendingCourseId = temporaryData.courseid;

    var personName = $rootScope.usernames;


    console.log('inside Student Course Details');

    $scope.Feedback= {

      //courseid: temporaryData.courseid,
      coursename: temporaryData.coursename,
      professor: temporaryData.professor
    }
  });
});
