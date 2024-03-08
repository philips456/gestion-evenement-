package com.example.gestionevenement.Controllers;

import entities.evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import entities.commentaire;
import services.Crudcommentaire;
import services.Crudevenement;

public class CommentaireController {

    public DatePicker datePicker;
    @FXML
    private ComboBox<String> eventIdComboBox;

    @FXML
    private TextField userIDField;

    @FXML
    private TextField contenuField;

    @FXML
    private TextField noteField;

    @FXML
    private TableView<commentaire> commentaireTableView;

    @FXML
    private TableColumn<commentaire, Integer> idEventColumn;

    @FXML
    private TableColumn<commentaire, Integer> userIDColumn;

    @FXML
    private TableColumn<commentaire, String> contenuColumn;

    @FXML
    private TableColumn<commentaire, Float> noteColumn;

    @FXML
    private TableColumn<commentaire, Date> dateCommentaireColumn;

    // You need to replace this with actual data service or repository
    private ObservableList<commentaire> commentaires = FXCollections.observableArrayList();

    // Method to initialize the controller
    @FXML
    public void initialize() {
        // Set cell value factories for table columns
        idEventColumn.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contenuColumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        dateCommentaireColumn.setCellValueFactory(new PropertyValueFactory<>("dateCommentaire"));

        // Load events into the ComboBox (you need to replace this with actual event data)
        ObservableList<String> eventTitles = FXCollections.observableArrayList("Event 1", "Event 2", "Event 3");
        eventIdComboBox.setItems(eventTitles);
        loadEvents(); // Call the method to load events

        // Load commentaries into the TableView (you need to replace this with actual commentaries data)
        loadCommentaries();
        commentaireTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() > 0) {
                handleCommentarySelection();
            }
        });
    }

    private void handleCommentarySelection() {
        commentaire selectedCommentaire = commentaireTableView.getSelectionModel().getSelectedItem();
        if (selectedCommentaire != null) {
            eventIdComboBox.getSelectionModel().select(selectedCommentaire.getIdEvent() - 1); // Adjust index accordingly
            userIDField.setText(String.valueOf(selectedCommentaire.getUserid()));
            contenuField.setText(selectedCommentaire.getContenu());
            noteField.setText(String.valueOf(selectedCommentaire.getNote()));
            datePicker.setValue(selectedCommentaire.getDateCommentaire().toLocalDate());
        }
    }

    private void loadEvents() {
        Crudevenement eventService = new Crudevenement();
        List<evenement> events = eventService.afficherevenement(); // Assuming getAllEvents() returns a list of events
        ObservableList<String> eventTitles = FXCollections.observableArrayList();
        for (evenement event : events) {
            eventTitles.add(event.getTitre());
        }
        eventIdComboBox.setItems(eventTitles);
    }

    // Method to handle adding a new commentary
    @FXML
    public void handleAddCommentaire() {
        // Fetch input values from fields
        int eventId = eventIdComboBox.getSelectionModel().getSelectedIndex() + 1; // Adjust index accordingly
        int userId = Integer.parseInt(userIDField.getText());
        String contenu = contenuField.getText();
        float note = Float.parseFloat(noteField.getText());
        Date dateCommentaire = java.sql.Date.valueOf(datePicker.getValue()); // Get selected date from DatePicker

        // Create a new commentary object
        commentaire newCommentaire = new commentaire(0, eventId, userId, contenu, note, (java.sql.Date) dateCommentaire);

        // Add the new commentary to the list and update the TableView
        commentaires.add(newCommentaire);
        commentaireTableView.setItems(commentaires);

        // Clear input fields after adding
        clearFields();
    }

    // Method to load commentaries into the TableView
    private void loadCommentaries() {
        Crudcommentaire service = new Crudcommentaire();
        commentaireTableView.setItems(FXCollections.observableArrayList(service.affichercommentaire()));
    }

    // Method to clear input fields after adding a commentary
    private void clearFields() {
        userIDField.clear();
        contenuField.clear();
        noteField.clear();
        datePicker.setValue(null); // Clear DatePicker value
    }

    // Method to handle updating a commentary
    @FXML
    public void handleUpdateCommentaire(ActionEvent actionEvent) {
        // Fetch selected commentary from the TableView
        commentaire selectedCommentaire = commentaireTableView.getSelectionModel().getSelectedItem();

        if (selectedCommentaire != null) {
            // Fetch input values from fields
            int commentaireId = selectedCommentaire.getIdCommentaire();
            int eventId = eventIdComboBox.getSelectionModel().getSelectedIndex() + 1; // Adjust index accordingly
            int userId = Integer.parseInt(userIDField.getText());
            String contenu = contenuField.getText();
            float note = Float.parseFloat(noteField.getText());
            Date dateCommentaire = java.sql.Date.valueOf(datePicker.getValue()); // Get selected date from DatePicker

            // Update the selected commentary with new values
            selectedCommentaire.setIdCommentaire(commentaireId);
            selectedCommentaire.setIdEvent(eventId);
            selectedCommentaire.setUserid(userId);
            selectedCommentaire.setContenu(contenu);
            selectedCommentaire.setNote(note);
            selectedCommentaire.setDateCommentaire((java.sql.Date) dateCommentaire);
             Crudcommentaire service = new Crudcommentaire();
            // Update the TableView
            service.modifierCommentaire(selectedCommentaire);
            commentaireTableView.refresh();

            // Clear input fields after updating
            clearFields();
        } else {
            // Display an error message if no commentary is selected
            showAlert(Alert.AlertType.ERROR, "No Commentary Selected", "Please select a commentary to update.");
        }
    }

    // Method to handle deleting a commentary
    @FXML
    public void handleDeleteCommentaire(ActionEvent actionEvent) {
        // Fetch selected commentary from the TableView
        commentaire selectedCommentaire = commentaireTableView.getSelectionModel().getSelectedItem();

        if (selectedCommentaire != null) {
            // Remove the selected commentary from the list
            Crudcommentaire service = new Crudcommentaire();
            // Update the TableView
            service.supprimerCommentaire(selectedCommentaire.getIdCommentaire());
            commentaireTableView.setItems(commentaires);
            loadCommentaries();
            // Clear input fields after deleting
            clearFields();

        } else {
            // Display an error message if no commentary is selected
            showAlert(Alert.AlertType.ERROR, "No Commentary Selected", "Please select a commentary to delete.");
        }
    }

    // Method to display an alert
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void OnExport(ActionEvent actionEvent) {
        try {
            // Create a new PDF document
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            // Create a new content stream for the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Set font and font size
            contentStream.setFont(PDType1Font.HELVETICA, 12);

            // Write TableView data to PDF
            int row = 0;
            for (commentaire commentaire : commentaireTableView.getItems()) {
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700 - row * 20); // Adjust position accordingly
                contentStream.showText("Event ID: " + commentaire.getIdEvent());
                contentStream.newLine();
                contentStream.showText("User ID: " + commentaire.getUserid());
                contentStream.newLine();
                contentStream.showText("Contenu: " + commentaire.getContenu());
                contentStream.newLine();
                contentStream.showText("Note: " + commentaire.getNote());
                contentStream.newLine();
                contentStream.showText("Date Commentaire: " + commentaire.getDateCommentaire());
                contentStream.newLine();
                contentStream.endText();
                row++;
            }

            // Close the content stream
            contentStream.close();

            // Save the document
            File file = new File("commentaires.pdf");
            document.save(file);

            // Close the document
            document.close();

            // Show a success message
            showAlert(Alert.AlertType.INFORMATION, "Export Successful", "TableView data exported to PDF successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message if an exception occurs during PDF creation
            showAlert(Alert.AlertType.ERROR, "Export Error", "An error occurred while exporting TableView data to PDF.");
        }
    }

}
