define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.service('UserService', function($http) {

        return {
            createUser: function(user) {
                $http.post(`${connectOn.constants.API_V1_BASE_URL}/users`, JSON.stringify(user))
                     .then(function (response) { return user; });
            },
            list: function(page) {
                $http.get(`${connectOn.constants.API_V1_BASE_URL}/users?page=${page || 1}`)
                     .then();
            },
            show: function(id) {
                $http.get(`${connectOn.constants.API_V1_BASE_URL}/users/${id}`)
                     .then();
            },
            me: function() {
                $http.get(`${connectOn.constants.API_V1_BASE_URL}/users/me`)
                     .then();
            }
        }

    });
});