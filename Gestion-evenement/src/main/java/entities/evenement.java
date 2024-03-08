package entities;

import java.util.Date;

public class evenement {

    private int idEvent, capacite, userID;
    private String titre, lieuEvent;
    private Date dateEvent;
    private String statusEvent;
    private String description;
    private float prixTicket;

    public evenement(String titre, int capacite, Date parsedDate, String description, double prixTicket, String lieu) {
    }



    public evenement(int idEvent, String titre, int capacite, Date dateEvenement, String description, float prixTicket, String lieuEvent) {
        this.idEvent = idEvent;
        this.titre = titre;
        this.capacite = capacite;
        this.dateEvent = dateEvenement;
        this.description = description;
        this.prixTicket = prixTicket;
        this.lieuEvent = lieuEvent;
    }
    public evenement(String titre, int capacite, Date dateEvent, String description, float prixTicket, String lieuEvent) {
        // Initialisez les membres de la classe avec les valeurs passées en paramètres
        this.titre = titre;
        this.capacite = capacite;
        this.dateEvent = dateEvent;
        this.description = description;
        this.prixTicket = prixTicket;
        this.lieuEvent = lieuEvent;
    }

    public evenement(String titre, int capacite, String date, String description, double prixTicket, String lieu) {

    }


    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLieuEvent() {
        return lieuEvent;
    }

    public void setLieuEvent(String lieuEvent) {
        this.lieuEvent = lieuEvent;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getStatusEvent() {
        return statusEvent;
    }

    public void setStatusEvent(String statusEvent) {
        this.statusEvent = statusEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrixTicket() {
        return prixTicket;
    }

    public void setPrixTicket(float prixTicket) {
        this.prixTicket = prixTicket;
    }

    @Override
    public String toString() {
        return "evenement{" +
                "idEvent=" + idEvent +
                ", capacite=" + capacite +
                ", userID=" + userID +
                ", titre='" + titre + '\'' +
                ", lieuEvent='" + lieuEvent + '\'' +
                ", dateEvent=" + dateEvent +
                ", statusEvent='" + statusEvent + '\'' +
                ", description='" + description + '\'' +
                ", prixTicket=" + prixTicket +
                '}';
    }
}
