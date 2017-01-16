define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.service('UserService', function($http) {

        return {
            createUser: function(user) {
                return $http.post(`${connectOn.constants.API_V1_BASE_URL}/users`, JSON.stringify(user));
            },
            login: function(params) {
                return $http.post(`${connectOn.constants.API_V1_BASE_URL}/login`, { email: params.email, password: params.password });
            },
            list: function(page) {
                return $http.get(`${connectOn.constants.API_V1_BASE_URL}/users?page=${page || 1}`);
            },
            show: function(id) {
                return $http.get(`${connectOn.constants.API_V1_BASE_URL}/users/${id}`);
            },
            me: function() {
                return $http.get(`${connectOn.constants.API_V1_BASE_URL}/users/me`);
            }
        }

    });
});
