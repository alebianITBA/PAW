'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.directive(
        'navbar',
        function() {
            return {
                restrict: 'E',
                templateUrl: 'grupo5/views/header.html'
            };
        }
    );

});
