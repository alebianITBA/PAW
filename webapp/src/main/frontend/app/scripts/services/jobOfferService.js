'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.service(
        'JobOfferService',
        ['$http', 'SessionService', 'CommonService',
        function($http, SessionService, CommonService) {
            var getId = function(element) {
                return element.id;
            };

            return {
                list: function(page, perPage, skillId) {
                    var url = connectOn.constants.API_V1_BASE_URL + '/job_offers';
                    url = CommonService.addQueryToUrl(url, 'page', (page || 1));
                    url = CommonService.addQueryToUrl(url, 'per_page', perPage);
                    url = CommonService.addQueryToUrl(url, 'skill_id', skillId);
                    return $http.get(url, CommonService.headers());
                },
                notApplied: function(page, perPage) {
                    var url = connectOn.constants.API_V1_BASE_URL + '/job_offers/not_applied';
                    url = CommonService.addQueryToUrl(url, 'page', (page || 1));
                    url = CommonService.addQueryToUrl(url, 'per_page', perPage);
                    return $http.get(url, CommonService.headers());
                },
                fromUser: function(userId, page, perPage) {
                    var url = connectOn.constants.API_V1_BASE_URL + '/users/' + userId + '/job_offers';
                    url = CommonService.addQueryToUrl(url, 'page', (page || 1));
                    url = CommonService.addQueryToUrl(url, 'per_page', perPage);
                    return $http.get(url, CommonService.headers());
                },
                create: function(offer) {
                    offer.skillIds = offer.skillIds.map(getId).join(',');
                    return $http.post(connectOn.constants.API_V1_BASE_URL + '/job_offers', JSON.stringify(offer), CommonService.headers());
                },
                show: function(offerId) {
                    return $http.get(connectOn.constants.API_V1_BASE_URL + '/job_offers/' + offerId, CommonService.headers());
                },
                applicationsOf: function(offerId) {
                    return $http.get(connectOn.constants.API_V1_BASE_URL + '/job_offers/' + offerId + '/applications', CommonService.headers());
                },
                delete: function(offerId) {
                    return $http.delete(connectOn.constants.API_V1_BASE_URL + '/job_offers/' + offerId, CommonService.headers());
                },
                edit: function(offer) {
                    offer.skillIds = offer.skillIds.map(getId).join(',');
                    return $http.put(connectOn.constants.API_V1_BASE_URL + '/job_offers/' + offer.id, JSON.stringify(offer), CommonService.headers());
                },
                open: function(offerId) {
                    return $http.put(connectOn.constants.API_V1_BASE_URL + '/job_offers/' + offerId + '/open', {}, CommonService.headers());
                },
                close: function(offerId) {
                    return $http.put(connectOn.constants.API_V1_BASE_URL + '/job_offers/' + offerId + '/finish', {}, CommonService.headers());
                }
            };

    }]);
});
