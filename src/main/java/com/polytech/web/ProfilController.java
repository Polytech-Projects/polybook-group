package com.polytech.web;

import com.polytech.services.Collaborateur;
import com.polytech.services.CollaborateurFeedService;
import com.polytech.services.CollaborateurPublicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfilController {

    private CollaborateurFeedService collaborateurFeedService ;
    private CollaborateurPublicationService collaborateurPublicationService ;

    public ProfilController(CollaborateurFeedService collaborateurFeedService, CollaborateurPublicationService collaborateurPublicationService) {
        this.collaborateurFeedService = collaborateurFeedService ;
        this.collaborateurPublicationService = collaborateurPublicationService ;
    } ;

    @RequestMapping(value = "/profil", method = RequestMethod.GET)
    public String modify(Principal principal, Model model) {
        model.addAttribute("username", principal.getName()) ;

        List<Collaborateur> ensCollab = collaborateurFeedService.fetchAll(principal.getName()) ;

        model.addAttribute("collaborateurs", ensCollab) ;

        return "profil" ;
    }

    @RequestMapping(value = "/profil/addCollaborateur", method = RequestMethod.POST)
    public String addCollaborateur(@RequestParam(value="demandeur") String demandeur, String collaborateur, Principal principal, Model model) {
        collaborateurPublicationService.post(principal.getName(), collaborateur) ;

        System.out.println(demandeur + " " + collaborateur) ;

        return "redirect:/profil" ;
    }
}
