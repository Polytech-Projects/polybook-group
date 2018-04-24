package com.polytech.persistence;

import com.polytech.services.Note;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNoteRepository implements NoteRepository {

    private static List<Note> database = new ArrayList<>();

    public List<Note> findAll() {
        return database;
    }

    @Override
    public Note find(int id) {
        return null;
    }

    @Override
    public void save(Note note) {
        database.add(note) ;
    }

    @Override
    public void remove(int ID) {

    }

    @Override
    public void update(int id, String content) {

    }
}
