'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.controller(
        'NavbarCtrl',
        ['$scope', '$http', '$location', 'SessionService', '$timeout',
        function($scope, $http, $location, SessionService, $timeout) {
            var that = this;
            this.constants = connectOn.constants;
            this.login = {};
            this.user = {};
            $scope.logged = SessionService.isLogged();

            this.updateBar = function() {
                $timeout(function() {
                    if (!SessionService.isLogged()) {
                        that.updateBar();
                    } else {
                        $scope.logged = true;
                    }
                }, 1000);
            };
            this.updateBar();

            this.redirect = function(path) {
                if ($scope.logged) {
                    $location.path(path);
                } else {
                    $location.path(this.constants.PATH_ROOT);
                }
            };

            if ($scope.logged) {
                that.user = SessionService.loggedUser();
            } else {
                if ($location.$$path !== that.constants.PATH_ROOT) {
                    $location.path(that.constants.PATH_ROOT);
                }
            }

            this.login = function() {
                SessionService.login(that.login.email, that.login.password);
                $timeout(function() {
                    if (!SessionService.isLogged()) {
                        login();
                    } else {
                        $scope.logged = true;
                        $location.path(that.constants.PATH_INDEX);
                    }
                }, 1000);
            };

            this.logout = function() {
                SessionService.logout();
                $scope.logged = false;
                $location.path(this.constants.PATH_ROOT);
            };

        }]
    );
});