routingApp.controller('PageNotesController', ['$scope', '$http',
'NoteService', function ($scope, $http, NoteService) {
    $scope.notes = [];

    $scope.updateNotes = function() {
        NoteService.getNotes(function(noteSet) {
            $scope.notes = noteSet;
        });
        console.log($scope.notes);
    };

    $scope.updateNotes();
}]);