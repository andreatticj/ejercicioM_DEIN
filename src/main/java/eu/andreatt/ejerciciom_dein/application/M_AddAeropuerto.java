package eu.andreatt.ejerciciom_dein.application;


import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;

public class M_AddAeropuerto extends Stage {

    public M_AddAeropuerto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciom_dein/fxml/M_AddAeropuerto.fxml"));
            GridPane root = loader.load();
            Scene scene = new Scene(root,300,550);
            setTitle("AVIONES - AÑADIR AEROPUERTO");
            setResizable(false);
            setScene(scene);
            scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/ejerciciom_dein/css/M.css").toExternalForm());

            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/ejerciciom_dein/images/avion.png"));
            getIcons().add(icon);

            // initModality(Modality.APPLICATION_MODAL); // Comenta esta línea temporalmente si hay problemas de modalidad
            showAndWait();
        } catch (Exception e) {
            System.err.println("Error al abrir la ventana de Añadir Aeropuerto: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
