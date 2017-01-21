'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.service(
        'UserService',
        ['$http', 'localStorageService', 'CommonService',
        function($http, localStorageService, CommonService) {
            var headers = {headers: {'Authorization': localStorageService.get(connectOn.constants.TOKEN_KEY)}};

            return {
                createUser: function(user) {
                    return $http.post(connectOn.constants.API_V1_BASE_URL + '/users', JSON.stringify(user));
                },
                login: function(params) {
                    return $http.post(connectOn.constants.API_V1_BASE_URL + '/login', {email: params.email, password: params.password});
                },
                list: function(page) {
                    var url = connectOn.constants.API_V1_BASE_URL + '/users';
                    url = CommonService.addQueryToUrl(url, 'page', (page || 1));
                    return $http.get(url, headers);
                },
                show: function(id) {
                    return $http.get(connectOn.constants.API_V1_BASE_URL + '/users/' + id, headers);
                },
                me: function() {
                    return $http.get(connectOn.constants.API_V1_BASE_URL + '/users/me', headers);
                }
            };

        }]
    );
});
