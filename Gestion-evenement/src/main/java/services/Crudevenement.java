package services;

import entities.evenement;
import utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Crudevenement {
    private Connection cnx = MyDB.getInstance().getCnx();

    public Crudevenement() {
    }

    public void creerevenement(evenement evenement) {
        String req = "INSERT INTO evenement (titre, capacite, dateEvent, description, prixTicket, lieuEvent,userID) VALUES (?, ?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement stm = this.cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, evenement.getTitre());
            stm.setInt(2, evenement.getCapacite());
            stm.setDate(3, new java.sql.Date(evenement.getDateEvent().getTime()));
            stm.setString(4, evenement.getDescription());
            stm.setFloat(5, evenement.getPrixTicket());
            stm.setString(6, evenement.getLieuEvent());
            stm.setInt(7, 1);
            stm.executeUpdate();

            // Récupérer l'ID auto-incrémenté généré pour l'événement
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                evenement.setIdEvent(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création de l'événement : " + e.getMessage());
        }
    }

    public void supprimerEvenement(int idEvent) {
        try {
            String req = "DELETE FROM evenement WHERE idEvent = ?";
            PreparedStatement stm = this.cnx.prepareStatement(req);
            stm.setInt(1, idEvent);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'événement : " + e.getMessage());
        }
    }

    public List<evenement> afficherevenement() {
        List<evenement> listEvenement = new ArrayList<>();
        try {
            String req = "SELECT * FROM evenement";
            Statement stm = this.cnx.createStatement();
            ResultSet result = stm.executeQuery(req);

            while(result.next()) {
                evenement evenement = new evenement(
                        result.getInt("idEvent"),
                        result.getString("titre"),
                        result.getInt("capacite"),
                        result.getDate("dateEvent"),
                        result.getString("description"),
                        result.getFloat("prixTicket"),
                        result.getString("lieuEvent")
                );
                listEvenement.add(evenement);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des événements : " + e.getMessage());
        }
        return listEvenement;
    }

    public void modifierEvenement(evenement evenement) {
        try {
            String req = "UPDATE evenement SET titre=?, capacite=?, dateEvent=?, description=?, prixTicket=?, lieuEvent=? WHERE idEvent=?";
            PreparedStatement stm = this.cnx.prepareStatement(req);
            stm.setString(1, evenement.getTitre());
            stm.setInt(2, evenement.getCapacite());
            stm.setDate(3, new java.sql.Date(evenement.getDateEvent().getTime()));
            stm.setString(4, evenement.getDescription());
            stm.setFloat(5, evenement.getPrixTicket());
            stm.setString(6, evenement.getLieuEvent());
            stm.setInt(7, evenement.getIdEvent());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de l'événement : " + e.getMessage());
        }
    }
}

