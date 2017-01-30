'use strict';
define(['connectOn', './NavbarCtrl', '../directives/navbar', 'services/jobOfferService', 'services/commonService', 'services/skillService'], function(connectOn) {

    connectOn.controller(
        'EditJobOfferCtrl',
        ['$scope', 'JobOfferService', '$routeParams', '$location', 'CommonService', 'SkillService',
        function($scope, JobOfferService, $routeParams, $location, CommonService, SkillService) {
            this.offer = {};
            this.selectedSkills = [];
            this.skillsToSelect = [];
            this.skillIdToAdd = null;
            this.constants = connectOn.constants;

            var that = this;
            this.getOffer = function() {
                JobOfferService.show($routeParams.offerId).then(function(result) {
                    that.offer = result.data;
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
            this.getOffer();

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

            this.editOffer = function() {
                that.offer.skillIds = that.selectedSkills;
                JobOfferService.edit(that.offer).then(function(result) {
                    $location.path(that.constants.BASE_PATH + '/job_offers/' + that.offer.id);
                });
            };
        }]
    );
});
