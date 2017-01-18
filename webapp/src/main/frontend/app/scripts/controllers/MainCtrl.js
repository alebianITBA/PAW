define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller(
        'MainCtrl',
        ['$scope', 'PostService', 'JobOfferService', 'CommonService',
        function($scope, PostService, JobOfferService, CommonService) {
            this.post = {};
            this.posts = [];
            this.page = 1;
            this.offers = [];

            this.createPost = function() {
                var that = this;
                PostService.createPost(that.post).then(function (response) {
                    that.posts.unshift(response.data);
                    that.post = {};
                });
            };

            var that = this;

            const incrementPage = function(newPage) {
                that.page++;
            };

            const decrementPage = function(newPage) {
                that.page--;
            };

            this.previousPage = function() {
                CommonService.previousPage(this.page, PostService, this.posts, decrementPage);
            };

            this.nextPage = function() {
                CommonService.nextPage(this.page, PostService, this.posts, incrementPage);
            };

            PostService.list(that.page).then(function(result) {
                that.posts = result.data;
            });
            JobOfferService.list(1).then(function(result) {
                that.offers = result.data.slice(0, 5);
            });

        }]
    );
});