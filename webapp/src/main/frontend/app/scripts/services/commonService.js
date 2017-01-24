'use strict';
define(['connectOn'], function(connectOn) {

    connectOn.service(
        'CommonService',
        ['SessionService',
        function(SessionService) {
            // Function used to reload any array of data with new data
            // It is necessary to use push() and pop() so the dom gets refreshed
            var reloadData = function(oldData, newData) {
                while (oldData.length > 0) {
                    oldData.pop();
                }
                for (var i = 0; i < newData.length; i++) {
                    oldData.push(newData[i]);
                }
            };

            // Function used to paginate - get previous page of data
            // Params:
            // + page: current page
            // + service: the service object that will be used to get data, 'method' named field of the object should be a function
            // + method: the name of the service's method that will be called to fetch the data
            // + args: array containing the arguments that will be passed to the service's method
            // + oldData: array of data containing the data to de refreshed
            // + callback: this should update the original page value
            var previousPage = function(page, service, method, args, oldData, callback) {
                if (page > 1) {
                    var newPage = page - 1;
                    service[method].apply(this, args).then(function(result) {
                        reloadData(oldData, result.data);
                        callback(newPage);
                    });
                }
            };

            // Function used to paginate - get next page of data
            // Params:
            // + page: current page
            // + service: the service object that will be used to get data, 'method' named field of the object should be a function
            // + method: the name of the service's method that will be called to fetch the data
            // + args: array containing the arguments that will be passed to the service's method
            // + oldData: array of data containing the data to de refreshed
            // + callback: this should update the original page value
            var nextPage = function(page, service, method, args, oldData, callback) {
                var newPage = page + 1;
                service[method].apply(this, args).then(function(result) {
                    if (result.data.length > 0) {
                        reloadData(oldData, result.data);
                        callback(newPage);
                    }
                });
            };

            var addQueryToUrl = function(url, queryName, queryParam) {
                if (queryParam === undefined) {
                    return url;
                }
                var newUrl;
                var urlParts = url.split('?');
                if (urlParts.length === 1) {
                    // First param
                    newUrl = urlParts[0] + '?' + queryName + '=' + queryParam;
                } else {
                    // url had previous params
                    newUrl = url + '&' + queryName + '=' + queryParam;
                }
                return newUrl;
            };

            // Function used to move an element from an array to another
            // Params:
            // + source: array to move elements from
            // + target: array to move elements to
            // + moveCheck: function that receives an element and returns true if the element should be moved
            var moveElements = function(source, target, moveCheck) {
                for (var i = 0; i < source.length; i++) {
                    var element = source[i];
                    if (moveCheck(element)) {
                        source.splice(i, 1);
                        target.push(element);
                        i--;
                    }
                }
            };

            var defaultEquals = function(elem1, elem2) {
                return elem1 === elem2;
            };

            // Function that checks if an element exists in an array
            var includes = function(data, element, comparator) {
                var equals = comparator === undefined ? defaultEquals : comparator;
                for (var i = 0; i < data.length; i++) {
                    if (equals(element, data[i])) {
                        return true;
                    }
                };
                return false;
            };

            // Subscribe to the user session service
            var headers = SessionService.headers();
            var getHeaders = function() {
                return headers;
            };
            var onLogin = function() {
                headers = SessionService.headers();
                return true;
            };
            SessionService.subscribe(onLogin, SessionService.doNothing, SessionService.doNothing, 'UserService');

            return {
                reloadData: reloadData,
                previousPage: previousPage,
                nextPage: nextPage,
                addQueryToUrl: addQueryToUrl,
                moveElements: moveElements,
                includes: includes,
                headers: getHeaders
            };

        }
    ]);
});
