define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.directive('navbar', function() {
        return {
            restrict: 'E',
            templateUrl: 'views/header.html',
            controller: 'NavbarCtrl',
            controllerAs: 'ctrl'
        }
    });

});