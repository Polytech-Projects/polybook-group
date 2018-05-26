package com.polytech.web;

import com.polytech.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

/**
 * Controller lié aux commentaires
 */
@Controller
public class CommentaryController {
    private CommentaryFeedService commentaryFeedService;
    private CommentaryPublicationService commentaryPublicationService;
    private FeedService feedService;

    public CommentaryController(CommentaryFeedService commentaryFeedService, CommentaryPublicationService commentaryPublicationService, FeedService feedService) {
        this.commentaryFeedService = commentaryFeedService ;
        this.commentaryPublicationService = commentaryPublicationService ;
        this.feedService = feedService ;
    }

    /**
     * Ajout d'un commentaire
     *
     * @param id_Note
     * @param content
     * @param principal
     * @return
     */
    @RequestMapping(value = "/commentary/share", method = RequestMethod.POST)
    public String post(@RequestParam(value="id_Note") int id_Note,String content, Principal principal) {
        commentaryPublicationService.post(id_Note, content, principal.getName());
        return "redirect:/commentary/noteDetails?id="+id_Note;
    }

    @RequestMapping(value= "/commentary/noteDetails", method = RequestMethod.GET)
    public String noteDetails(@RequestParam(value="id") int id, Principal principal, Model model) {
        List<Note> result = feedService.find(id) ;
        Note note = result.get(0) ;

        model.addAttribute("noteDetailsContent", note.content) ;
        model.addAttribute("noteDetailsId", id) ;

        List<Commentary> commentaries = this.commentaryFeedService.fetchAll(id);
        model.addAttribute("commentaries", commentaries);

        return "noteDetails" ;
    }
}
