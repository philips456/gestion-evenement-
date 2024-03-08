module com.example.gestionevenement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.pdfbox;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    exports entities;
    opens entities;

    opens com.example.gestionevenement to javafx.fxml;
    exports com.example.gestionevenement;
    exports com.example.gestionevenement.Controllers;
    opens com.example.gestionevenement.Controllers;
}