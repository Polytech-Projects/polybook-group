package com.polytech.services;

import com.polytech.persistence.CollaborateurRepository;

public class CollaborateurPublicationService {
    private CollaborateurRepository collaborateurRepository;

    public CollaborateurPublicationService(CollaborateurRepository collaborateurRepository) {
        this.collaborateurRepository = collaborateurRepository;
    }

    public void post(String demandeur, String collaborateur) {
        collaborateurRepository.save(new Collaborateur(collaborateur), demandeur);
    }
}
