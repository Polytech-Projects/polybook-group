package com.polytech.services;

import com.polytech.persistence.CollaborateurRepository;

/**
 * Collaborateur : ecriture sur la base de donnée.
 */
public class CollaborateurPublicationService {
    private CollaborateurRepository collaborateurRepository;

    public CollaborateurPublicationService(CollaborateurRepository collaborateurRepository) {
        this.collaborateurRepository = collaborateurRepository;
    }

    /**
     * Demande d'ajout d'un lien de collaboration.
     * @param demandeur Utilisateur a l'origine de la requete
     * @param collaborateur Nom du collaborateur.
     */
    public void post(String demandeur, String collaborateur) {
        collaborateurRepository.save(new Collaborateur(collaborateur), demandeur);
    }
}
