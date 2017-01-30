'use strict';
define(['connectOn', 'services/commonService', 'services/sessionService'], function(connectOn) {

    connectOn.service(
        'PostService',
        ['$http', 'CommonService', 'SessionService',
        function($http, CommonService, SessionService) {
            return {
                createPost: function(post) {
                    return $http.post(connectOn.constants.API_V1_BASE_URL + '/posts', JSON.stringify(post), CommonService.headers());
                },
                list: function(page) {
                    var url = connectOn.constants.API_V1_BASE_URL + '/posts';
                    url = CommonService.addQueryToUrl(url, 'page', (page || 1));
                    return $http.get(url, CommonService.headers());
                },
                show: function(id) {
                    return $http.get(connectOn.constants.API_V1_BASE_URL + '/posts/' + id, CommonService.headers());
                },
                delete: function(id) {
                    return $http.delete(connectOn.constants.API_V1_BASE_URL + '/posts/' + id, CommonService.headers());
                },
                edit: function(post) {
                    return $http.put(connectOn.constants.API_V1_BASE_URL + '/posts/' + post.id, JSON.stringify(post), CommonService.headers());
                },
                userPosts: function(userId, page, perPage) {
                    var url = connectOn.constants.API_V1_BASE_URL + '/posts';
                    url = CommonService.addQueryToUrl(url, 'user_id', userId);
                    url = CommonService.addQueryToUrl(url, 'page', (page || 1));
                    url = CommonService.addQueryToUrl(url, 'per_page', perPage);
                    return $http.get(url, CommonService.headers());
                }
            };

        }])
    ;
});
