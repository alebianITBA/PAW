'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.controller(
        'UserCtrl',
        ['$scope', 'UserService', 'PostService', 'JobApplicationService', 'JobOfferService', 'CommonService', 'localStorageService', '$routeParams',
        function($scope, UserService, PostService, JobApplicationService, JobOfferService, CommonService, localStorageService, $routeParams) {
            this.belongsToUser = false;
            this.loggedUserId = localStorageService.get(connectOn.constants.LOGGED_USER).id;
            var that = this;
            var defaultPerPage = 3;

            // USER
            this.user = {};

            UserService.show($routeParams.userId).then(function(result) {
                that.user = result.data;
                var isOwner = that.loggedUserId === result.data.id;
                that.belongsToUser = isOwner;
                if (isOwner) {
                    that.currentApplications();
                }
            });

            // POSTS
            this.posts = [];
            this.postsPage = 1;
            this.postsPerPage = defaultPerPage;

            this.currentPosts = function() {
                PostService.userPosts($routeParams.userId, that.postsPage, that.postsPerPage).then(function(result) {
                    CommonService.reloadData(that.posts, result.data);
                });
            };
            this.currentPosts();

            var incrementPostsPage = function() {
                that.postsPage++;
            };

            var decrementPostsPage = function() {
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
            this.jobOffers = [];
            this.jobOffersPage = 1;
            this.jobOffersPerPage = defaultPerPage;

            // JOB APPLICATIONS
            this.applications = [];
            this.applicationsPage = 1;
            this.applicationsPerPage = defaultPerPage;

            this.currentApplications = function() {
                if (that.belongsToUser) {
                    JobApplicationService.myApplications(that.applicationsPage, that.applicationsPerPage).then(function(result) {
                        CommonService.reloadData(that.applications, result.data);
                    });
                }
            };

            var incrementApplicationsPage = function() {
                that.applicationsPage++;
            };

            var decrementApplicationsPage = function() {
                that.applicationsPage--;
            };

            this.previousApplicationsPage = function() {
                CommonService.previousPage(this.applicationsPage, JobApplicationService, 'myApplications', [this.applicationsPage - 1, this.applicationsPerPage], this.applications, decrementApplicationsPage);
            };

            this.nextApplicationsPage = function() {
                CommonService.nextPage(this.applicationsPage, JobApplicationService, 'myApplications', [this.applicationsPage + 1, this.applicationsPerPage], this.applications, incrementApplicationsPage);
            };

            this.deleteApplication = function(applicationId) {
                JobApplicationService.delete(applicationId).then(function(result) {
                    that.currentApplications();
                });
            };
        }]
    );
});
