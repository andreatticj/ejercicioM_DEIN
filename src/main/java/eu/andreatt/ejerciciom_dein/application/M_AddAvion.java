package eu.andreatt.ejerciciom_dein.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class M_AddAvion extends Stage {
    public M_AddAvion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciom_dein/fxml/M_AddAvion.fxml"));
            GridPane root = loader.load();
            Scene scene = new Scene(root,300,300);
            setTitle("AVIONES - AÑADIR AVION");
            setResizable(false);
            setScene(scene);
            scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/ejerciciom_dein/css/M.css").toExternalForm());

            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/ejerciciom_dein/images/avion.png"));
            getIcons().add(icon);

            // initModality(Modality.APPLICATION_MODAL); // Comenta esta línea temporalmente si hay problemas de modalidad
            showAndWait();
        } catch (Exception e) {
            System.err.println("Error al abrir la ventana de Añadir Avion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}