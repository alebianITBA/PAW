'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.controller(
        'PostCtrl',
        ['$scope', 'PostService', '$routeParams', 'SessionService', '$location',
        function($scope, PostService, $routeParams, SessionService, $location) {
            this.post = {};
            this.belongsToUser = false;

            var that = this;
            var app = connectOn;
            PostService.show($routeParams.postId).then(function(result) {
                that.post = result.data;
                that.belongsToUser = SessionService.loggedUser().id === that.post.user.id;
            });

            this.delete = function() {
                PostService.delete(that.post.id).then(function() {
                    $location.path(app.constants.PATH_INDEX);
                });
            };
        }]
    );
});
