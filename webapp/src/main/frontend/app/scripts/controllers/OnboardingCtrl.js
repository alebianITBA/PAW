'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.controller(
        'OnboardingCtrl',
        ['$scope', 'UserService', 'SkillService', 'CommonService', 'SessionService', '$timeout',
        function($scope, UserService, SkillService, CommonService, SessionService, $timeout) {
            this.selectedSkills = SessionService.loggedUser().skills;
            this.skillsToSelect = [];
            this.skillIdToAdd = null;

            // USER
            this.user = SessionService.loggedUser();
            var that = this;

            this.getSkills = function() {
                SkillService.all().then(function(response) {
                    var skillEquals = function(skill1, skill2) {
                        return skill1.id === skill2.id;
                    };
                    for (var i = 0; i < response.data.length; i++) {
                        if (!CommonService.includes(that.selectedSkills, response.data[i], skillEquals)) {
                            that.skillsToSelect.push(response.data[i]);
                        }
                    };
                });
            };
            this.getSkills();

            this.removeSelected = function(skillId) {
                var check = function(element) {
                    if (element.id === skillId) {
                        return true;
                    }
                    return false;
                };
                CommonService.moveElements(that.selectedSkills, that.skillsToSelect, check);

                that.user.skillIds = that.selectedSkills;
                UserService.edit(that.user).then(function(response) {
                    if (response.status !== 200) {
                        CommonService.moveElements(that.skillsToSelect, that.selectedSkills, check);
                    }
                });
            };

            this.addSelected = function() {
                var id = Number.parseInt(this.skillIdToAdd, 10);
                var check = function(element) {
                    if (element.id === id) {
                        return true;
                    }
                    return false;
                };
                CommonService.moveElements(that.skillsToSelect, that.selectedSkills, check);

                that.user.skillIds = that.selectedSkills;
                UserService.edit(that.user).then(function(response) {
                    if (response.status !== 200) {
                        CommonService.moveElements(that.selectedSkills, that.skillsToSelect, check);
                    }
                });
            };

        }]
    );
});
