define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller(
        'UsersCtrl',
        ['$scope', 'UserService', 'CommonService',
        function($scope, UserService, CommonService) {
            this.page = 1;
            this.users = [];

            var that = this;

            const incrementPage = function(newPage) {
                that.page++;
            };

            const decrementPage = function(newPage) {
                that.page--;
            };

            this.previousPage = function() {
                CommonService.previousPage(this.page, UserService, this.posts, decrementPage);
            };

            this.nextPage = function() {
                CommonService.nextPage(this.page, UserService, this.posts, incrementPage);
            };

            UserService.list(that.page).then(function(result) {
                that.users = result.data;
            });
        }]
    );
});
