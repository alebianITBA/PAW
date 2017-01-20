define(['connectOn'], function(connectOn) {

    connectOn.service(
        'CommonService',
        ['$http', 'localStorageService',
        function($http, localStorageService) {
            // Function used to reload any array of data with new data
            // It is necessary to use push() and pop() so the dom gets refreshed
            const reloadData = function(oldData, newData) {
                while (oldData.length > 0) {
                    oldData.pop();
                }
                oldData.push(...newData);
            };

            // Function used to paginate - get previous page of data
            // Params:
            // + page: current page
            // + service: the service object that will be used to get data, 'method' named field of the object should be a function
            // + method: the name of the service's method that will be called to fetch the data
            // + arguments: array containing the arguments that will be passed to the service's method
            // + oldData: array of data containing the data to de refreshed
            // + callback: this should update the original page value
            const previousPage = function(page, service, method, arguments, oldData, callback) {
                if (page > 1) {
                    const newPage = page - 1;
                    service[method].apply(this, arguments).then(function(result) {
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
            // + arguments: array containing the arguments that will be passed to the service's method
            // + oldData: array of data containing the data to de refreshed
            // + callback: this should update the original page value
            const nextPage = function(page, service, method, arguments, oldData, callback) {
                var newPage = page + 1;
                service[method].apply(this, arguments).then(function(result) {
                    if (result.data.length > 0) {
                        reloadData(oldData, result.data);
                        callback(newPage);
                    }
                });
            };

            const addQueryToUrl = function(url, queryName, queryParam) {
                if (queryParam === null || queryParam === 'undefined') {
                    return url;
                }
                var newUrl;
                var urlParts = url.split('?');
                if (urlParts.length === 1) {
                    // First param
                    newUrl = urlParts[0] + `?${queryName}=${queryParam}`;
                } else {
                    // url had previous params
                    newUrl = url + `&${queryName}=${queryParam}`;
                }
                return newUrl;
            };

            return {
                reloadData: reloadData,
                previousPage: previousPage,
                nextPage: nextPage,
                addQueryToUrl: addQueryToUrl
            }

        }]
    );
});