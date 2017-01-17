define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.service('UserService', ['$http', 'localStorageService', function($http, localStorageService) {
        const headers = { headers: { 'Authorization': localStorageService.get(connectOn.constants.TOKEN_KEY) } };

        return {
            createUser: function(user) {
                return $http.post(`${connectOn.constants.API_V1_BASE_URL}/users`, JSON.stringify(user));
            },
            login: function(params) {
                return $http.post(`${connectOn.constants.API_V1_BASE_URL}/login`, { email: params.email, password: params.password });
            },
            list: function(page) {
                return $http.get(`${connectOn.constants.API_V1_BASE_URL}/users?page=${page || 1}`, headers);
            },
            show: function(id) {
                return $http.get(`${connectOn.constants.API_V1_BASE_URL}/users/${id}`, headers);
            },
            me: function() {
                return $http.get(`${connectOn.constants.API_V1_BASE_URL}/users/me`, headers);
            }
        }

    }]);
});
