package eu.andreatt.ejerciciom_dein.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * La clase M_ListadoAeropuertos representa una ventana que muestra un listado
 * de aeropuertos en la aplicación. Hereda de la clase Stage de JavaFX.
 */
public class M_ListadoAeropuertos extends Stage {

    /**
     * Constructor de la clase M_ListadoAeropuertos.
     * Carga el archivo FXML correspondiente para definir la interfaz de usuario
     * y configura la ventana para mostrar el listado de aeropuertos.
     */
    public M_ListadoAeropuertos() {
        try {
            // Carga el archivo FXML para la interfaz de la ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciom_dein/fxml/M_ListadoAeropuertos.fxml"));
            GridPane root = loader.load();

            // Crea una escena con el contenido cargado y establece su tamaño
            Scene scene = new Scene(root, 800, 550);
            scene.getStylesheets().add(getClass().getResource("/eu/andreatt/ejerciciom_dein/css/M.css").toExternalForm());
            setTitle("AVIONES - AEROPUERTOS"); // Título de la ventana
            setResizable(false); // La ventana no es redimensionable
            setScene(scene); // Asigna la escena a la ventana

            // Configurar el icono de la ventana
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/ejerciciom_dein/images/avion.png"));
            getIcons().add(icon);

            showAndWait(); // Muestra la ventana y espera a que se cierre
        } catch (Exception e) {
            // Manejo de excepciones: imprime un mensaje de error si hay problemas al abrir la ventana
            System.err.println("Error al abrir la ventana de Listado de Aeropuertos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
