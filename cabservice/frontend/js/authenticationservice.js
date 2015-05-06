'use strict';
//THANKS TO http://jasonwatmore.com/post/2014/05/26/AngularJS-Basic-HTTP-Authentication-Example
//For his authenticationservice
var auth = angular.module('myApp.authenticationService', [])
    .factory('AuthenticationService', [
        'Base64',
        '$http',
        '$cookieStore',
        '$rootScope',
        '$timeout',
        // 'GoogleMapService',
        // function(Base64, $http, $cookieStore, $rootScope, $timeout, GoogleMapService) {
        function(Base64, $http, $cookieStore, $rootScope, $timeout) {
            var service = {};

            //The login function
            service.Login = function(username, password, callback) {

                $http.post('/api/authenticate', {
                        username: username,
                        password: password
                    })
                    .success(function(response) {

                        //We refresh the locations to change
                        //the color of our own markers to blue

                        // GoogleMapService.refreshLocations();

                        callback(response);
                    });

            };

            //The sign up function
            service.SignUp = function(username, password, callback) {

                $http.post('/api/users', {
                        username: username,
                        password: password
                    })
                    .success(function(response) {

                        //We refresh the locations to change
                        //the color of our own markers to blue

                        // GoogleMapService.refreshLocations();

                        callback(response);
                    })
                    .error(function(response) {
                        callback(response);
                    });
            };

            //The login function
            service.Update = function(username, newpassword, callback) {

                $http.post('/api/users/update', {
                        username: username,
                        password: newpassword
                    })
                    .success(function(response) {

                        callback(response);
                    })
                    .error(function(response) {
                        callback(response);
                    });

            };

            //we add the loggedIn method to the $rootScope
            $rootScope.loggedIn = function() {
                if ($rootScope.globals && $rootScope.globals.currentUser)
                    return true;
                return false;
            };

            //we add the getUsername method to the $rootScope
            $rootScope.getUsername = function() {
                if ($rootScope.loggedIn())
                    return $rootScope.globals.currentUser.username;
            };

            $rootScope.getState = function() {
                if ($rootScope.loggedIn())
                    return $rootScope.globals.currentUser.state;
            };

            service.SetCredentials = function(username, password, state) {
                var authdata = Base64.encode(username + ':' + password);

                $rootScope.globals = {
                    currentUser: {
                        state: state,
                        username: username,
                        authdata: authdata
                    }
                };

                $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line
                $cookieStore.put('globals', $rootScope.globals);
            };

            service.ClearCredentials = function() {
                // GoogleMapService.clearMarker();
                // GoogleMapService.refreshLocations();
                $rootScope.globals = {};
                $cookieStore.remove('globals');
                $http.defaults.headers.common.Authorization = 'Basic ';

            };


            return service;
        }
    ])

.factory('Base64', function() {
    /* jshint ignore:start */

    var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';

    return {
        encode: function(input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            do {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);

                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;

                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }

                output = output +
                    keyStr.charAt(enc1) +
                    keyStr.charAt(enc2) +
                    keyStr.charAt(enc3) +
                    keyStr.charAt(enc4);
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            } while (i < input.length);

            return output;
        },

        decode: function(input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
            var base64test = /[^A-Za-z0-9\+\/\=]/g;
            if (base64test.exec(input)) {
                window.alert("There were invalid base64 characters in the input text.\n" +
                    "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                    "Expect errors in decoding.");
            }
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

            do {
                enc1 = keyStr.indexOf(input.charAt(i++));
                enc2 = keyStr.indexOf(input.charAt(i++));
                enc3 = keyStr.indexOf(input.charAt(i++));
                enc4 = keyStr.indexOf(input.charAt(i++));

                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;

                output = output + String.fromCharCode(chr1);

                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }

                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";

            } while (i < input.length);

            return output;
        }
    };

    /* jshint ignore:end */
});