package com.polytech.persistence;

import com.polytech.services.Collaborateur;

import java.util.List;

public interface CollaborateurRepository {
    List<Collaborateur> findAll(String username);

    void save(Collaborateur collaborateur, String demandeur);
}
