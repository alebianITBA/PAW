define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.service('JobOfferService', ['$http', 'localStorageService', function($http, localStorageService) {
        const headers = { headers: { 'Authorization': localStorageService.get(connectOn.constants.TOKEN_KEY) } };

        return {
            list: function(page) {
                return $http.get(`${connectOn.constants.API_V1_BASE_URL}/job_offers?page=${page || 1}`, headers);
            }
        }

    }]);
});
