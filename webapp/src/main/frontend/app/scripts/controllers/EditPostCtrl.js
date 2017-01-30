'use strict';
define(['connectOn', './NavbarCtrl', '../directives/navbar', 'services/postService'], function(connectOn) {

    connectOn.controller(
        'EditPostCtrl',
        ['$scope', 'PostService', '$routeParams', '$location',
        function($scope, PostService, $routeParams, $location) {
            this.post = {};
            this.constants = connectOn.constants;

            var that = this;
            PostService.show($routeParams.postId).then(function(result) {
                that.post = result.data;
            });

            this.editPost = function() {
                PostService.edit(that.post).then(function(result) {
                    $location.path(that.constants.BASE_PATH + '/posts/' + that.post.id);
                });
            };
        }]
    );
});
