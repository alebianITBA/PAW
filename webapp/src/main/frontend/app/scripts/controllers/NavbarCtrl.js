define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller('NavbarCtrl', ['$scope', '$http', 'localStorageService', function($scope, $http, localStorageService) {
        this.login = {};
        $scope.logged = localStorageService.get(connectOn.constants.TOKEN_KEY) !== null;

        this.login = function() {
            $http.post(connectOn.constants.API_V1_BASE_URL + '/login', { email: this.login.email, password: this.login.password })
                 .then(function (response) {
                     localStorageService.set(connectOn.constants.TOKEN_KEY, response.data.token);
                     $scope.logged = true;
                 });
        };

        this.logout = function() {
            localStorageService.clearAll();
            $scope.logged = false;
        };

    }]);
});