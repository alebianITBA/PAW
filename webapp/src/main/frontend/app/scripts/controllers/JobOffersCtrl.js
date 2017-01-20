define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller(
        'JobOffersCtrl',
        ['$scope', 'JobOfferService', 'JobApplicationService', 'CommonService', 'localStorageService', '$location', 'SkillService',
        function($scope, JobOfferService, JobApplicationService, CommonService, localStorageService, $location, SkillService) {
            this.perPage = 10;
            this.loggedUserId = localStorageService.get(connectOn.constants.LOGGED_USER).id;

            var that = this;

            // GET INFO
            this.offers = [];
            this.page = 1;
            this.skillIdFilter = $location.search().skill_id;
            this.getOffers = function() {
                JobOfferService.list(that.page, that.perPage, that.skillIdFilter).then(function(result) {
                    CommonService.reloadData(that.offers, result.data);
                })
            };
            this.getOffers();

            this.skills = [];
            this.getSkills = function() {
                SkillService.all().then(function(result) {
                    CommonService.reloadData(that.skills, result.data);
                    CommonService.reloadData(that.skillsToSelect, result.data);
                })
            };
            this.getSkills();

            // OTHER FUNCTIONS
            const incrementPage = function(newPage) {
                that.page++;
            };

            const decrementPage = function(newPage) {
                that.page--;
            };

            this.previousPage = function() {
                CommonService.previousPage(this.page, JobOfferService, 'list', [this.page - 1, this.perPage, this.skillIdFilter], this.offers, decrementPage);
            };

            this.nextPage = function() {
                CommonService.nextPage(this.page, JobOfferService, 'list', [this.page + 1, this.perPage, this.skillIdFilter], this.offers, incrementPage);
            };

            this.createApplication = function(jobOfferId) {
                JobApplicationService.create(jobOfferId).then(function (response) {
                    if (response.status === 201) {
                        that.getOffers();
                    }
                });
            };

            this.filter = function() {
                that.getOffers();
            };

            this.offer = {};
            this.selectedSkills = [];
            this.skillsToSelect = [];
            this.skillIdToAdd;
            this.removeSelected = function(skillId) {
                var check = function(element) {
                    if(element.id === skillId) {
                        return true;
                    }
                    return false;
                }
                CommonService.moveElements(that.selectedSkills, that.skillsToSelect, check);
            };
            this.addSelected = function() {
                var id = Number.parseInt(this.skillIdToAdd);
                var check = function(element) {
                    if(element.id === id) {
                        return true;
                    }
                    return false;
                }
                CommonService.moveElements(that.skillsToSelect, that.selectedSkills, check);
            };
            this.createOffer = function() {
                this.offer.skillIds = this.selectedSkills;
                JobOfferService.create(that.offer).then(function(response) {
                    if (response.status === 201) {
                        that.offer = {};
                        CommonService.moveElements(that.selectedSkills, that.skillsToSelect, function() { return true; });
                        that.getOffers();
                    }
                });
            };
        }]
    );
});
