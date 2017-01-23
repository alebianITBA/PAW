'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.controller(
        'EditUserCtrl',
        ['$scope', 'UserService', '$routeParams', '$location', 'CommonService', 'SkillService',
        function($scope, UserService, $routeParams, $location, CommonService, SkillService) {
            this.user = {};
            this.selectedSkills = [];
            this.skillsToSelect = [];
            this.skillIdToAdd = null;

            var that = this;
            this.getUser = function() {
                UserService.me().then(function(result) {
                    that.user = result.data;
                    CommonService.reloadData(that.selectedSkills, result.data.skills);
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
                });
            };
            this.getUser();

            this.removeSelected = function(skillId) {
                var check = function(element) {
                    if (element.id === skillId) {
                        return true;
                    }
                    return false;
                };
                CommonService.moveElements(that.selectedSkills, that.skillsToSelect, check);
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
            };

            this.editUser = function() {
                that.user.skillIds = that.selectedSkills;
                that.user.lastName = that.user.last_name;
                that.user.firstName = that.user.first_name;
                UserService.edit(that.user).then(function(result) {
                    $location.path('/users/' + that.user.id);
                });
            };
        }]
    );
});
