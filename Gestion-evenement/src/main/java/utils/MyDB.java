package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {
    final String URL = "jdbc:mysql://localhost:3306/3a20"; // Modifier le nom de la base de données ici
    final String USER = "root"; // Votre nom d'utilisateur MySQL
    final String password = ""; // Votre mot de passe MySQL
    private static MyDB instance;

    private Connection cnx;

    private MyDB() {
        try {
            cnx = DriverManager.getConnection(URL, USER, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static MyDB getInstance() {
        if (instance == null) {
            instance = new MyDB();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
