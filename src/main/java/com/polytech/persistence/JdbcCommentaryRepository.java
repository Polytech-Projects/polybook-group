package com.polytech.persistence;

import com.polytech.services.Commentary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcCommentaryRepository implements CommentaryRepository {
    JdbcTemplate jdbcTemplate ;

    public JdbcCommentaryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate ;
    }

    @Override
    public void save(Commentary commentary, String userName) {
        String query = "INSERT INTO COMMENTARY (CONTENT, username, ID_NOTE)VALUES(?,?,?)" ;
        jdbcTemplate.update(query, commentary.getContent(), userName, commentary.getIdNote());
    }

    @Override
    public List<Commentary> findAll(int id_Note) {
        String query = "SELECT * FROM COMMENTARY WHERE ID_NOTE = '" + id_Note + "' ;";
        return jdbcTemplate.query(query, new NoteMapper());
    }

    class NoteMapper implements RowMapper<Commentary> {

        @Override
        public Commentary mapRow(ResultSet rs, int rowNum) throws SQLException {
            String content = rs.getString("CONTENT");
            String username = rs.getString("username") ;
            return new Commentary(content, username);
        }
    }
}
