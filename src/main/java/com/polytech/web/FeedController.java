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

@RestController
public class FeedController {

    private PublicationService publicationService;
    private FeedService feedService;

    public FeedController(PublicationService publicationService, FeedService feedService) {
        this.publicationService = publicationService;
        this.feedService = feedService;
    }

    @RequestMapping(value = "/note", method = RequestMethod.GET)
    public Note get(@RequestParam(value = "id") int id) {
        return this.feedService.fetchNote(id);
    }

    @RequestMapping(value = "/note", method = RequestMethod.DELETE)
    public void delete(@RequestParam(value="id") int id) {
        publicationService.remove(id);
    }

    @RequestMapping(value = "/note", method = RequestMethod.POST)
    public boolean post(@RequestBody() Note content) {
    //public boolean post(@RequestParam(value = "content") String content) {
        publicationService.post(content);
        return true;
    }

    @RequestMapping(value = "/note/update", method = RequestMethod.POST)
    public void update(@RequestParam(value="id") int id, @RequestParam(value = "content") String content) {
        publicationService.update(id, content);
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public List<Note> feed() {
        List<Note> notes = this.feedService.fetchAll();
        return notes;
    }
}
