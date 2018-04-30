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

    serv.addNote = function (text, cb) {
        var req = {
            content: text
        };

        $http.post('/note', req).then(function (resp) {
            //console.log(resp);
            console.log(resp);
            cb(resp.data);
        });

    };

        serv.getNotes = function(cb) {
            $http.get('notes').then(function(resp) {
              cb(resp.data);
            });
        };

        serv.getNote = function(cb) {
           $http.get('note').then(function(resp) {
             cb(resp.data);
           });
       };

       serv.deleteNote =  function(id, cb) {
           var req = {
               id: id
           };
             $http.delete('note/delete', req) .then(function(resp) {
               cb(resp.data);
             });
         };

       serv.postNote = function (noteContent, cb) {
            var req = {
              content: noteContent
            };

            $http.post('/note',req).then(function (resp) {
                    //console.log(resp);
                    cb(resp.data.success);
            });
        };
       serv.updateNote = function (taskName, cb) {
            var req = {
              taskName: taskName
            };

            $http.post('/note/update',req).then(function (resp) {
                    //console.log(resp);
                    cb(resp.data.success);
                    $scope.counter = dt.data.counter
                });

        };

        return serv;
}]);
