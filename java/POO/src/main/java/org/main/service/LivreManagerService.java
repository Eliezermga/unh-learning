package org.main.service;

import org.main.data.Connexion;
import org.main.biblo.Livre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LivreManagerService implements LivreManager{
    Connexion connexion = new Connexion();
     Statement statement;
     ResultSet resultSet;

    @Override
    public void updateLivre(Livre a) {
        String query2 = "UPDATE Livres SET titre = ? ,  date_publication = ?, nbres_pages = ? WHERE id = ?";
        PreparedStatement state = null;

        try {
            state = connexion.getCon().prepareStatement(query2);
            state.setString (1, a.getTitre());
            state.setDate (2, a.getDatePublication());
            state.setInt(3, a.getNombrePages());
            state.setInt (4, a.getId());
            state.execute();
        } catch (SQLException e ){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ajoutLivre(Livre a) {

        String query = "INSERT INTO Livres (titre , date_publication, nbres_pages) VALUES (?,?,?)";
        PreparedStatement state = null;

        try {
            state = connexion.getCon().prepareStatement(query);
            state.setString (1, a.getTitre());
            state.setDate (2, a.getDatePublication());
            state.setInt(3, a.getNombrePages());
            state.execute();
        } catch (SQLException e ){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void supprimerLivre(Livre a) {

        String query2 = "DELETE FROM Livres WHERE id = ?";
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
    public Livre findLivreByid(int id) {
        return null;
    }

    @Override
    public List<Livre> getLivres() {
        List<Livre> livres = new ArrayList<Livre>();
        try {
            statement = connexion.getCon().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM livres ");
            while (resultSet.next()){
                Livre livre= new Livre();
                livre.setId(resultSet.getInt("id"));
                livre.setTitre(resultSet.getString("titre"));
                livre.setDatePublication(resultSet.getDate("date_Publication"));
                livre.setNombrePages(resultSet.getInt("nbres_Pages"));
                livres.add(livre);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livres;
    }
}







