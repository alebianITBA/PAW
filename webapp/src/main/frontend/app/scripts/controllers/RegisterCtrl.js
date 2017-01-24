'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.controller(
        'RegisterCtrl',
        ['$scope', 'UserService', 'SessionService', '$location', '$timeout',
        function($scope, UserService, SessionService, $location, $timeout) {
            this.user = {};
            this.constants = connectOn.constants;
            var that = this;

            var login = function() {
                SessionService.login(that.user.email, that.user.password);
                $timeout(function() {
                    if (!SessionService.isLogged()) {
                        login();
                    } else {
                        $scope.logged = true;
                        $location.path('/onboarding');
                    }
                }, 1000);
            };

            this.register = function() {
                var usr = this.user;
                UserService.createUser(usr).then(function (response) {
                    if (response.status === 201) {
                        login();
                    }
                });
            };

        }]
    );
});
