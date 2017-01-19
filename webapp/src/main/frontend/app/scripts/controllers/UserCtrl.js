define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller(
        'UserCtrl',
        ['$scope', 'UserService', 'PostService', 'JobApplicationService', 'JobOfferService', 'CommonService', 'localStorageService', '$routeParams',
        function($scope, UserService, PostService, JobApplicationService, JobOfferService, CommonService, localStorageService, $routeParams) {
            this.belongsToUser = false;
            this.loggedUserId = localStorageService.get(connectOn.constants.LOGGED_USER).id;
            var that = this;
            
            // USER
            this.user = {};

            UserService.show($routeParams.userId).then(function(result) {
                that.user = result.data;
                const isOwner = that.loggedUserId === result.data.id;
                that.belongsToUser = isOwner;
                if (isOwner) {

                }
            });

            // POSTS
            this.posts = [];
            this.postsPage = 1;
            this.postsPerPage = 5;

            this.currentPosts = function() {
                PostService.userPosts($routeParams.userId, that.postsPage, that.postsPerPage).then(function(result) {
                    CommonService.reloadData(that.posts, result.data);
                })
            };
            this.currentPosts();

            const incrementPostsPage = function(newPage) {
                that.postsPage++;
            };

            const decrementPostsPage = function(newPage) {
                that.postsPage--;
            };

            this.previousPostsPage = function() {
                CommonService.previousPage(this.postsPage, PostService, 'userPosts', [this.user.id, this.postsPage - 1, this.postsPerPage], this.posts, decrementPostsPage);
            };

            this.nextPostsPage = function() {
                CommonService.nextPage(this.postsPage, PostService, 'userPosts', [this.user.id, this.postsPage + 1, this.postsPerPage], this.posts, incrementPostsPage);
            };

            this.deletePost = function(postId) {
                PostService.delete(postId).then(function(result) {
                    that.currentPosts();
                });
            };

            // JOB OFFERS
            this.job_offers = [];

            // JOB APPLICATIONS
            this.applications = [];
        }]
    );
});
