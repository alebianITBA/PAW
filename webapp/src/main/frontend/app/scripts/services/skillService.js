'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.service(
        'SkillService',
        ['$http', 'SessionService',
        function($http, SessionService) {
            var headers = SessionService.headers();

            return {
                all: function() {
                    return $http.get(connectOn.constants.API_V1_BASE_URL + '/skills', headers);
                },
                addSkill: function(skillId) {
                    return $http.post(connectOn.constants.API_V1_BASE_URL + '/users/me/add_skill', {skillId: skillId}, headers);
                },
                removeSkill: function(skillId) {
                    return $http.delete(connectOn.constants.API_V1_BASE_URL + '/users/me/remove_skill' + '?skill_id=' + skillId, headers);
                }
            };

        }]
    );
});
