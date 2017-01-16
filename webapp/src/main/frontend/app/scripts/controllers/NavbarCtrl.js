define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller('NavbarCtrl', ['$scope', '$http', 'localStorageService', '$location', 'UserService',
                            function($scope, $http, localStorageService, $location, UserService) {

        this.constants = connectOn.constants;
        this.login = {};
        $scope.logged = localStorageService.get(this.constants.TOKEN_KEY) !== null;

        this.redirect = function(path) {
            if ($scope.logged) {
                $location.path(path);
            } else {
                $location.path(this.constants.PATH_ROOT);
            }
        };

        if (!$scope.logged && $location.$$path !== this.constants.PATH_ROOT) {
            this.redirect(this.constants.PATH_ROOT);
        }

        this.login = function() {
            var that = this;
            UserService.login(that.login).then(function (response) {
                localStorageService.set(that.constants.TOKEN_KEY, response.data.token);
                localStorageService.set(that.constants.LOGGED_USER, response.data.user);
                $scope.logged = true;
                that.redirect(that.constants.PATH_INDEX);
            });
        };

        this.logout = function() {
            localStorageService.clearAll();
            $scope.logged = false;
            this.redirect(this.constants.PATH_ROOT);
        };

    }]);
});