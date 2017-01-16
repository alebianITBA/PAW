define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller('MainCtrl', ['$scope', 'PostService', 'JobOfferService', function($scope, PostService, JobOfferService) {
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

        this.previousPage = function() {
            var that = this;
            if (that.page > 1) {
                PostService.list(that.page - 1).then(function(result) {
                    while (that.posts.length > 0) {
                        that.posts.pop();
                    }
                    that.posts.push(...result.data);
                    that.page--;
                });
            }
        };

        this.nextPage = function() {
            var that = this;
            PostService.list(that.page + 1).then(function(result) {
                if (result.data.length > 0) {
                    while (that.posts.length > 0) {
                        that.posts.pop();
                    }
                    that.posts.push(...result.data);
                    that.page++;
                }
            });
        };

        var that = this;
        PostService.list(that.page).then(function(result) {
             that.posts = result.data;
        });
        JobOfferService.list(1).then(function(result) {
             that.offers = result.data.slice(0, 5);
        });

    }]);
});