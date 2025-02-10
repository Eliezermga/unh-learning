package org.main.humain;

public class Auteur  {

    private  int id;
    private  String nom;
    private  String email;
    private Sexe sexe ;
private Specialites  specialites;

    public Specialites getSpecialites() {
        return specialites;
    }

    public void setSpecialites(Specialites specialites) {
        this.specialites = specialites;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
