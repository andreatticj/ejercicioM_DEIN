package eu.andreatt.ejerciciom_dein.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La clase M_Login es la clase principal de la aplicación, que gestiona
 * la ventana de inicio de sesión. Hereda de la clase Application de JavaFX.
 */
public class M_Login extends Application {

    /**
     * Metodo principal que inicia la aplicación JavaFX.
     *
     * @param stage El escenario principal que se mostrará.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Carga el archivo FXML que define la interfaz de usuario para la ventana de login
        FXMLLoader fxmlLoader = new FXMLLoader(M_Login.class.getResource("/eu/andreatt/ejerciciom_dein/fxml/M_Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); // Crea una escena a partir del contenido FXML
        scene.getStylesheets().add(getClass().getResource("/eu/andreatt/ejerciciom_dein/css/M.css").toExternalForm()); // Añade la hoja de estilos CSS
        stage.setTitle("AVIONES - LOGIN"); // Establece el título de la ventana
        stage.setScene(scene); // Asigna la escena al escenario
        stage.show(); // Muestra la ventana al usuario
    }

    /**
     * Metodo principal que se utiliza para lanzar la aplicación.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch(); // Inicia la aplicación JavaFX
    }
}
