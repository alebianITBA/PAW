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
            },
            delete: function(id) {
                return $http.delete(`${connectOn.constants.API_V1_BASE_URL}/posts/${id}`, headers);
            },
            edit: function(post) {
                return $http.put(`${connectOn.constants.API_V1_BASE_URL}/posts/${post.id}`, JSON.stringify(post), headers);
            },
            userPosts: function(userId, page, perPage) {
                return $http.get(`${connectOn.constants.API_V1_BASE_URL}/posts?user_id=${userId}&page=${page}&per_page=${perPage}`, headers);
            }
        }

    }]);
});