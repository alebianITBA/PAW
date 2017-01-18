define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.service('JobApplicationService', ['$http', 'localStorageService', function($http, localStorageService) {
        const headers = { headers: { 'Authorization': localStorageService.get(connectOn.constants.TOKEN_KEY) } };

        return {
            myApplications: function() {
                return $http.get(`${connectOn.constants.API_V1_BASE_URL}/job_applications/me`, headers);
            }
        }

    }]);
});
