package eu.andreatt.ejerciciom_dein.application;

import eu.andreatt.ejerciciom_dein.controllers.M_EditAeropuertoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Blob;

/**
 * La clase M_EditarAeropuerto representa una ventana para editar la información
 * de un aeropuerto en la aplicación. Hereda de la clase Stage de JavaFX.
 */
public class M_EditarAeropuerto extends Stage {

    private M_EditAeropuertoController controller;

    /**
     * Constructor de la clase M_EditarAeropuerto.
     * Carga el archivo FXML correspondiente para definir la interfaz de usuario,
     * establece los datos del aeropuerto a editar y configura la ventana.
     *
     * @param nombre           El nombre del aeropuerto.
     * @param pais             El país del aeropuerto.
     * @param ciudad           La ciudad donde se encuentra el aeropuerto.
     * @param calle            La calle del aeropuerto.
     * @param numero           El número de la calle.
     * @param anioInauguracion El año de inauguración del aeropuerto.
     * @param capacidad        La capacidad del aeropuerto.
     * @param radioPublico     Indica si el aeropuerto es público.
     * @param radioPrivado     Indica si el aeropuerto es privado.
     * @param numSocios        El número de socios del aeropuerto.
     * @param financiacion     La financiación del aeropuerto.
     * @param numTrabajadores  El número de trabajadores del aeropuerto.
     * @param idDireccion      El ID de la dirección del aeropuerto.
     * @param idAeropuerto     El ID del aeropuerto.
     * @param imagen
     */
    public M_EditarAeropuerto(String nombre, String pais, String ciudad, String calle, int numero,
                              int anioInauguracion, int capacidad, boolean radioPublico,
                              boolean radioPrivado, int numSocios, double financiacion,
                              int numTrabajadores, int idDireccion, int idAeropuerto, Blob imagen) {
        try {
            // Carga el archivo FXML para la interfaz de la ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciom_dein/fxml/M_EditarAeropuerto.fxml"));
            GridPane root = loader.load();

            // Obtener el controlador de la ventana
            controller = loader.getController();

            // Configurar los datos del aeropuerto en los campos de texto del controlador
            controller.setTextFieldNombre(nombre);
            controller.setTextFieldPais(pais);
            controller.setTextFieldCiudad(ciudad);
            controller.setTextFieldCalle(calle);
            controller.setTextFieldNumero(String.valueOf(numero));
            controller.setTextFieldAnioInauguracion(String.valueOf(anioInauguracion));
            controller.setTextFieldCapacidad(String.valueOf(capacidad));
            controller.setPublicSelected(radioPublico);
            controller.setPrivadoSelected(radioPrivado);
            controller.setTextFieldNumSocios(String.valueOf(numSocios));
            controller.setTextFieldFinanciacion(String.valueOf(financiacion));
            controller.setTextFieldNumTrabajadores(String.valueOf(numTrabajadores));
            controller.setIdDireccion(idDireccion);
            controller.setIdAeropuerto(idAeropuerto);

            // Configuración de visibilidad según el tipo de aeropuerto
            if (controller.isPrivadoSelected()) {
                controller.habilitarRadioPrivados();
            } else {
                controller.habilitarRadioPublicos();
            }

            // Crea una escena con el contenido cargado y establece su tamaño
            Scene scene = new Scene(root, 300, 550);
            initModality(Modality.APPLICATION_MODAL); // La ventana se muestra como modal
            setTitle("AVIONES - EDITAR AEROPUERTO");
            setResizable(false); // La ventana no es redimensionable
            setScene(scene); // Asigna la escena a la ventana

            // Agrega la hoja de estilos CSS para la apariencia de la ventana
            scene.getStylesheets().add(getClass().getResource("/eu/andreatt/ejerciciom_dein/css/M.css").toExternalForm());

            // Configurar el icono de la ventana
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/ejerciciom_dein/images/avion.png"));
            getIcons().add(icon);

            showAndWait(); // Muestra la ventana y espera a que se cierre
        } catch (Exception e) {
            // Manejo de excepciones: imprime un mensaje de error si hay problemas al abrir la ventana
            System.err.println("Error al abrir la ventana de Editar Aeropuerto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el controlador asociado a esta ventana.
     *
     * @return El controlador de la ventana de edición de aeropuerto.
     */
    public M_EditAeropuertoController getController() {
        return controller;
    }
}
