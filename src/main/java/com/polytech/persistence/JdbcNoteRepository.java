package com.polytech.persistence;

import com.polytech.services.Note;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcNoteRepository implements NoteRepository {


    private Connection connection;


    public JdbcNoteRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(Note note) {
        String query = "INSERT INTO NOTE (CONTENT)VALUES('" + note.getContent() + "')";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public List<Note> findAll() {
        String query = "SELECT * FROM NOTE";
        List<Note> stories = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int ID = resultSet.getInt("ID") ;
                String content = resultSet.getString("CONTENT");
                stories.add(new Note(ID, content));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stories;
    }
}
