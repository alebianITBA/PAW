define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.service('PostService', ['$http', 'localStorageService', function($http, localStorageService) {
        const headers = { headers: { 'Authorization': localStorageService.get(connectOn.constants.TOKEN_KEY) } };

        return {
            createPost: function(post) {
                return $http.post(`${connectOn.constants.API_V1_BASE_URL}/posts`, JSON.stringify(post), headers);
            },
            list: function(page) {
                return $http.get(`${connectOn.constants.API_V1_BASE_URL}/posts?page=${page || 1}`, headers);
            },
            show: function(id) {
                return $http.get(`${connectOn.constants.API_V1_BASE_URL}/posts/${id}`, headers);
            }
        }

    }]);
});