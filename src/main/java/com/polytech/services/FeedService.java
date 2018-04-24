package com.polytech.services;

import com.polytech.persistence.NoteRepository;

import java.util.List;

/**
 * Service de lecture de l'espace de stockage.
 */
public class FeedService {
    private NoteRepository noteRepository;          // Interface de communication avec la couche Persistence.

    /**
     * Constructeur.
     *
     * @param noteRepository Interface de communication
     */
    public FeedService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Demande de recuperation de l'ensemble des notes sauvegardees.
     *
     * @return Liste des notes sauvegardees.
     */
    public List<Note> fetchAll() {
        return noteRepository.findAll();
    }

    /**
     * Demande de lecture d'une note avec un identifiant donne.
     *
     * @param id Identifiant de la note a recuperer.
     *
     * @return Renvoie la note associee.
     */
    public Note fetchNote(int id) {
        return noteRepository.find(id);
    }
}
