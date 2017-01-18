define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller(
        'RegisterCtrl',
        ['$scope', 'UserService', 'localStorageService', '$location',
        function($scope, UserService, localStorageService, $location) {
            this.user = {};

            this.register = function() {
                var usr = this.user;
                UserService.createUser(usr).then(function (response) {
                    UserService.login(usr).then(function (response) {
                        localStorageService.set(connectOn.constants.TOKEN_KEY, response.data.token);
                        $scope.logged = true;
                        $location.path('/index');
                    });
                });
            };

        }]
    );
});
