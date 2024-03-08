package entities;

import java.sql.Date;

public class commentaire {
    private int idCommentaire, idEvent, userid;
    private float note;
    private String contenu;
    private Date dateCommentaire;

    public commentaire(int idCommentaire, int idEvent, int userid, String contenu, float note, Date dateCommentaire) {
        this.idCommentaire = idCommentaire;
        this.idEvent = idEvent;
        this.userid = userid; // Initialisation du champ userid dans le constructeur
        this.contenu = contenu;
        this.note = note;
        this.dateCommentaire = dateCommentaire;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    @Override
    public String toString() {
        return "commentaire{" +
                "idCommentaire=" + idCommentaire +
                ", idEvent=" + idEvent +
                ", userid=" + userid +
                ", note=" + note +
                ", contenu='" + contenu + '\'' +
                ", dateCommentaire=" + dateCommentaire +
                '}';
    }
}
