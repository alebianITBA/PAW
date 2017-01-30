'use strict';
define(['connectOn', './NavbarCtrl', '../directives/navbar', 'services/userService', 'services/skillService', 'services/commonService', 'services/sessionService'], function(connectOn) {

    connectOn.controller(
        'OnboardingCtrl',
        ['$scope', 'UserService', 'SkillService', 'CommonService', 'SessionService', '$timeout', '$location',
        function($scope, UserService, SkillService, CommonService, SessionService, $timeout, $location) {
            var that = this;
            this.selectedSkills = null;
            this.skillsToSelect = [];
            this.skillIdToAdd = null;
            this.constants = connectOn.constants;
            this.alreadyLoaded = false;

            // USER
            this.user = {};

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

            var onUserUpdate = function() {
                if (!that.alreadyLoaded) {
                    that.user = SessionService.loggedUser();
                    that.selectedSkills = SessionService.loggedUser().skills || [];
                    that.getSkills();
                    that.alreadyLoaded = true;
                }
                return true;
            };
            SessionService.subscribe(SessionService.doNothing, SessionService.doNothing, onUserUpdate, 'RegisterCtrl');

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

            this.goToIndex = function() {
                $location.path(that.constants.PATH_INDEX);
            };

        }]
    );
});
