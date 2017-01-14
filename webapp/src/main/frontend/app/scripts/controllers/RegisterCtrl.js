define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller('RegisterCtrl', ['$scope', 'UserService', function($scope, UserService) {
        this.user = {};

        this.register = function() {
            return UserService.createUser(this.user);
        };

    }]);
});
