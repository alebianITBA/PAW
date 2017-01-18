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
                templateUrl: 'views/posts/post.html',
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
                templateUrl: 'views/users/user.html',
                controller: 'UserCtrl'
            },
            /* ===== yeoman hook ===== */
            /* Do not remove these commented lines! Needed for auto-generation */
        }
    };

});
