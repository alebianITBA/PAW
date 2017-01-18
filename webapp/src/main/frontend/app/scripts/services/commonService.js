define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.service('CommonService', ['$http', 'localStorageService', function($http, localStorageService) {
        var reloadData = function(oldData, newData) {
            while (oldData.length > 0) {
                oldData.pop();
            }
            oldData.push(...newData);
        };

        return {
            previousPage: function(page, service, oldData, callback) {
                if (page > 1) {
                    const newPage = page - 1;
                    service.list(newPage).then(function(result) {
                        reloadData(oldData, result.data);
                        callback(newPage);
                    });
                }
            },
            nextPage: function(page, service, oldData, callback) {
                var newPage = page + 1;
                service.list(newPage).then(function(result) {
                    if (result.data.length > 0) {
                        reloadData(oldData, result.data);
                        callback(newPage);
                    }
                });
            }
        }

    }]);
});