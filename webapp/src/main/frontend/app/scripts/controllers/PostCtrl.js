define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller(
        'PostCtrl',
        ['$scope', 'PostService', '$routeParams', 'localStorageService', '$location',
        function($scope, PostService, $routeParams, localStorageService, $location) {
            this.post = {};
            this.belongsToUser = false;

            var that = this;
            var storage = localStorageService;
            var app = connectOn;
            PostService.show($routeParams.postId).then(function(result) {
                that.post = result.data;
                that.belongsToUser = storage.get(app.constants.LOGGED_USER).id === that.post.user.id;
            });

            this.delete = function() {
                PostService.delete(that.post.id).then(function() {
                    $location.path(app.constants.PATH_INDEX);
                });
            };
        }]
    );
});
