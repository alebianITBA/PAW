'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.controller(
        'MainCtrl',
        ['$scope', 'PostService', 'JobOfferService', 'CommonService', 'JobApplicationService', '$timeout',
        function($scope, PostService, JobOfferService, CommonService, JobApplicationService, $timeout) {
            this.post = {};
            this.posts = [];
            this.page = 1;
            this.offers = [];
            this.showSuccessfulApplication = false;
            var that = this;

            this.createPost = function() {
                PostService.createPost(that.post).then(function (response) {
                    that.posts.unshift(response.data);
                    that.post = {};
                });
            };

            this.createApplication = function(jobOfferId) {
                JobApplicationService.create(jobOfferId).then(function (response) {
                    if (response.status === 201) {
                        that.getJobOffers();
                        that.showSuccessfulApplication = true;
                        $timeout(function() {
                            that.showSuccessfulApplication = false;
                        }, 3000);
                    }
                });
            };

            var incrementPage = function(newPage) {
                that.page++;
            };

            var decrementPage = function(newPage) {
                that.page--;
            };

            this.previousPage = function() {
                CommonService.previousPage(this.page, PostService, 'list', [this.page - 1], this.posts, decrementPage);
            };

            this.nextPage = function() {
                CommonService.nextPage(this.page, PostService, 'list', [this.page + 1], this.posts, incrementPage);
            };

            this.getPosts = function() {
                PostService.list(that.page).then(function(result) {
                    CommonService.reloadData(that.posts, result.data);
                });
            };
            this.getPosts();

            this.getJobOffers = function() {
                JobOfferService.notApplied(1, 5).then(function(result) {
                    CommonService.reloadData(that.offers, result.data);
                });
            };
            this.getJobOffers();

        }]
    );
});