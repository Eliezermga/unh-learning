package org.main.service;

import org.main.data.Connexion;
import org.main.humain.Auteur;
import org.main.humain.Sexe;
import org.main.humain.Specialites;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuteurManagerService implements AuteurManager{
    Connexion connexion = new Connexion();
     Statement statement;
     ResultSet resultSet;

    @Override
    public void updateAuteur(Auteur a) {
        String query2 = "UPDATE Auteur SET nom = ? ,  email = ? , sexe= ?, specialites = ? WHERE id = ?";
        PreparedStatement state = null;

        try {
            state = connexion.getCon().prepareStatement(query2);
            state.setString (1, a.getNom());
            state.setString (2, a.getEmail());
            state.setString(3, a.getSexe().toString());
            state.setString(4, a.getSpecialites().toString());
            state.setInt (5, a.getId());
            state.execute();
        } catch (SQLException e ){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ajoutAuteur(Auteur a) {

        String query = "INSERT INTO Auteur (nom , email,sexe,specialites) VALUES (?,?,?,?)";
        PreparedStatement state = null;

        try {
            state = connexion.getCon().prepareStatement(query);
            state.setString (1, a.getNom());
            state.setString (2, a.getEmail());
            state.setString(3, a.getSexe().toString());
            state.setString(4, a.getSpecialites().toString());
            state.execute();
        } catch (SQLException e ){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void supprimerAuteur(Auteur a) {

        String query2 = "DELETE FROM Auteur WHERE id = ?";
        PreparedStatement state = null;

        try {
            state = connexion.getCon().prepareStatement(query2);
            state.setInt (1, a.getId());
            state.execute();
        } catch (SQLException e ){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Auteur findAuteurByid(int id) {
        return null;
    }

    @Override
    public List<Auteur> getAuteurs() {
        List<Auteur> auteurs = new ArrayList<Auteur>();
        try {
            statement = connexion.getCon().createStatement();
            resultSet = statement.executeQuery("select * from auteur");
            while (resultSet.next()){
                Auteur auteur= new Auteur();
                auteur.setId(resultSet.getInt("id"));
                auteur.setNom(resultSet.getString("nom"));
                auteur.setEmail(resultSet.getString("email"));
                try {
                    auteur.setSexe((Sexe.valueOf(resultSet.getString("sexe"))));
                }catch (Exception e){
                    auteur.setSexe((Sexe.NULL));
                }
                try{
                    auteur.setSpecialites((Specialites.valueOf(resultSet.getString("specialites"))));
                }catch (Exception e){
                    auteur.setSpecialites((Specialites.NULL));
                }

                auteurs.add(auteur);
              //  System.out.println(resultSet.getInt("id")+" "+resultSet.getString("nom")+" "+resultSet.getString("email"));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return auteurs;
    }
}







