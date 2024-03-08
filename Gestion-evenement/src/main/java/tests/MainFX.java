package tests;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier FXML
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/gestionevenement/AfficherCommentaire.fxml"));

        // Créer la scène
        Scene scene = new Scene(root, 800, 600);

        // Configurer la scène et afficher la fenêtre
        primaryStage.setTitle("Gestion des Evenements");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
