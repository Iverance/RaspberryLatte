var loginCtrl = angular.module('myApp.loginCtrl', ['ngRoute', 'ngCookies', 'myApp.authenticationService']);

loginCtrl.controller('LoginCtrl', ['$scope', '$rootScope', '$location', 'AuthenticationService',
    function($scope, $rootScope, $location, AuthenticationService ) {

        // reset login status
        AuthenticationService.ClearCredentials();



        // $scope.testing = function() {
        //     GoogleMapService.addMarker();
        // };



        //the scope login function
        $scope.login = function() {

            //Show the spinner
            $scope.dataLoading = true;

            //Log the user with the service
            AuthenticationService.Login($scope.username, $scope.password,
                function(response) {

                    if (response.success) {

                        //we set the credentials
                        AuthenticationService.SetCredentials($scope.username, $scope.password,"driver");

                        $scope.dataLoading = false;

                        console.log("success");
                        //we relocate to /panel
                        window.location.href = 'http://localhost:3000/driverHomePage.html';

                    } else {

                        //we show an error
                        $scope.error = response.message;
                        console.log("fail");

                        $scope.dataLoading = false;


                    }
                });
        };
        $scope.login2 = function() {

            //Show the spinner
            $scope.dataLoading = true;

            //Log the user with the service
            AuthenticationService.Login($scope.username, $scope.password,
                function(response) {

                    if (response.success) {

                        //we set the credentials
                        AuthenticationService.SetCredentials($scope.username, $scope.password,"rider");

                        //we relocate to /panel2
                        window.location.href = 'http://localhost:3000/userHomePage.html';

                    } else {

                        //we show an error
                        $scope.error = response.message;

                        $scope.dataLoading = false;
                    }
                });
        };
    }
]);