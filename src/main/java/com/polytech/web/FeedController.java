package com.polytech.web;

import com.polytech.services.FeedService;
import com.polytech.services.Note;
import com.polytech.services.PublicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Controller contenant l'ensemble des actions possibles pour un utilisateur.
 */
@RestController
public class FeedController {
    private PublicationService publicationService;          // Service d'ecriture sur la base de donnee
    private FeedService feedService;                        // Service de lecture de la base de donnee

    /**
     * Constructeur
     *
     * @param publicationService Service d'ecriture sur la BD
     * @param feedService Service de lecture de la BD
     */
    public FeedController(PublicationService publicationService, FeedService feedService) {
        this.publicationService = publicationService;
        this.feedService = feedService;
    }

    /**
     *
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/note", method = RequestMethod.GET)
    public Note get(@RequestParam(value = "id") int id) {
        return this.feedService.fetchNote(id);
    }

    /**
     * Demande de suppression d'une note a partir de son identifiant.
     *
     * @param id Identifiant de la note a supprimer.
     */
    @RequestMapping(value = "/note", method = RequestMethod.DELETE)
    public void delete(@RequestParam(value="id") int id) {
        publicationService.remove(id);
    }

    /**
     * Demande de sauvegarde d'une note a partir d'un contenu saisi par l'utilisateur.
     *
     * @param content Contenu de la note.
     * @return Renvoie true si bon fonctionnement.
     */
    @RequestMapping(value = "/note", method = RequestMethod.POST)
    public boolean post(@RequestBody() Note content) {
    //public boolean post(@RequestParam(value = "content") String content) {
        publicationService.post(content);
        return true;
    }

    /**
     * Demande de modification d'une note avec un identifiant donne et le texte modifie.
     *
     * @param id Identifiant de la note a modifier.
     * @param content Nouveau contenu de la note.
     */
    @RequestMapping(value = "/note/update", method = RequestMethod.POST)
    public void update(@RequestParam(value="id") int id, @RequestParam(value = "content") String content) {
        publicationService.update(id, content);
    }

    /**
     * Demande de recuperation de l'ensemble des notes.
     *
     * @return Renvoie une liste de toutes les notes connues.
     */
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public List<Note> feed() {
        List<Note> notes = this.feedService.fetchAll();
        return notes;
    }
}
