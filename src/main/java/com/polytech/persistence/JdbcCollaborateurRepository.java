package com.polytech.persistence;

import com.polytech.services.Collaborateur;
import com.polytech.services.Commentary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcCollaborateurRepository implements CollaborateurRepository {
    JdbcTemplate jdbcTemplate ;

    public JdbcCollaborateurRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate ;
    }

    @Override
    public void save(Collaborateur collaborateur, String demandeur) {
        String query =  "SELECT count(*) FROM users WHERE username = ?;" ;
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, collaborateur.name);

        boolean resulta = (count > 0) ? true : false ;

        query =  "SELECT count(*) FROM COLLABORATEUR WHERE (CollaborateurA = ? AND CollaborateurB = ?) OR (CollaborateurA = ? AND CollaborateurB = ?);" ;
        count = jdbcTemplate.queryForObject(query, Integer.class, collaborateur.name, demandeur, demandeur, collaborateur.name);

        boolean resultb = (count > 0) ? false : true ;


        if (resulta && resultb) {
            query = "INSERT INTO COLLABORATEUR (CollaborateurA, CollaborateurB)VALUES(?,?)";
            jdbcTemplate.update(query, demandeur, collaborateur.name);
        }
    }

    @Override
    public List<Collaborateur> findAll(String username) {
        String query = "SELECT * FROM COLLABORATEUR WHERE CollaborateurA = '" + username + "' OR CollaborateurB = '" + username + "' ;";
        return jdbcTemplate.query(query, new CollaborateurMapper(username));
    }

    class CollaborateurMapper implements RowMapper<Collaborateur> {
        private String demandeur ;

        public CollaborateurMapper(String demandeur) {
            this.demandeur = demandeur ;
        }

        @Override
        public Collaborateur mapRow(ResultSet rs, int rowNum) throws SQLException {
            String collaborateur = rs.getString("CollaborateurA");

            if (collaborateur == demandeur) collaborateur = rs.getString("CollaborateurB") ;

            return new Collaborateur(collaborateur);
        }
    }
}
