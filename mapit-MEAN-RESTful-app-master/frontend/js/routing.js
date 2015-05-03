var routing = angular.module('myApp.routing', []);

routing.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            //login panel
        when('/login', {
                templateUrl: 'partials/login.html',
                controller: 'LoginCtrl'
            }).
            //sign-up panel
        when('/sign-up', {
                templateUrl: 'partials/sign-up.html',
                controller: 'SignUpCtrl'
            }).
            //control panel
        when('/panel', {
            templateUrl: 'partials/panel.html',
            controller: 'PanelCtrl'
        }).
		when('/panel2', {
            templateUrl: 'partials/panel2.html',
            controller: 'PanelCtrl'
        }).
		when('/panel3', {
            templateUrl: 'partials/panel3.html',
            controller: 'PanelCtrl'
        }).
		
        otherwise({
            redirectTo: '/login'
        });
    }
]);
