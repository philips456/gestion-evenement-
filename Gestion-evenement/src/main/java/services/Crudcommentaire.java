package services;

import entities.commentaire;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Crudcommentaire {
    private Connection cnx = MyDB.getInstance().getCnx();

    public Crudcommentaire() {}

    public void ajouterCommentaire(commentaire commentaire) {
        String req = "INSERT INTO commentaire (idEvent, userid, contenu, note, dateCommentaire) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = this.cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, commentaire.getIdEvent());
            stm.setInt(2, 1);
            stm.setString(3, commentaire.getContenu());
            stm.setFloat(4, commentaire.getNote());
            stm.setDate(5, new java.sql.Date(commentaire.getDateCommentaire().getTime()));


            stm.executeUpdate();

            // Récupérer l'ID auto-incrémenté généré pour le commentaire
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                commentaire.setIdCommentaire(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du commentaire : " + e.getMessage());
        }
    }

    public void supprimerCommentaire(int idCommentaire) {
        try {
            String req = "DELETE FROM commentaire WHERE idCommentaire = ?";
            PreparedStatement stm = this.cnx.prepareStatement(req);
            stm.setInt(1, idCommentaire);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du commentaire : " + e.getMessage());
        }
    }

    public List<commentaire> affichercommentaire() {
        List<commentaire> listCommentaires = new ArrayList<>();
        try {
            String req = "SELECT * FROM commentaire";
            Statement stm = this.cnx.createStatement();
            ResultSet result = stm.executeQuery(req);

            while(result.next()) {
                commentaire commentaire = new commentaire(
                        result.getInt("idCommentaire"),
                        result.getInt("idEvent"),
                        result.getInt("userid"), // Ajout du champ userid
                        result.getString("contenu"),
                        result.getFloat("note"),
                        result.getDate("dateCommentaire")


                );
                //System.out.println(commentaire.toString());
                listCommentaires.add(commentaire);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des commentaires : " + e.getMessage());
        }
        return listCommentaires;
    }

    public void modifierCommentaire(commentaire commentaire) {
        try {
            String req = "UPDATE commentaire SET idEvent=?, userid=?, contenu=?, note=?, dateCommentaire=? WHERE idCommentaire=?";
            PreparedStatement stm = this.cnx.prepareStatement(req);
            stm.setInt(1, commentaire.getIdEvent());
            stm.setInt(2, commentaire.getUserid()); // Ajout du champ userid
            stm.setString(3, commentaire.getContenu());
            stm.setFloat(4, commentaire.getNote());
            stm.setDate(5, new java.sql.Date(commentaire.getDateCommentaire().getTime()));
            stm.setInt(6, commentaire.getIdCommentaire());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du commentaire : " + e.getMessage());
        }
    }
}
