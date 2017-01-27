'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.controller(
        'NavbarCtrl',
        ['$scope', '$http', '$location', 'SessionService', '$timeout',
        function($scope, $http, $location, SessionService, $timeout) {
            var that = this;
            this.constants = connectOn.constants;
            this.user = {};
            this.login = {};
            $scope.logged = SessionService.isLogged();

            this.showError = SessionService.showLoginError;
            this.errorCode = SessionService.loginErrorCode;

            // Subscribe to the user session service
            this.canRedirect = false;
            var updateUserInfo = function() {
                var updatedUserData = SessionService.loggedUser();
                that.user.id = updatedUserData.id;
                return true;
            };
            var onLogin = function() {
                $scope.logged = true;
                updateUserInfo();
                if (that.canRedirect) {
                    // This is needed in case we are logging from register
                    $location.path(that.constants.PATH_INDEX);
                }
                return true;
            };
            var onLogInError = function(error) {
                that.showError = true;
                that.errorCode = error;
                $timeout(function() {
                    that.showError = false;
                }, 3000);
                return true;
            };
            var onLogout = function() {
                $scope.logged = false;
                $location.path(that.constants.PATH_ROOT);
                that.login.email = null;
                that.login.password = null;
                return true;
            };
            var onUserUpdate = function() {
                updateUserInfo();
                return true;
            };
            SessionService.subscribe(onLogin, onLogout, onUserUpdate, 'NavbarCtrl', onLogInError);

            this.redirect = function(path) {
                if ($scope.logged) {
                    $location.path(path);
                } else {
                    $location.path(this.constants.PATH_ROOT);
                }
            };

            if (!$scope.logged && $location.$$path !== that.constants.PATH_ROOT) {
                $location.path(that.constants.PATH_ROOT);
            } else if ($scope.logged && ($location.$$path === '/' || $location.$$path === '/onboarding')) {
                $location.path(that.constants.PATH_INDEX);
            } else {
                updateUserInfo();
            }

            this.login = function() {
                that.canRedirect = true;
                SessionService.login(that.login.email, that.login.password);
            };

            this.logout = function() {
                SessionService.logout();
            };

        }]
    );
});
