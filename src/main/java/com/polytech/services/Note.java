package com.polytech.services;

import java.util.Objects;

public class Note {
    private int ID ;
    private String content;

    public Note(String content) {
        this.content = content;
    }

    public Note(int ID, String content) {
        this.ID = ID ;
        this.content = content ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(content, note.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
