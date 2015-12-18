angular.module('app.routes', [])

.config(function($stateProvider, $urlRouterProvider) {

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider



    .state('login', {
      url: '/login',
      templateUrl: 'templates/login.html',
      controller: 'loginCtrl'
    })





    .state('homePage', {
      url: '/home',
      templateUrl: 'templates/homePage.html',
      controller: 'homePageCtrl'
    })





    .state('signup', {
      url: '/signup',
      templateUrl: 'templates/signup.html',
      controller: 'signupCtrl'
    })





    .state('homepage.professors', {
      url: '/professor',
      views: {
        'tab4': {
          templateUrl: 'templates/professors.html',
          controller: 'professorsCtrl'
        }
      }
    })





    .state('homepage.coursesPage', {
      url: '/courses',
      views: {
        'tab5': {
          templateUrl: 'templates/coursesPage.html',
          controller: 'coursesPageCtrl'
        }
      }
    })





    .state('profilePage', {
      url: '/profile',
      templateUrl: 'templates/profilePage.html',
      controller: 'profilePageCtrl'
    })




    .state('homepage', {
      url: '/home',
      abstract:true,
      templateUrl: 'templates/homepage.html'
    })




    .state('professorRatings', {
      url: '/page14',
      templateUrl: 'templates/feedback.html',
      controller: 'professorRatingsCtrl'
    })





    .state('homepage.professorHome.ratings', {
      url: '/ratings',
      views: {
        'tab7': {
          templateUrl: 'templates/ratings.html',
          controller: 'ratingsCtrl'
        }
      }
    })





    .state('homepage.professorHome.feedback', {
      url: '/feedback',
      views: {
        'tab8': {
          templateUrl: 'templates/feedback.html',
          controller: 'feedbackCtrl'
        }
      }
    })





    .state('homepage.professorHome.homePage2', {
      url: '/home',
      views: {
        'tab9': {
          templateUrl: 'templates/homePage2.html',
          controller: 'homePage2Ctrl'
        }
      }
    })




    .state('homepage.professorHome', {
      url: '/professortab',
      abstract:true,
      templateUrl: 'templates/professorHome.html'
    })

    ;

  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/login');

});
