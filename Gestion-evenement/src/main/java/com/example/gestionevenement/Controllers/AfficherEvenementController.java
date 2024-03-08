package com.example.gestionevenement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import entities.evenement;
import javafx.stage.Stage;
import services.Crudevenement;

import java.sql.Date;

public class AfficherEvenementController {

    @FXML
    private TextField titreField;

    @FXML
    private TextField capaciteField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField prixTicketField;

    @FXML
    private TextField lieuField;

    @FXML
    private TableView<evenement> eventTableView;

    @FXML
    private TableColumn<evenement, Integer> idColumn;

    @FXML
    private TableColumn<evenement, String> titreColumn;

    @FXML
    private TableColumn<evenement, Integer> capaciteColumn;

    @FXML
    private TableColumn<evenement, String> dateColumn;

    @FXML
    private TableColumn<evenement, String> descriptionColumn;

    @FXML
    private TableColumn<evenement, Float> prixTicketColumn;

    @FXML
    private TableColumn<evenement, String> lieuColumn;

    // You need to replace this with actual data service or repository
    private ObservableList<evenement> events = FXCollections.observableArrayList();

    // Method to initialize the controller
    @FXML
    public void initialize() {
        // Set cell value factories for table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        capaciteColumn.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateEvent"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixTicketColumn.setCellValueFactory(new PropertyValueFactory<>("prixTicket"));
        lieuColumn.setCellValueFactory(new PropertyValueFactory<>("lieuEvent"));

        // Load events into the TableView (you need to replace this with actual event data)
        loadEvents();

        eventTableView.setOnMouseClicked(event -> handleEventSelection());

    }

    private void handleEventSelection() {
        // Get the selected event
        evenement selectedEvent = eventTableView.getSelectionModel().getSelectedItem();

        // Populate the text fields with selected event's details
        titreField.setText(selectedEvent.getTitre());
        capaciteField.setText(String.valueOf(selectedEvent.getCapacite()));
        dateField.setText(selectedEvent.getDateEvent().toString());
        descriptionField.setText(selectedEvent.getDescription());
        prixTicketField.setText(String.valueOf(selectedEvent.getPrixTicket()));
        lieuField.setText(selectedEvent.getLieuEvent());
    }

    // Method to handle updating an event
    @FXML
    public void handleUpdateEvent() {
        // Check if any event is selected
        if (eventTableView.getSelectionModel().getSelectedItem() != null) {
            // Fetch input values from fields
            int id = eventTableView.getSelectionModel().getSelectedItem().getIdEvent();
            String titre = titreField.getText();
            int capacite = Integer.parseInt(capaciteField.getText());
            String date = dateField.getText();
            String description = descriptionField.getText();
            float prixTicket = Float.parseFloat(prixTicketField.getText());
            String lieu = lieuField.getText();

            // Update the selected event in the TableView (you need to replace this with actual update logic)
            evenement updatedEvent = new evenement(id, titre, capacite, Date.valueOf(date), description, prixTicket, lieu);
            Crudevenement service = new Crudevenement();
            service.modifierEvenement(updatedEvent);
            // Clear input fields after updating
            loadEvents();
            clearFields();
        } else {
            // Display a message or handle the case where no event is selected
            // For example:
            System.out.println("No event selected for update.");
        }
    }


    // Method to handle deleting an event
    @FXML
    public void handleDeleteEvent() {
        // Remove the selected event from the TableView (you need to replace this with actual delete logic)
        int selectedIndex = eventTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Crudevenement service = new Crudevenement();
            service.supprimerEvenement(selectedIndex);
        }
        loadEvents();
    }

    // Method to load events into the TableView
    private void loadEvents() {
        Crudevenement service = new Crudevenement();
        eventTableView.setItems(FXCollections.observableArrayList(service.afficherevenement()));
    }

    // Method to clear input fields after updating or deleting an event
    private void clearFields() {
        titreField.clear();
        capaciteField.clear();
        dateField.clear();
        descriptionField.clear();
        prixTicketField.clear();
        lieuField.clear();
    }
    @FXML
    public void OnAjouter(ActionEvent actionEvent) {
        // Fetch input values from fields
        String titre = titreField.getText();
        int capacite = Integer.parseInt(capaciteField.getText());
        String date = dateField.getText();
        String description = descriptionField.getText();
        float prixTicket = Float.parseFloat(prixTicketField.getText());
        String lieu = lieuField.getText();

        // Create a new event object
        evenement newEvent = new evenement(0, titre, capacite, Date.valueOf(date), description, prixTicket, lieu);

        // Call the service to add the event
        Crudevenement service = new Crudevenement();
        service.creerevenement(newEvent);

        // Reload events into the TableView
        loadEvents();

        // Clear input fields after adding
        clearFields();
    }

    @FXML
    public void OnComment(ActionEvent actionEvent) {
        try {
            // Load the FXML file for the comment page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherCommentaire.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the stage from the event source
            Stage stage = new Stage();
            // Set the scene on the stage and show the stage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OnExport(ActionEvent actionEvent) {
        try {
            // Create a new Excel workbook
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Events");

            // Create a header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Titre");
            headerRow.createCell(2).setCellValue("Capacité");
            headerRow.createCell(3).setCellValue("Date");
            headerRow.createCell(4).setCellValue("Description");
            headerRow.createCell(5).setCellValue("Prix Ticket");
            headerRow.createCell(6).setCellValue("Lieu");

            // Populate data rows
            int rowIdx = 1;
            for (evenement event : eventTableView.getItems()) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(event.getIdEvent());
                row.createCell(1).setCellValue(event.getTitre());
                row.createCell(2).setCellValue(event.getCapacite());
                row.createCell(3).setCellValue(event.getDateEvent().toString());
                row.createCell(4).setCellValue(event.getDescription());
                row.createCell(5).setCellValue(event.getPrixTicket());
                row.createCell(6).setCellValue(event.getLieuEvent());
            }

            // Resize columns to fit content
            for (int i = 0; i < 7; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the workbook to an Excel file
            String excelFilePath = "events.xlsx";
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();

            // Show a success message
            showAlert(Alert.AlertType.INFORMATION, "Export Successful", "TableView data exported to Excel successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message if an exception occurs during Excel export
            showAlert(Alert.AlertType.ERROR, "Export Error", "An error occurred while exporting TableView data to Excel.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
