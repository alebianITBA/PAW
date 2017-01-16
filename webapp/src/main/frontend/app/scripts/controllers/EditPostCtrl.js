define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller('EditPostCtrl', ['$scope', 'PostService', '$routeParams', '$location', function($scope, PostService, $routeParams, $location) {
        this.post = {};

        var that = this;
        PostService.show($routeParams.postId).then(function(result) {
            that.post = result.data;
        });

        this.editPost = function() {
            PostService.edit(that.post).then(function(result) {
                $location.path(`/posts/${that.post.id}`);
            });
        };
    }]);
});
