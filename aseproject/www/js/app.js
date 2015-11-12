// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
var myapp = angular.module('aseproject', ['ionic','ngCordova'])

  .run(function($ionicPlatform) {
    $ionicPlatform.ready(function() {
      // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
      // for form inputs)
      if(window.cordova && window.cordova.plugins.Keyboard) {
        cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      }
      if(window.StatusBar) {
        // org.apache.cordova.statusbar required
        StatusBar.styleDefault();
      }
    });
  })

  .config(function($stateProvider, $urlRouterProvider) {

    // Ionic uses AngularUI Router which uses the concept of states
    // Learn more here: https://github.com/angular-ui/ui-router
    // Set up the various states which the app can be in.
    // Each state's controller can be found in controllers.js
    $stateProvider

      .state('login', {
        url: '/login',
        templateUrl: 'templates/login.html',
        controller: "LoginCtrl"

      })
      .state('signup', {
        url: '/signup',
        templateUrl: 'templates/signup.html',
        controller: "SignupCtrl"

      })
      .state('adminhome', {
        url: '/adminhome',
        templateUrl: 'templates/AdminHome.html',
        controller: "AdminHomeCtrl"
      })
      .state('adminedit', {
        url: '/adminedit',
        templateUrl: 'templates/AdminEditCourse.html',
        controller: "AdminEditCtrl"
      })
      .state('admincreate', {
        url: '/admincreate',
        templateUrl: 'templates/AdminCreateHtml.html',
        controller: "AdminCreateCtrl"
      })
      .state('homepage.professors', {
        url: '/professor',
        views: {
          'professor': {
            templateUrl: 'templates/professors.html',
            controller: 'ProfessorsCtrl'
          }
        }
      })
      .state('homepage.coursespage', {
        url: '/coursespage',
        views: {
          'courses': {
            templateUrl: 'templates/coursesPage.html',
            controller: 'coursespagectrl'
          }
        }
      })
      .state('homepage.profilePage', {
        url: '/profilePage',
        views: {
          'profile': {
            templateUrl: 'templates/profilePage.html',
            controller: 'ProfilePageCtrl'
          }
        }
      })
      .state('homepage', {
        url: '/home',
        abstract:true,
        templateUrl: 'templates/homepage.html',
        controller: 'homectrl'
      })
      .state('professorRatings', {
        url: '/profileratings',
        templateUrl: 'templates/professorRatings.html',
        controller: 'professorRatingsCtrl'
      })


      .state('homepage.professorHome', {
        url: '/professortab',
        //abstract:true,
        templateUrl: 'templates/professorHome.html'
      })

      .state('profile', {
        url: "/profile",
        abstract: true,
        templateUrl: "templates/profileSideMenu.html"
      })
      .state('profile.home', {
        url: "/profilehome",
        views: {
          'appContent' :{
            templateUrl: "templates/profileHome.html",
            controller : "ProfileHomeController"
          }
        }
      })

      //States for profile information navigations
      .state('profile.basic', {
        url: "/profilebasic",
        views: {
          'appContent' :{
            templateUrl: "templates/basicProfilePage.html",
            controller : "ProfileHomeController"
          }
        }
      })
      .state('profile.courseCompleted', {
        url: "/profileCourseCompleted",
        views: {
          'appContent' :{
            templateUrl: "templates/coursesCompletedPage.html",
            controller : "CoursesCompletedController"
          }
        }
      })
      .state('profile.coursesEnrolled', {
        url: "/profileCoursesEnrolled",
        views: {
          'appContent' :{
            templateUrl: "templates/coursesEnrolledPage.html",
            controller : "CoursesEnrolledCtrl"
          }
        }
      })
      .state('profile.academicDetails', {
        url: "/profileAcademicDetails",
        views: {
          'appContent' :{
            templateUrl: "templates/PreviousAcademicDetailsPage.html",
            controller : "ProfileHomeController"
          }
        }
      })


    // if none of the above states are matched, use this as the fallback

    $urlRouterProvider.otherwise('/login');


  })


// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'


.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs).
    // The reason we default this to hidden is that native apps don't usually show an accessory bar, at
    // least on iOS. It's a dead giveaway that an app is using a Web View. However, it's sometimes
    // useful especially with forms, though we would prefer giving the user a little more room
    // to interact with the app.
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);
    }
    if (window.StatusBar) {
      // Set the statusbar to use the default style, tweak this to
      // remove the status bar on iOS or change it to use white instead of dark colors.
      StatusBar.styleDefault();
    }
  });
})

.directive("ionCart", function() {
  return {
    restrict : "E",
    templateUrl : "templates/profileItems.html",
    controller: 'ProfileItemsCtrl'
  }
})

  .directive("ionPurchase", function() {
    return {
      restrict : "E",
      template : "<h2>This is Ion Purchase</h2>"
    }
  });

//var allowCrossDomain = function(req, res, next) {
//  // Website you wish to allow to connect
//  res.setHeader('Access-Control-Allow-Origin', 'http://localhost');
//
//  // Request methods you wish to allow
//  res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
//
//  // Request headers you wish to allow
//  res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');
//
//  // Set to true if you need the website to include cookies in the requests sent
//  // to the API (e.g. in case you use sessions)
//  res.setHeader('Access-Control-Allow-Credentials', true);
//
//  // Pass to next layer of middleware
//  next();
//}
//
//.use(allowCrossDomain);
