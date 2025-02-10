package org.main.service;

import org.main.humain.Auteur;

import java.sql.PreparedStatement;
import java.util.List;

public interface AuteurManager {
    void updateAuteur (Auteur a) ;
    void ajoutAuteur (Auteur a) ;
    void supprimerAuteur (Auteur a) ;
    Auteur findAuteurByid (int id) ;
    List<Auteur> getAuteurs();
}
