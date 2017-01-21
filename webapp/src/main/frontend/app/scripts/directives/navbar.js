'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.directive(
        'navbar',
        function() {
            return {
                restrict: 'E',
                templateUrl: 'views/header.html',
                controller: 'NavbarCtrl',
                controllerAs: 'ctrl'
            };
        }
    );

});