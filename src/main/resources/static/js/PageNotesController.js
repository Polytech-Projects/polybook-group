routingApp.controller('PageNotesController', ['$scope', '$http',
'NoteService', function ($scope, $http, NoteService) {
    $scope.notes = [];

    $scope.updateNotes = function() {
        NoteService.getNotes(function(noteSet) {
            $scope.notes = noteSet;
        });
    };

    $scope.text = '...';
    $scope.submit = function() {
        if ($scope.text) {
            NoteService.addNote($scope.text, function(resp) {
                if (resp === true) {
                    // on reset la note
                    $scope.text = '...';
                    // on met à jour nos notes
                    $scope.updateNotes();
                }
                else
                    alert('UNE ERREUR MON POTE');
            });
        }
    };

    $scope.updateNotes();
}]);