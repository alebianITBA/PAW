'use strict';
define([], function() {

    return {
        defaultRoutePath: '/',
        routes: {
            '/': {
                templateUrl: 'grupo5/views/register.html',
                controller: 'RegisterCtrl'
            },
            '/index': {
                templateUrl: 'grupo5/views/main.html',
                controller: 'MainCtrl'
            },
            '/posts/:postId': {
                templateUrl: 'grupo5/views/posts/show.html',
                controller: 'PostCtrl'
            },
            '/posts/:postId/edit': {
                templateUrl: 'grupo5/views/posts/edit.html',
                controller: 'EditPostCtrl'
            },
            '/users': {
                templateUrl: 'grupo5/views/users/index.html',
                controller: 'UsersCtrl'
            },
            '/users/:userId': {
                templateUrl: 'grupo5/views/users/show.html',
                controller: 'UserCtrl'
            },
            '/users/me/edit': {
                templateUrl: 'grupo5/views/users/edit.html',
                controller: 'EditUserCtrl'
            },
            '/onboarding': {
                templateUrl: 'grupo5/views/users/onboarding.html',
                controller: 'OnboardingCtrl'
            },
            '/job_offers': {
                templateUrl: 'grupo5/views/job_offers/index.html',
                controller: 'JobOffersCtrl'
            },
            '/job_offers/:offerId': {
                templateUrl: 'grupo5/views/job_offers/show.html',
                controller: 'JobOfferCtrl'
            },
            '/job_offers/:offerId/edit': {
                templateUrl: 'grupo5/views/job_offers/edit.html',
                controller: 'EditJobOfferCtrl'
            }
            /* ===== yeoman hook ===== */
            /* Do not remove these commented lines! Needed for auto-generation */
        }
    };

});
