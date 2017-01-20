define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller(
        'JobOffersCtrl',
        ['$scope', 'JobOfferService', 'JobApplicationService', 'CommonService', 'localStorageService',
        function($scope, JobOfferService, JobApplicationService, CommonService, localStorageService) {
            this.offers = [];
        }]
    );
});
