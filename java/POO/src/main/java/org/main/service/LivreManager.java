package org.main.service;

import org.main.biblo.Livre;

import java.util.List;

public interface LivreManager {


    void updateLivre(Livre a);

    void ajoutLivre(Livre a);

    void supprimerLivre(Livre a);

    Livre findLivreByid(int id);

    List<Livre> getLivres();
}
