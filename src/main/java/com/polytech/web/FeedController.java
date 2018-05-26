package com.polytech.web;

import com.polytech.services.FeedService;
import com.polytech.services.Note;
import com.polytech.services.PublicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
     * Demande de suppression d'une note a partir de son identifiant.
     *
     * @param id Identifiant de la note a supprimer.
     */
    @RequestMapping(value = "/note/remove", params="id", method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id, Principal principal) {
        publicationService.remove(id, principal.getName());
        return "redirect:/notes";
    }

    /**
     * Demande de sauvegarde d'une note a partir d'un contenu saisi par l'utilisateur.
     *
     * @param content Contenu de la note.
     * @return Renvoie true si bon fonctionnement.
     */
    @RequestMapping(value = "/note/share", method = RequestMethod.POST)
    public String post(String content, Principal principal) {
        publicationService.post(content, principal.getName());
        return "redirect:/notes";
    }

    /**
     * Demande de modification d'une note avec un identifiant donne et le texte modifie.
     *
     * @param id Identifiant de la note a modifier.
     * @param content Nouveau contenu de la note.
     */
    @RequestMapping(value = "/note/update", params = { "id", "content" }, method = RequestMethod.POST)
    public String update(@RequestParam(value="id") int id,String content, Principal principal) {
        publicationService.update(id, content, principal.getName());
        return "redirect:/notes";
    }

    /**
     * Demande de modification d'une note avec un identifiant donne et le texte modifie.
     *
     * @param id Identifiant de la note a modifier.
     */
    @RequestMapping(value = "/note/modify", params = { "id"}, method = RequestMethod.GET)
    public String modify(@RequestParam(value="id") int id, Model model, Principal principal) {
        List<Note> notes = feedService.find(id) ;
        Note note = notes.get(0) ;

        model.addAttribute("toModifyContent", note.content) ;
        model.addAttribute("toModifyId", id) ;
        model.addAttribute("username", principal.getName()) ;
        return "modifyNote" ;
    }

    /**
     * Demande de recuperation de l'ensemble des notes.
     *
     * @return La page d'affichage des notes.
     */
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public String feed(Principal principal, Model model) {
        List<Note> notes = this.feedService.fetchAll(principal.getName());
        model.addAttribute("notes", notes);
        return "notes";
    }

    @RequestMapping(value = "/notes/addCollaborateur", method = RequestMethod.POST)
    public String addCollaborateur(@RequestParam(value="id") int id, String collaborateur, Principal principal, Model model) {
        publicationService.addCollaborateur(id, collaborateur, principal.getName()) ;

        return "redirect:/note/modify?id="+id;
    }

    @RequestMapping(value = "/notes/collaborationNotes", method = RequestMethod.GET)
    public String feedNotesInCollaboration(Principal principal, Model model) {
        List<Note> notesCollaboration = this.feedService.fetchAllCollaborationNote(principal.getName());
        model.addAttribute("notes", notesCollaboration);

        return "collaborationNotes";
    }
}
