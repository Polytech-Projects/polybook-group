//routingApp.factory('NoteService', function () {
//    var serv = {};
//
//    serv.getNotes = function() {
//        $http.get('note/feed').then(function(response) {
//          self.notes = response.data;
//        });
//    };
//    return serv;
//});

routingApp.factory('NoteService', ['$http', function($http) {
    var serv = {};

        serv.getNotes = function(cb) {
            $http.get('note/feed').then(function(resp) {
              cb(resp.data);
            });
        };
        return serv;
}]);
