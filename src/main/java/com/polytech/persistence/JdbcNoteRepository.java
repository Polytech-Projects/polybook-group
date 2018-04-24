package com.polytech.persistence;

import com.polytech.services.Note;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Communication avec une base de donnee SQL (via JDBC)
 */
public class JdbcNoteRepository implements NoteRepository {

    private Connection connection;              // Connection a la base de donnee

    public JdbcNoteRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Sauvegarde dans la base de donnee : insertion SQL
     *
     * A PROTEGER DES INJECTIONS SQL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     * @param note Note a sauvegarder.
     */
    public void save(Note note) {
        String query = "INSERT INTO NOTE (CONTENT)VALUES('" + note.getContent() + "')";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Suppression d'une note de la base de donnee : DELETE sql
     *
     * A PROTEGER DES INJECTIONS SQL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     * @param ID Identifiant de la note a supprimer.
     */
    @Override
    public void remove(int ID) {
        String query = "DELETE FROM NOTE WHERE ID =" + ID + " ;";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modification d'une note donnee : UPDATE sql
     *
     * A PROTEGER DES INJECTIONS SQL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     * @param id identification  de la note a modifier.
     * @param content Nouveau contenu de la note.
     */
    @Override
    public void update(int id, String content) {
        String query = "UPDATE NOTE SET CONTENT = '" + content + "' WHERE ID = " + id + ";";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recupere l'ensemble des notes contenues dans la base de donnee via un simple "SELECT" sql.
     *
     * @return Liste des notes contenues dans la base de donne
     */
    public List<Note> findAll() {
        String query = "SELECT * FROM NOTE";
        List<Note> stories = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {                                  // Pour chaque enregistrement
                int ID = resultSet.getInt("ID") ;
                String content = resultSet.getString("CONTENT");
                stories.add(new Note(ID, content));                         // Ajouter la note associee dans la liste
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stories;
    }

    /**
     * Recupere une note precise dans la base de donnee a partir de son identifiant.
     *
     * A PROTEGER DES INJECTIONS SQL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     * @param id Identifiant de la note a recuperer.
     *
     * @return La note correspondante
     */
    @Override
    public Note find(int id) {
        Note note = null;
        String query = "SELECT * FROM NOTE WHERE ID = " + id + ";";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID") ;
                String content = resultSet.getString("CONTENT");
                note = new Note(ID, content);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return note;
    }
}
