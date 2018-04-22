package com.polytech.web;

import com.polytech.services.FeedService;
import com.polytech.services.Note;
import com.polytech.services.PublicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
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

    @RequestMapping(value = "/note/feed", method = RequestMethod.GET)
    public List<Note> feed() {
        List<Note> notes = this.feedService.fetchAll() ;
        return notes;
    }

    @RequestMapping(value = "/note/delete", method = RequestMethod.DELETE)
    public boolean delete(@RequestParam(value="id") int id) {
        publicationService.remove(id);
        return true;
    }
}
