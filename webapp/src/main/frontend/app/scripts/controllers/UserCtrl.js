'use strict';
define(['connectOn', './NavbarCtrl', '../directives/navbar', 'services/userService', 'services/postService', 'services/jobApplicationService', 'services/jobOfferService', 'services/commonService', 'services/sessionService'], function(connectOn) {

    connectOn.controller(
        'UserCtrl',
        ['$scope', 'UserService', 'PostService', 'JobApplicationService', 'JobOfferService', 'CommonService', 'SessionService', '$routeParams',
        function($scope, UserService, PostService, JobApplicationService, JobOfferService, CommonService, SessionService, $routeParams) {
            this.belongsToUser = false;
            var that = this;
            var defaultPerPage = 3;

            this.onUrl = connectOn.constants.BASE_URL + '/images/on.ba5bc526.png';
            this.offUrl = connectOn.constants.BASE_URL + '/images/off.dd64f230.png';

            // USER
            this.user = {};

            this.getUser = function() {
                UserService.show($routeParams.userId).then(function(result) {
                    that.user = result.data;
                    var isOwner = SessionService.loggedUser().id === result.data.id;
                    that.belongsToUser = isOwner;
                    if (isOwner) {
                        that.currentApplications();
                    }
                });
            };
            this.getUser();

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

            this.getJobOffers = function() {
                JobOfferService.fromUser($routeParams.userId, that.jobOffersPage, that.jobOffersPerPage).then(function(result) {
                    CommonService.reloadData(that.jobOffers, result.data);
                });
            };
            this.getJobOffers();

            var incrementOffersPage = function() {
                that.jobOffersPage++;
            };

            var decrementOffersPage = function() {
                that.jobOffersPage--;
            };

            this.previousOffersPage = function() {
                CommonService.previousPage(this.jobOffersPage, JobOfferService, 'fromUser', [$routeParams.userId, this.jobOffersPage - 1, this.jobOffersPerPage], this.jobOffers, decrementOffersPage);
            };

            this.nextOffersPage = function() {
                CommonService.nextPage(this.jobOffersPage, JobOfferService, 'fromUser', [$routeParams.userId, this.jobOffersPage + 1, this.jobOffersPerPage], this.jobOffers, incrementOffersPage);
            };

            this.deleteOffer = function(offerId) {
                JobOfferService.delete(offerId).then(function(result) {
                    that.getJobOffers();
                });
            };

            this.openOffer = function(offerId) {
                JobOfferService.open(offerId).then(function(result) {
                    that.getJobOffers();
                });
            };

            this.closeOffer = function(offerId) {
                JobOfferService.close(offerId).then(function(result) {
                    that.getJobOffers();
                });
            };

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
