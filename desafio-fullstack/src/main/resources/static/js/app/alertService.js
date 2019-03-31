angular.module('app.services', [])
    .factory('AlertService', ['$http', function($http) {

        var service = {};
        var _baseUrl = '/api/alerts';


        service.getAll = function() {
            return $http.get(_baseUrl).then(function (response) {
                return response.data;
            });
        };

        return service;

    }]);