package org.main.service;


import org.main.biblo.Publication;
import java.sql.SQLException;
import java.util.List;

public interface PublicationManager {
    List<Publication> getPublications() throws SQLException;
    void savePublication(int idLivre, List<Integer> idAuteurs) throws SQLException;
}
