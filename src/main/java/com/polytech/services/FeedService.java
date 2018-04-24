package com.polytech.services;

import com.polytech.persistence.NoteRepository;

import java.util.List;

public class FeedService {

    private NoteRepository noteRepository;

    public FeedService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> fetchAll() {
        return noteRepository.findAll();
    }

    public Note fetchNote(int id) {
        return noteRepository.find(id);
    }
}
