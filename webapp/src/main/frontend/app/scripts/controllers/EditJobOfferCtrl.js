'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.controller(
        'EditJobOfferCtrl',
        ['$scope', 'JobOfferService', '$routeParams', '$location',
        function($scope, JobOfferService, $routeParams, $location) {
            this.post = {};

            var that = this;
            JobOfferService.show($routeParams.postId).then(function(result) {
                that.post = result.data;
            });

            this.editPost = function() {
                JobOfferService.edit(that.post).then(function(result) {
                    $location.path('/posts/' + '${that.post.id}');
                });
            };
        }]
    );
});
