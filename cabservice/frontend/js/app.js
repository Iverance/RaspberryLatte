var app = angular.module('myApp', [
    'ngRoute',
    'ngCookies',
    'myApp.authenticationService',
    'myApp.loginCtrl',
    'myApp.signUpCtrl',
    'myApp.googleMapService',
    'myApp.userCtrl'
]).run(function($http, $rootScope, $location, $cookieStore, AuthenticationService) {

    //We check if the user is logged in.
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
    }

});