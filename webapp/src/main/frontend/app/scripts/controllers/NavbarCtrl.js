define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller('NavbarCtrl', ['$scope', '$http', 'localStorageService', '$location', 'UserService',
                            function($scope, $http, localStorageService, $location, UserService) {
        this.login = {};
        $scope.logged = localStorageService.get(connectOn.constants.TOKEN_KEY) !== null;

        if (!$scope.logged && $location.$$path !== '/') {
            $location.path('/');
        }

        this.login = function() {
            UserService.login(this.login).then(function (response) {
                localStorageService.set(connectOn.constants.TOKEN_KEY, response.data.token);
                $scope.logged = true;
                $location.path('/index');
            });
        };

        this.logout = function() {
            localStorageService.clearAll();
            $scope.logged = false;
            $location.path('/');
        };

    }]);
});