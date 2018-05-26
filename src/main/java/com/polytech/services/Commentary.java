package com.polytech.services;

public class Commentary {
    public String content ;
    public String author ;
    private int id_Note ;

    public Commentary(String content, String author) {
        this.content = content ;
        this.author = author ;
    }

    public Commentary(String content, String author, int id_Note) {
        this.content = content ;
        this.author = author ;
        this.id_Note = id_Note ;
    }

    public String getContent() {
        return this.content ;
    }

    public int getIdNote() {
        return this.id_Note ;
    }
}
