define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.service(
        'JobOfferService',
        ['$http', 'localStorageService', 'CommonService',
        function($http, localStorageService, CommonService) {
            const headers = { headers: { 'Authorization': localStorageService.get(connectOn.constants.TOKEN_KEY) } };

            return {
                list: function(page, perPage, skillId) {
                    var url = `${connectOn.constants.API_V1_BASE_URL}/job_offers`;
                    url = CommonService.addQueryToUrl(url, 'page', (page || 1));
                    url = CommonService.addQueryToUrl(url, 'per_page', perPage);
                    url = CommonService.addQueryToUrl(url, 'skill_id', skillId);
                    return $http.get(url, headers);
                },
                notApplied: function(page, perPage) {
                    var url = `${connectOn.constants.API_V1_BASE_URL}/job_offers/not_applied`;
                    url = CommonService.addQueryToUrl(url, 'page', (page || 1));
                    url = CommonService.addQueryToUrl(url, 'per_page', perPage);
                    return $http.get(url, headers);
                },
                create: function(offer) {
                    offer.skillIds = offer.skillIds.map(function(element) { return element.id; }).join(',');
                    return $http.post(`${connectOn.constants.API_V1_BASE_URL}/job_offers`, JSON.stringify(offer), headers);
                }
            }

    }]);
});
