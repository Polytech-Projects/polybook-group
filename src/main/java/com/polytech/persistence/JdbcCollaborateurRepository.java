package com.polytech.persistence;

import com.polytech.services.Collaborateur;
import com.polytech.services.Commentary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Lien a la base de donnée, en lien avec les collaborateurs. ( a l'aide de JDBC )
 */
public class JdbcCollaborateurRepository implements CollaborateurRepository {
    JdbcTemplate jdbcTemplate ;             // Connection a la BD

    /**
     * Constructeur
     *
     * @param jdbcTemplate Connection a la BD
     */
    public JdbcCollaborateurRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate ;
    }

    /**
     * Creation d'un lien de collaboration entre deux utilisateurs. Requete insert sur la table Collaborateur.
     *
     * @param collaborateur Nom du collaborateur à rajouter.
     * @param demandeur Utilisateur a l'origine de la requete.
     */
    @Override
    public void save(Collaborateur collaborateur, String demandeur) {
                                                                                                // Verification de l'existance du collaborateur
        String query =  "SELECT count(*) FROM users WHERE username = ?;" ;
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, collaborateur.name);

        boolean resulta = (count > 0) ? true : false ;

                                                                                                // Verification pas d'existance d'un lien de collaboration
        query =  "SELECT count(*) FROM COLLABORATEUR WHERE (CollaborateurA = ? AND CollaborateurB = ?) OR (CollaborateurA = ? AND CollaborateurB = ?);" ;
        count = jdbcTemplate.queryForObject(query, Integer.class, collaborateur.name, demandeur, demandeur, collaborateur.name);

        boolean resultb = (count > 0) ? false : true ;

                        // Si collaborateur existe et pas deja collaborateur alors ajout dans la base de donnée.
        if (resulta && resultb) {
            query = "INSERT INTO COLLABORATEUR (CollaborateurA, CollaborateurB)VALUES(?,?)";
            jdbcTemplate.update(query, demandeur, collaborateur.name);
        }
    }

    /**
     * Recherche de l'ensemble des collaborateurs pour un utilisateur donné.
     *
     * @param username Nom d'utilisateur du demandeur.
     *
     * @return Renvoie l'ensemble des collaborateurs de cet utilisateur.
     */
    @Override
    public List<Collaborateur> findAll(String username) {
        String query = "SELECT * FROM COLLABORATEUR WHERE CollaborateurA = '" + username + "' OR CollaborateurB = '" + username + "' ;";
        return jdbcTemplate.query(query, new CollaborateurMapper(username));
    }

    /**
     * Recuperation de l'ensemble des collaborateurs a partir d'une requete SELECT.
     */
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
