package org.main.service;

//import con.Connexion;
//import humain.Auteur;
//import humain.Sexe;
//import humain.Specialites;
//import librairie.Livre;
//import librairie.Publication;

import org.main.biblo.Publication;
import org.main.data.Connexion;
import org.main.service.PublicationManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class PublicationManagerService implements PublicationManager {
    private Statement statement;
    private ResultSet resultSet;
    private Connexion connexion = new Connexion();

    @Override
    public List<Publication> getPublications() throws SQLException {
        List<Publication> publications = new ArrayList<>();
        statement = connexion.getCon().createStatement();
        resultSet = statement.executeQuery(
                "SELECT livres.id as id, livres.titre AS titre, GROUP_CONCAT(auteur.nom ORDER BY auteur.nom ASC SEPARATOR ', ') AS auteurs FROM     publication JOIN     auteur ON publication.idAuteur = auteur.id JOIN     livres ON publication.idLivre = livres.id GROUP BY     livres.id ORDER BY     livres.titre ASC;");
        int code;
        String title;
        Publication publication;
        while (resultSet.next()) {
            publication = new Publication();
            publication.setId(resultSet.getInt("id"));
            publication.setTitre(resultSet.getString("titre"));
            publication.setAuteurs(resultSet.getString("auteurs"));

            publications.add(publication);
        }
        resultSet.close();
        statement.close();
        return publications;
    }

    @Override
    public void savePublication(int idLivre, List<Integer> idAuteurs) throws SQLException {
        String query = "INSERT INTO publication (idAuteur, idLivre) VALUES (?, ?)";
        PreparedStatement state = connexion.getCon().prepareStatement(query);
        for (int id : idAuteurs) {
            state.setInt (1, id);
            state.setInt (2, idLivre);
            state.execute();
        }

    }

}
