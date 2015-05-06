var app = angular.module('myApp', ['ngRoute', 'ngCookies', 'myApp.authenticationService', 'myApp.googleMapService']);


app.run(function($http, $rootScope, $scope, $location, $cookieStore, AuthenticationService) {

    //We check if the user is logged in.
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
    }
});

app.controller('headerCtrl', function($scope, $rootScope, $location, $http, AuthenticationService, GoogleMapService) {

        $scope.redirect = function() {
            if ($rootScope.loggedIn()) {
                window.location.href = 'http://localhost:3000';
            } else {
                AuthenticationService.ClearCredentials();
                window.location.href = 'http://localhost:3000/driverHomePage.html';
            }
        }

        $scope.check = function() {
            if ($rootScope.loggedIn()) {
                return "Sign Out";
            } else {
                return "Login";
            }
        }


    }
]);