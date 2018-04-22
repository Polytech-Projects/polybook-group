var routingApp = angular.module('routingApp', ['ui.router']);
routingApp.config(function($stateProvider) {

    var pageNotesState = {
            name: 'notes',
            url: '/notes',
            templateUrl: 'NotesPage.html',
            controller: 'PageNotesController'
    };

    $stateProvider.state(pageNotesState);
});