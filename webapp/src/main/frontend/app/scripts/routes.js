define([], function() {

    'use strict';
    return {
        defaultRoutePath: '/',
        routes: {
            '/': {
                templateUrl: 'views/register.html',
                controller: 'RegisterCtrl'
            }
            /* ===== yeoman hook ===== */
            /* Do not remove these commented lines! Needed for auto-generation */
        }
    };

});
