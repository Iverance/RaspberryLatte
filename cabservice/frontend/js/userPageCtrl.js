var userControl = angular.module('myApp.userCtrl', ['ngRoute', 'ngCookies', 'myApp.authenticationService', 'myApp.googleMapService']);


userControl.run(function($http, $rootScope, $location, $cookieStore, AuthenticationService) {

    //We check if the user is logged in.
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
    }

});

userControl.controller('UserCtrl', function($scope, $rootScope, $location, $http, AuthenticationService, GoogleMapService) {

    $scope.rideDone = function() {

        //pass data to DB

        //clear Markers
        GoogleMapService.clearMarkers();
    };
    $scope.testing2 = function() {
        GoogleMapService.calcRoute();
    };

    $scope.testing = function() {
        GoogleMapService.addRoutes();
    };
    //The scope logout function
    $scope.logout = function() {

        GoogleMapService.clearMarkers();

        //we clear the credentials from storage
        AuthenticationService.ClearCredentials();

        //we relocate to login page
        window.location.href = 'http://localhost:3000';
    };

    $scope.editProfile = function() {

        AuthenticationService.Login($scope.username, $scope.password,
            function(response) {

                console.log("start update with "+$scope.username+$scope.password);
                
                if (response.success) {

                    AuthenticationService.Update($scope.username, $scope.newpassword,
                        function(response) {

                            if (response.success) {

                                AuthenticationService.SetCredentials($scope.username, $scope.newpassword, "rider");
                                console.log("update success");
                                window.location.href = 'http://localhost:3000/userHomePage.html';

                            }
                        });

                } else {

                    //we show an error
                    $scope.error = response.message;
                    console.log($scope.error);

                }
            });
    };

    //Called when adding a message to the map
    $scope.sendMessage = function() {

        //we hide the error message if there was one
        $scope.showErrorMessage = false;

        //We need to set a marker!
        if (!GoogleMapService.isMarkerSet()) {
            $scope.errorMessage = "Please set a marker first";
            $scope.showErrorMessage = true;
        } else {

            //we post to the api
            $http.post('/api/locations', {
                message: $scope.message,
                longitude: GoogleMapService.getLocation().longitude,
                latitude: GoogleMapService.getLocation().latitude
            }).success(function(response) {
                $scope.message = "";
                $scope.successMessage = "You mapped it!";
                //   $scope.showSuccessMessage = true;
                GoogleMapService.refreshLocations();
            });
            $location.path('panel3');
        }
    };
    $scope.sendMessageRider = function() {

        //we hide the error message if there was one
        $scope.showErrorMessage = false;

        //We need to set a marker!
        if (!GoogleMapService.isMarkerSet()) {
            $scope.errorMessage = "Please set a marker first";
            $scope.showErrorMessage = true;
        } else {

            //we post to the api
            $http.post('/api/locations', {
                message: $scope.message,
                longitude: GoogleMapService.getLocation().longitude,
                latitude: GoogleMapService.getLocation().latitude
            }).success(function(response) {
                $scope.message = "";
                $scope.successMessage = "You mapped it!";
                //   $scope.showSuccessMessage = true;
                GoogleMapService.refreshLocations();
            });
            $location.path('panel3');
        }
    };

    $scope.sendMessageDriver = function() {

        //we hide the error message if there was one
        $scope.showErrorMessage = false;

        //We need to set a marker!
        if (!GoogleMapService.isMarkerSet()) {
            $scope.errorMessage = "Please set a marker first";
            $scope.showErrorMessage = true;
        } else {

            //we post to the api
            $http.post('/api/locations', {
                message: "Driver Currently here",
                longitude: GoogleMapService.getLocation().longitude,
                latitude: GoogleMapService.getLocation().latitude
            }).success(function(response) {
                $scope.message = "Driver Currently here";
                $scope.successMessage = "Location received";
                $scope.showSuccessMessage = true;
                GoogleMapService.refreshLocations();
            });
        }
    };

    //scope function to delete a marker
    $scope.deleteMessage = function() {

        //we get the location object
        var csm = GoogleMapService.getSelectedLocation();

        //we delete it using its id
        $http.delete('/api/locations/' + csm.id).success(function() {
            $scope.successMessage = "Deleted!";
            $scope.showSuccessMessage = true;
            GoogleMapService.refreshLocations();
        });
    };

    $scope.invoice = {
        records: [{
            num: 1,
            location: 'SJSU',
            destination: 'SFO Airport',
            driver: 'Jonny',
            date: 'Now'
        }]
    };

    //listen for allow event
    $rootScope.$on('allow', function() {
        $scope.$apply(function() {
            $scope.showDeleteButton = true;

        });
    });

    //listen for disallow event
    $rootScope.$on('disallow', function() {
        $scope.$apply(function() {
            $scope.showDeleteButton = false;

        });

    });

    //listen for hideAllMessages event
    $rootScope.$on('hideAllMessages', function() {
        $scope.$apply(function() {
            $scope.showErrorMessage = false;
            $scope.showSuccessMessage = false;

        });

    });

});