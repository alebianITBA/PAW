define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.service(
        'JobApplicationService',
        ['$http', 'localStorageService', 'CommonService',
        function($http, localStorageService, CommonService) {
            const headers = { headers: { 'Authorization': localStorageService.get(connectOn.constants.TOKEN_KEY) } };

            return {
                myApplications: function(page, perPage) {
                    var url = `${connectOn.constants.API_V1_BASE_URL}/job_applications`;
                    url = CommonService.addQueryToUrl(url, 'page', (page || 1));
                    url = CommonService.addQueryToUrl(url, 'per_page', perPage);
                    return $http.get(url, headers);
                },
                delete: function(applicationId) {
                    return $http.delete(`${connectOn.constants.API_V1_BASE_URL}/job_applications/${applicationId}`, headers);
                },
                create: function(jobOfferId) {
                    return $http.post(`${connectOn.constants.API_V1_BASE_URL}/job_applications`, { jobOfferId: jobOfferId }, headers);
                }
            }

        }]
    );
});
