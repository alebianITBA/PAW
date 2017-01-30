'use strict';
define(['connectOn', 'services/sessionService', 'services/commonService'], function(connectOn) {

    connectOn.service(
        'SkillService',
        ['$http', 'SessionService', 'CommonService',
        function($http, SessionService, CommonService) {
            return {
                all: function() {
                    return $http.get(connectOn.constants.API_V1_BASE_URL + '/skills', CommonService.headers());
                },
                addSkill: function(skillId) {
                    return $http.post(connectOn.constants.API_V1_BASE_URL + '/users/me/add_skill', {skillId: skillId}, CommonService.headers());
                },
                removeSkill: function(skillId) {
                    return $http.delete(connectOn.constants.API_V1_BASE_URL + '/users/me/remove_skill' + '?skill_id=' + skillId, CommonService.headers());
                }
            };

        }]
    );
});
