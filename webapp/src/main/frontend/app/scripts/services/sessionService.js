'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.service(
        'SessionService',
        ['$http', 'localStorageService',
        function($http, localStorageService) {
            var constants = connectOn.constants;
            var storage = localStorageService;

            var statuses = {
                LOGGED_OUT: 'LOGGED_OUT',
                LOGGED_IN: 'LOGGED_IN',
                LOG_IN_ERROR: 'LOG_IN_ERROR',
                USER_INFO_UPDATED: 'USER_INFO_UPDATED'
            };
            var currentStatus = storage.get(constants.SESSION_STATUS_KEY);
            var observers = [];

            var changeStatus = function(newState) {
                if (statuses[newState] !== undefined) {
                    currentStatus = statuses[newState];
                    storage.set(constants.SESSION_STATUS_KEY, statuses[newState]);
                    notifyObservers();
                }
            };

            var notifyObservers = function() {
                var currentObserver = undefined;
                var response = false;
                for (var i = 0; i < observers.length; i++) {
                    currentObserver = observers[i];
                    if (currentObserver.active) {
                        switch (currentStatus) {
                            case statuses.LOGGED_IN:
                                response = currentObserver.onLogin();
                                break;
                            case statuses.LOGGED_OUT:
                                response = currentObserver.onLogout();
                                break;
                            case statuses.USER_INFO_UPDATED:
                                response = currentObserver.onUserUpdate();
                                break;
                            case statuses.LOG_IN_ERROR:
                                response = currentObserver.onLogInError('ERROR_1005');
                                break;
                            default:
                                break;
                        }
                        if (!response) {
                            currentObserver.active = false;
                        }
                    }
                }
                // TODO remove observers if they do not respond true
            };

            var doNothing = function() {
                return true;
            };

            var subscribe = function(onLogin, onLogout, onUserUpdate, debugName, onLogInError) {
                observers.push({
                    name: debugName,
                    active: true,
                    onLogin: onLogin,
                    onLogout: onLogout,
                    onUserUpdate: onUserUpdate,
                    onLogInError: (onLogInError || doNothing)
                });
            };

            var token = storage.get(constants.TOKEN_KEY);

            var user = {};

            var headers = function() {
                return {headers: {'Authorization': token}};
            };

            var isLogged = function() {
                if (currentStatus !== statuses.LOGGED_OUT) {
                    updateUserInfo();
                    return true;
                }
                return false;
            };

            var updateUserInfo = function() {
                $http.get(constants.API_V1_BASE_URL + '/users/me', headers()).then(function(result) {
                        if (result.status === 200) {
                            user = result.data;
                            changeStatus(statuses.USER_INFO_UPDATED);
                        }
                        if (result.status === 401 || result.status === 403) {
                            logout();
                        }
                    });
            };

            var loggedUser = function() {
                return user;
            };

            var login = function(email, password) {
                $http.post(constants.API_V1_BASE_URL + '/login', {email: email, password: password})
                    .then(function(result) {
                        if (result.status === 200) {
                            storage.set(constants.TOKEN_KEY, result.data.token);
                            token = result.data.token;
                            updateUserInfo();
                            changeStatus(statuses.LOGGED_IN);
                        }
                    })
                    .catch(function(result) {
                        changeStatus(statuses.LOG_IN_ERROR);
                    });
            };

            var logout = function() {
                storage.set(constants.TOKEN_KEY, null);
                storage.clearAll();
                token = null;
                changeStatus(statuses.LOGGED_OUT);
            };

            return {
                statuses: statuses,
                subscribe: subscribe,
                isLogged: isLogged,
                loggedUser: loggedUser,
                login: login,
                logout: logout,
                headers: headers,
                doNothing: doNothing
            };

        }]
    );
});
