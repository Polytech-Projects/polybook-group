package com.polytech.persistence;

import com.polytech.services.Note;

import java.util.List;

public interface NoteRepository {
    List<Note> findAll();

    void save(Note note);

    void remove(int ID) ;
}
