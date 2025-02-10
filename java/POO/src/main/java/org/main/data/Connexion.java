package org.main.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
    private Connection con;
    static String DB= "bibliotheque";
    static String USER = "root";
    static String Password = "";
    static String Host = "localhost";
    static String Port = "3306";
    static String URL = "jdbc:mysql://" + Host + ":" + Port + "/" + DB;

    public Connexion () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL ,USER , Password);
        } catch (Exception exception){
            con = null;
        }
    }

    public Connection getCon() {
        return con;
    }
}
