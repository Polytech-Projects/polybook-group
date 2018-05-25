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
@Controller
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
    @RequestMapping(value = "/note/get", method = RequestMethod.GET)
    public Note get(@RequestParam(value = "id") int id) {
        return this.feedService.fetchNote(id);
    }

    /**
     * Demande de suppression d'une note a partir de son identifiant.
     *
     * @param id Identifiant de la note a supprimer.
     */
    @RequestMapping(value = "/note/remove", params="id", method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id) {
        publicationService.remove(id);
        return "redirect:/notes";
    }

    /**
     * Demande de sauvegarde d'une note a partir d'un contenu saisi par l'utilisateur.
     *
     * @param content Contenu de la note.
     * @return Renvoie true si bon fonctionnement.
     */
    @RequestMapping(value = "/note/share", method = RequestMethod.POST)
    public String post(String content) {
        publicationService.post(content);
        return "redirect:/notes";
    }

    /**
     * Demande de modification d'une note avec un identifiant donne et le texte modifie.
     *
     * @param id Identifiant de la note a modifier.
     * @param content Nouveau contenu de la note.
     */
    @RequestMapping(value = "/note/update", params = { "id", "content" }, method = RequestMethod.POST)
    public String update(@RequestParam(value="id") int id,String content) {
        publicationService.update(id, content);
        return "redirect:/notes";
    }

    /**
     * Demande de modification d'une note avec un identifiant donne et le texte modifie.
     *
     * @param id Identifiant de la note a modifier.
     * @param content Nouveau contenu de la note.
     */
    @RequestMapping(value = "/note/modify", params = { "id", "content" }, method = RequestMethod.GET)
    public String modify(@RequestParam(value="id") int id, @RequestParam(value = "content") String content, Model model) {
        model.addAttribute("toModifyContent", content) ;
        model.addAttribute("toModifyId", id) ;
        return "modifyNote" ;
    }

    /**
     * Demande de recuperation de l'ensemble des notes.
     *
     * @return La page d'affichage des notes.
     */
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public String feed(Model model) {
        List<Note> notes = this.feedService.fetchAll();
        model.addAttribute("notes", notes);
        return "notes";
    }
}
