package com.polytech.services;

import com.polytech.persistence.NoteRepository;


public class PublicationService {

    private NoteRepository noteRepository;

    public PublicationService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void post(String note) {
        this.noteRepository.save(new Note(note)) ;
    }

    public void remove(int ID) {
        this.noteRepository.remove(ID) ;
    }
}
