(function() {
    'use strict';
    angular
        .module('maderaApp')
        .factory('DuplicateModule', DuplicateModule);

    DuplicateModule.$inject = ['$resource'];

    function DuplicateModule ($resource) {
        var resourceUrl =  'api/modules/duplicate';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                },
                isArray:true
            },
            'update': { method:'PUT' }
        });
    }
})();
