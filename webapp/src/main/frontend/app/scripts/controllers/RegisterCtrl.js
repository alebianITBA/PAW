'use strict';

define(['connectOn'], function(connectOn) {

    connectOn.controller('RegisterCtrl', function($scope) {
        this.user = {};

        this.register = function() {
            console.log(this.user);
        };
    });
});
