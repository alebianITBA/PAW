define([], function() {

    'use strict';
    return {
        defaultRoutePath: '/',
        routes: {
            '/': {
                templateUrl: 'views/register.html',
                controller: 'RegisterCtrl'
            },
            '/index': {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            },
            '/posts/:postId': {
                templateUrl: 'views/posts/show.html',
                controller: 'PostCtrl'
            },
            '/posts/:postId/edit': {
                templateUrl: 'views/posts/edit.html',
                controller: 'EditPostCtrl'
            },
            '/users': {
                templateUrl: 'views/users/index.html',
                controller: 'UsersCtrl'
            },
            '/users/:userId': {
                templateUrl: 'views/users/show.html',
                controller: 'UserCtrl'
            },
            '/job_offers': {
                templateUrl: 'views/job_offers/index.html',
                controller: 'JobOffersCtrl'
            },
            '/job_offers/:offerId': {
                templateUrl: 'views/job_offers/show.html',
                controller: 'JobOfferCtrl'
            },
            /* ===== yeoman hook ===== */
            /* Do not remove these commented lines! Needed for auto-generation */
        }
    };

});
