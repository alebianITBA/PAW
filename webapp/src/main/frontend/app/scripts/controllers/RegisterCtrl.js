'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.controller(
        'RegisterCtrl',
        ['$scope', 'UserService', 'SessionService', '$location', '$timeout',
        function($scope, UserService, SessionService, $location, $timeout) {
            this.user = {};
            this.constants = connectOn.constants;
            var that = this;

            this.showError = false;
            this.errorCode = undefined;

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
                UserService.createUser(that.user)
                    .then(function (response) {
                        if (response.status === 201) {
                            that.redirect = true;
                            SessionService.login(that.user.email, that.user.password);
                        }
                    })
                    .catch(function(response) {
                        if (response.status === 400) {
                            that.showError = true;
                            that.errorCode = 'ERROR_' + response.data.code;
                            $timeout(function() {
                                that.showError = false;
                            }, 3000);
                        }
                    });
            };

        }]
    );
});
