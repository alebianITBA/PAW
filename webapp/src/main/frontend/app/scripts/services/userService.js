'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.service(
        'UserService',
        ['$http', 'CommonService', 'SessionService',
        function($http, CommonService, SessionService) {
            var headers = SessionService.headers();

            return {
                createUser: function(user) {
                    return $http.post(connectOn.constants.API_V1_BASE_URL + '/users', JSON.stringify(user));
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
                },
                edit: function(user) {
                    var getId = function(element) {
                        return element.id;
                    };
                    user.skillIds = user.skillIds.map(getId).join(',');
                    return $http.put(connectOn.constants.API_V1_BASE_URL + '/users/me', JSON.stringify(user), headers);
                }
            };

        }]
    );
});
