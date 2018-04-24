package com.polytech.persistence;

import com.polytech.services.Note;

import java.util.List;

public interface NoteRepository {
    List<Note> findAll();

    Note find(int id);

    void save(Note note);

    void remove(int ID) ;

    void update(int id, String content);
}
