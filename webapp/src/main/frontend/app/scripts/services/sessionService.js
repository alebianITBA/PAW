'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.service(
        'SessionService',
        ['$http', 'localStorageService', 'CommonService',
        function($http, localStorageService, CommonService) {
            var constants = connectOn.constants;
            var storage = localStorageService;
            var user = {
                token: storage.get(constants.TOKEN_KEY),
                skills: []
            };

            var headers = function() {
                return {headers: {'Authorization': user.token}};
            };

            var isLogged = function() {
                if (user.token === null) {
                    return false;
                }
                updateUserInfo();
                return true;
            };

            var updateUserInfo = function() {
                $http.get(constants.API_V1_BASE_URL + '/users/me', headers())
                    .then(function(result) {
                        if (result.status === 200) {
                            user.id = result.data.id;
                            user.first_name = result.data.first_name;
                            user.last_name = result.data.last_name;
                            user.email = result.data.email;
                            CommonService.reloadData(user.skills, result.data.skills);
                        }
                        if (result.status === 401 || result.status === 403) {
                            user.token = null;
                        }
                    });
            };

            var loggedUser = function() {
                updateUserInfo();
                return user;
            };

            var login = function(email, password) {
                $http.post(constants.API_V1_BASE_URL + '/login', {email: email, password: password})
                    .then(function(result) {
                        if (result.status === 200) {
                            storage.set(constants.TOKEN_KEY, result.data.token);
                            user.token = result.data.token;
                            updateUserInfo();
                        }
                    });
            };

            var logout = function() {
                storage.set(constants.TOKEN_KEY, null);
                storage.clearAll();
                user.token = null;
            };

            return {
                isLogged: isLogged,
                loggedUser: loggedUser,
                login: login,
                logout: logout,
                headers: headers
            };

        }]
    );
});
