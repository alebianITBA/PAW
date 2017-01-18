define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller(
        'UserCtrl',
        ['$scope', 'UserService', 'PostService', 'JobApplicationService', 'JobOfferService', 'localStorageService',
        function($scope, UserService, PostService, JobApplicationService, JobOfferService, localStorageService) {
            this.user = {};
            this.job_offers = [];
            this.posts = [];
            this.applications = [];
            this.belongsToUser = false;

            var app = connectOn;
            var storage = localStorageService;
            var that = this;
            UserService.show($routeParams.userId).then(function(result) {
                that.user = result.data;
                const isOwner = storage.get(app.constants.LOGGED_USER).id === result.data.id;
                that.belongsToUser = isOwner;
                if (isOwner) {

                }
            });
        }]
    );
});
