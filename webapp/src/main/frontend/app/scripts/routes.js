'use strict';
define([], function() {

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
            '/users/me/edit': {
                templateUrl: 'views/users/edit.html',
                controller: 'EditUserCtrl'
            },
            '/job_offers': {
                templateUrl: 'views/job_offers/index.html',
                controller: 'JobOffersCtrl'
            },
            '/job_offers/:offerId': {
                templateUrl: 'views/job_offers/show.html',
                controller: 'JobOfferCtrl'
            },
            '/job_offers/:offerId/edit': {
                templateUrl: 'views/job_offers/edit.html',
                controller: 'EditJobOfferCtrl'
            }
            /* ===== yeoman hook ===== */
            /* Do not remove these commented lines! Needed for auto-generation */
        }
    };

});
