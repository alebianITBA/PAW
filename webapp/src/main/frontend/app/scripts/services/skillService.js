define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.service(
        'SkillService',
        ['$http', 'localStorageService',
        function($http, localStorageService) {
            const headers = { headers: { 'Authorization': localStorageService.get(connectOn.constants.TOKEN_KEY) } };

            return {
                all: function() {
                    return $http.get(`${connectOn.constants.API_V1_BASE_URL}/skills`, headers);
                }
            }

        }]
    );
});
