define(['connectOn'], function(connectOn) {

    'use strict';
    connectOn.controller('RegisterCtrl', function($scope, $http) {
        this.user = {};

        this.register = function() {
            console.log(JSON.stringify(this.user));
            $http.post(connectOn.apiV1BaseUrl + '/users', JSON.stringify(this.user))
                 .then(function (response) {
                     console.log(response)
                 })
                 .catch(function(error) {
                    console.log(error)
                 });
        };
    });

});
