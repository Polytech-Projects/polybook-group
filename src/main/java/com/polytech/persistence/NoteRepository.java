package com.polytech.persistence;

import com.polytech.services.Note;

import java.util.List;

/**
 * Interface de communication avec la base de donnee.
 */
public interface NoteRepository {
    /**
     * Recuperation de l'ensemble des notes contenues dans la BD.
     *
     * @return Renvoie la liste des notes connues.
     */
    List<Note> findAll();

    /**
     * Recuperation d'une note particuliere a partir de son identifiant.
     *
     * @param id Identifiant de la note a recuperer.
     *
     * @return Renvoie la note correspondante.
     */
    Note find(int id);

    /**
     * Sauvegarde une note dans la base de donnee.
     *
     * @param note Note a sauvegarder.
     */
    void save(Note note);

    /**
     * Supprime une note a partir de son identifiant.
     *
     * @param ID Identifiant de la note a supprimer.
     */
    void remove(int ID) ;

    /**
     * Modification d'une note.
     *
     * @param id identification  de la note a modifier.
     * @param content Nouveau contenu de la note.
     */
    void update(int id, String content);
}
