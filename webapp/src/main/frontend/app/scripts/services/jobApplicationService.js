define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.service('JobApplicationService', ['$http', 'localStorageService', function($http, localStorageService) {
        const headers = { headers: { 'Authorization': localStorageService.get(connectOn.constants.TOKEN_KEY) } };

        return {
            myApplications: function(page, perPage) {
                return $http.get(`${connectOn.constants.API_V1_BASE_URL}/job_applications?page=${page}&per_page=${perPage}`, headers);
            },
            delete: function(applicationId) {
                return $http.delete(`${connectOn.constants.API_V1_BASE_URL}/job_applications/${applicationId}`, headers);
            },
            create: function(jobOfferId) {
                return $http.post(`${connectOn.constants.API_V1_BASE_URL}/job_applications?job_offer_id`, { jobOfferId: jobOfferId }, headers);
            }
        }

    }]);
});
