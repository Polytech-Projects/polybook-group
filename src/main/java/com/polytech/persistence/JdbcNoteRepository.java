package com.polytech.persistence;

import com.polytech.services.Note;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Communication avec une base de donnee SQL (via JDBC)
 */
public class JdbcNoteRepository implements NoteRepository, LoginRepository {

    private Connection connection;              // Connection a la base de donnee
    private JdbcTemplate jdbcTemplate;

    public JdbcNoteRepository(Connection connection, JdbcTemplate jdbcTemplate) {
        this.connection = connection;
        this.jdbcTemplate = jdbcTemplate ;
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
        List<Note> notes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {                                  // Pour chaque enregistrement
                int id = resultSet.getInt("ID") ;
                String content = resultSet.getString("CONTENT");
                notes.add(new Note(id, content));                         // Ajouter la note associee dans la liste
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
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

    @Override
    public void addUser(String userName, String password) {
        String query ;

        query = "INSERT INTO USERS VALUES('test', 'test', true);" ;
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);

            query = "INSERT INTO AUTHORITIES VALUES('test', 'USER');" ;
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("ICICICIC") ;

        this.findAllUser();
    }

    public void findAllUser() {
        String query = "SELECT * FROM users";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {                                  // Pour chaque enregistrement
                String username = resultSet.getString("username") ;
                String password = resultSet.getString("password");

                System.out.println("USERNAME : " + username + "  PASSWORD : " + password) ;
            }

            query = "SELECT * FROM authorities";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {                                  // Pour chaque enregistrement
                String username = resultSet.getString("username") ;
                String password = resultSet.getString("authority");

                System.out.println("USERNAME : " + username + "  authority : " + password) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
