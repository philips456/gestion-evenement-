package tests;

import entities.evenement;
import services.Crudevenement;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Crudevenement crudEvenements = new Crudevenement();

        // Création d'un nouvel événement
        evenement nouvelEvenement = new evenement("la fete", 12, new Date(), "ariana", 20.0f, "ariana");

        crudEvenements.creerevenement(nouvelEvenement);

        // Affichage de tous les événements
        List<evenement> listeevenement = crudEvenements.afficherevenement();
        System.out.println("Liste des événements :");
        for (evenement evenement : listeevenement) {
            System.out.println(evenement);
        }

        // Modification du premier événement de la liste
        if (!listeevenement.isEmpty()) {
            evenement premierevenement = listeevenement.get(0);
            premierevenement.setTitre("Nouveau titre");
            crudEvenements.modifierEvenement(premierevenement);
            System.out.println("Événement modifié : " + premierevenement);
        }

        // Suppression du premier événement de la liste
        if (!listeevenement.isEmpty()) {
            evenement premierEvenement = listeevenement.get(0);
            crudEvenements.supprimerEvenement(premierEvenement.getIdEvent());
            System.out.println("Événement supprimé : " + premierEvenement);
        }
    }
}


/*
import entities.commentaire;
import services.Crudcommentaire;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Crudcommentaire crudCommentaire = new Crudcommentaire();

        // Ajout d'un nouveau commentaire
        Date d = new Date();
        commentaire nouveauCommentaire = new commentaire(1, 55, 3, "fetejeune" ,20.0f ,new Date());
        crudCommentaire.ajouterCommentaire(nouveauCommentaire);

        // Affichage de tous les commentaires
        List<commentaire> listeCommentaires = crudCommentaire.affichercommentaire();
        System.out.println("Liste des commentaires :");

        for (commentaire commentaire : listeCommentaires) {
            System.out.println(commentaire.toString());
        }


        // Modification du premier commentaire de la liste
        if (!listeCommentaires.isEmpty()) {
            commentaire premierCommentaire = listeCommentaires.get(0);
            premierCommentaire.setContenu("Contenu modifié");
            crudCommentaire.modifierCommentaire(premierCommentaire);
            System.out.println("Commentaire modifié : " + premierCommentaire);
        }

        // Suppression du premier commentaire de la liste
        if (!listeCommentaires.isEmpty()) {
            commentaire premierCommentaire = listeCommentaires.get(0);
            crudCommentaire.supprimerCommentaire(premierCommentaire.getIdCommentaire());
            System.out.println("Commentaire supprimé : " + premierCommentaire);
        }

         }*/
        // }


