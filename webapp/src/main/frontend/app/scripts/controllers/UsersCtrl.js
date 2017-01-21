'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.controller(
        'UsersCtrl',
        ['$scope', 'UserService', 'CommonService',
        function($scope, UserService, CommonService) {
            this.page = 1;
            this.users = [];

            var that = this;

            var incrementPage = function(newPage) {
                that.page++;
            };

            var decrementPage = function(newPage) {
                that.page--;
            };

            this.previousPage = function() {
                CommonService.previousPage(this.page, UserService, 'list', [this.page - 1], this.users, decrementPage);
            };

            this.nextPage = function() {
                CommonService.nextPage(this.page, UserService, 'list', [this.page + 1], this.users, incrementPage);
            };

            UserService.list(that.page).then(function(result) {
                that.users = result.data;
            });
        }]
    );
});
