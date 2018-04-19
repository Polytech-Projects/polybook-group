package com.polytech.web;

import com.polytech.services.FeedService;
import com.polytech.services.Note;
import com.polytech.services.PublicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FeedController {

    private PublicationService publicationService;
    private FeedService feedService;

    public FeedController(PublicationService publicationService, FeedService feedService) {
        this.publicationService = publicationService;
        this.feedService = feedService;
    }

    @RequestMapping(value = "/share", method = RequestMethod.POST)
    public String post(String content) {
        publicationService.post(content) ;
        return "redirect:/feed";
    }

    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public String feed(Model model) {
        List<Note> notes = this.feedService.fetchAll() ;
        model.addAttribute("notes", notes) ;
        return "feed" ;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam int ID) {
        this.publicationService.remove(ID) ;
        return "feed" ;
    }
}
