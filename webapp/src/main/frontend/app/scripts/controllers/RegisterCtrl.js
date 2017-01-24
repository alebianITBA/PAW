'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.controller(
        'RegisterCtrl',
        ['$scope', 'UserService', 'SessionService', '$location', '$timeout',
        function($scope, UserService, SessionService, $location, $timeout) {
            this.user = {};
            this.constants = connectOn.constants;
            var that = this;

            // Subscribe to the user session service
            this.redirect = false;
            var onUserUpdate = function() {
                if (that.redirect) {
                    $location.path('/onboarding');
                }
                return true;
            };
            SessionService.subscribe(SessionService.doNothing, SessionService.doNothing, onUserUpdate, 'RegisterCtrl');

            this.register = function() {
                UserService.createUser(that.user).then(function (response) {
                    if (response.status === 201) {
                        that.redirect = true;
                        SessionService.login(that.user.email, that.user.password);
                    }
                });
            };

        }]
    );
});
