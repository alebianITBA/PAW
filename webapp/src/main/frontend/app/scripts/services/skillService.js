'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.service(
        'SkillService',
        ['$http', 'localStorageService',
        function($http, localStorageService) {
            var headers = {headers: {'Authorization': localStorageService.get(connectOn.constants.TOKEN_KEY)}};

            return {
                all: function() {
                    return $http.get(connectOn.constants.API_V1_BASE_URL + '/skills', headers);
                }
            };

        }]
    );
});
