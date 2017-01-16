define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller('UserCtrl', ['$scope', 'UserService', function($scope, UserService) {
        this.user = {};

        var that = this;
        UserService.show($routeParams.userId).then(function(result) {
            that.user = result.data;
        });
    }]);
});
