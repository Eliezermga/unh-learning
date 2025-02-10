package org.main.biblo;

import org.main.humain.Auteur;

import java.util.ArrayList;
import java.util.List;

public class Publication {
    private Integer id;
    private String auteurs;
    private String titre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(String auteurs) {
        this.auteurs = auteurs;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
