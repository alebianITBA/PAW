'use strict';
define(['connectOn', './NavbarCtrl', '../directives/navbar', 'services/jobOfferService', 'services/sessionService', 'services/commonService', 'services/jobApplicationService'], function(connectOn) {

    connectOn.controller(
        'JobOfferCtrl',
        ['$scope', 'JobOfferService', '$routeParams', 'SessionService', 'CommonService', '$location', 'JobApplicationService',
        function($scope, JobOfferService, $routeParams, SessionService, CommonService, $location, JobApplicationService) {
            var that = this;
            var constants = connectOn.constants;
            this.belongsToUser = false;
            this.showApply = false;
            this.showApplied = false;
            this.loggedUserId = SessionService.loggedUser().id;

            // OFFER
            this.offer = {};
            this.applications = [];
            JobOfferService.show($routeParams.offerId)
                .then(function(result) {
                    that.offer = result.data;
                    var isOwner = that.loggedUserId === result.data.user.id;
                    that.belongsToUser = isOwner;
                    if (isOwner) {
                        JobOfferService.applicationsOf($routeParams.offerId).then(function(response) {
                            if (response.status === 200) {
                                CommonService.reloadData(that.applications, response.data);
                            }
                        });
                    } else {
                      that.showApply = !that.offer.user_applied;
                      that.showApplied = that.offer.user_applied;
                    }
                })
                .catch(function() {
                    $location.path(that.constants.BASE_PATH + '/job_offers/');
                });

            this.delete = function() {
                JobOfferService.delete($routeParams.offerId).then(function(response) {
                    if (response.status === 200) {
                        $location.path(that.constants.BASE_PATH + '/job_offers/');
                    }
                });
            };

            this.createApplication = function(jobOfferId) {
                JobApplicationService.create(jobOfferId).then(function (response) {
                    if (response.status === 201) {
                        that.showApply = false;
                        that.showApplied = true;
                        $location.path(that.constants.BASE_PATH + '/job_offers/' + that.offer.id);
                    }
                });
            };
        }]
    );
});
