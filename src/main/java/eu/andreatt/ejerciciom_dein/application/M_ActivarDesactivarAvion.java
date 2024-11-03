package eu.andreatt.ejerciciom_dein.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * La clase M_ActivarDesactivarAvion representa una ventana que permite
 * activar o desactivar un avión en la aplicación.
 * Hereda de la clase Stage de JavaFX.
 */
public class M_ActivarDesactivarAvion extends Stage {

    /**
     * Constructor de la clase M_ActivarDesactivarAvion.
     * Carga el archivo FXML correspondiente para definir la interfaz de usuario,
     * establece el título de la ventana, configura su tamaño y estilo,
     * y muestra la ventana de forma modal.
     */
    public M_ActivarDesactivarAvion() {
        try {
            // Carga el archivo FXML para la interfaz de la ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciom_dein/fxml/M_ActivarDesactivarAvion.fxml"));
            GridPane root = loader.load();

            // Crea una escena con el contenido cargado y establece su tamaño
            Scene scene = new Scene(root, 300, 300);
            setTitle("AVIONES - ACTIVAR / DESACTIVAR AVION");
            setResizable(false); // La ventana no es redimensionable
            setScene(scene); // Asigna la escena a la ventana

            // Agrega la hoja de estilos CSS para la apariencia de la ventana
            scene.getStylesheets().add(getClass().getResource("/eu/andreatt/ejerciciom_dein/css/M.css").toExternalForm());

            // Establece el icono de la ventana
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/ejerciciom_dein/images/avion.png"));
            getIcons().add(icon);

            // initModality(Modality.APPLICATION_MODAL); // Comenta esta línea temporalmente si hay problemas de modalidad
            showAndWait(); // Muestra la ventana y espera a que se cierre
        } catch (Exception e) {
            // Manejo de excepciones: imprime un mensaje de error si hay problemas al abrir la ventana
            System.err.println("Error al abrir la ventana de Activar / Desactivar Avion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
