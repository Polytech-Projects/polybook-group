package com.polytech.services;

import com.polytech.persistence.CollaborateurRepository;

import java.util.List;

public class CollaborateurFeedService {
    private CollaborateurRepository collaborateurRepository;

    public CollaborateurFeedService(CollaborateurRepository collaborateurRepository) {
        this.collaborateurRepository = collaborateurRepository;
    }

    public List<Collaborateur> fetchAll(String username) {
        return collaborateurRepository.findAll(username);
    }
}
