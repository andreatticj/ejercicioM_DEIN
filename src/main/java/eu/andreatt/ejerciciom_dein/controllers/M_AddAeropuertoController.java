package eu.andreatt.ejerciciom_dein.controllers;

import eu.andreatt.ejerciciom_dein.dao.AeropuertosDao;
import eu.andreatt.ejerciciom_dein.dao.AeropuertosPrivadosDao;
import eu.andreatt.ejerciciom_dein.dao.AeropuertosPublicosDao;
import eu.andreatt.ejerciciom_dein.dao.DireccionesDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javafx.scene.image.Image;

/**
 * Controlador combinado para gestionar la creación de un nuevo aeropuerto.
 */
public class M_AddAeropuertoController {

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnImagen;
    @FXML
    private Label lblFinanciacion;
    @FXML
    private Label lblNumTrabajadores;
    @FXML
    private Label lblSocios;
    @FXML
    private ImageView imageView;
    @FXML
    private ToggleGroup rbGroup;
    @FXML
    private RadioButton rbPrivado;
    @FXML
    private RadioButton rbPublico;
    @FXML
    private TextField txtAnioInaguracion;
    @FXML
    private TextField txtCalle;
    @FXML
    private TextField txtCapaciad;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtFinanciacion;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtNumTrabajadores;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtPais;
    @FXML
    private TextField txtSocios;

    private AeropuertosDao aeropuertosDao;
    private DireccionesDao direccionesDao;
    private AeropuertosPrivadosDao aeropuertosPrivadosDao;
    private AeropuertosPublicosDao aeropuertosPublicosDao;

    private String ruta;

    /**
     * Muestra u oculta los campos dependiendo del tipo de aeropuerto (privado o público).
     * @param event El evento de acción.
     */
    @FXML
    void actPrivado(ActionEvent event) {
        lblSocios.setVisible(true);
        txtSocios.setVisible(true);
        lblFinanciacion.setVisible(false);
        txtFinanciacion.setVisible(false);
        lblNumTrabajadores.setVisible(false);
        txtNumTrabajadores.setVisible(false);
    }

    @FXML
    void actPublico(ActionEvent event) {
        lblSocios.setVisible(false);
        txtSocios.setVisible(false);
        lblFinanciacion.setVisible(true);
        txtFinanciacion.setVisible(true);
        lblNumTrabajadores.setVisible(true);
        txtNumTrabajadores.setVisible(true);
    }

    /**
     * Cierra la ventana actual sin guardar cambios.
     * @param event El evento de acción.
     */
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Guarda los datos del nuevo aeropuerto después de validarlos.
     * @param event El evento de acción.
     */
    @FXML
    void guardar(ActionEvent event) {
        String errores = validarCampos();
        if (!errores.isEmpty()) {
            Alert alerta = generarVentana(AlertType.ERROR, errores, "ERROR");
            alerta.show();
            return;
        }

        // Inicializar DAOs
        aeropuertosDao = new AeropuertosDao();
        direccionesDao = new DireccionesDao();
        aeropuertosPrivadosDao = new AeropuertosPrivadosDao();
        aeropuertosPublicosDao = new AeropuertosPublicosDao();

        // Verificar si el aeropuerto ya existe
        boolean existe = aeropuertosDao.existeNombreAeropuerto(txtNombre.getText());
        if (existe) {
            Alert alerta = generarVentana(AlertType.ERROR, "El aeropuerto ya existía", "ERROR");
            alerta.show();
            return;
        }

        // Verificar si la dirección ya existe
        int direccionId = direccionesDao.existeDireccion(txtPais.getText(), txtCiudad.getText(), txtCalle.getText(), Integer.parseInt(txtNumero.getText()));
        if (direccionId == -1) {
            if (!direccionesDao.insertarDireccion(txtPais.getText(), txtCiudad.getText(), txtCalle.getText(), Integer.parseInt(txtNumero.getText()))) {
                Alert alerta = generarVentana(AlertType.ERROR, "Error al insertar la dirección", "ERROR");
                alerta.show();
                return;
            }
            direccionId = direccionesDao.dameMaxIdDirecciones(); // Asignar el nuevo ID
        }

        // Insertar el aeropuerto
        aeropuertosDao.insertarAeropuerto(txtNombre.getText(), Integer.parseInt(txtAnioInaguracion.getText()), Integer.parseInt(txtCapaciad.getText()), direccionId);
        aeropuertosDao.insertarImagen(ruta, direccionId);

        // Insertar según el tipo de aeropuerto
        if (rbPublico.isSelected()) {
            aeropuertosPublicosDao.insertarAeropuertoPublico(aeropuertosDao.dameMaxIdAeropuertos(), Float.parseFloat(txtFinanciacion.getText()), Integer.parseInt(txtNumTrabajadores.getText()));
        } else {
            aeropuertosPrivadosDao.insertarAeropuertoPrivado(aeropuertosDao.dameMaxIdAeropuertos(), Integer.parseInt(txtSocios.getText()));
        }

        // Mensaje de alerta
        Alert alerta = generarVentana(AlertType.INFORMATION, "Se ha añadido un aeropuerto", "INFO");
        alerta.show();

        // Cerrar ventana
        Stage stage = (Stage) btnGuardar.getScene().getWindow();
        stage.close();
    }

    /**
     * Permite seleccionar una imagen para el aeropuerto.
     * @param event El evento de acción.
     */
    @FXML
    void actionImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);

        Stage stage = (Stage) btnImagen.getScene().getWindow();
        File archivoSeleccionado = fileChooser.showOpenDialog(stage);

        if (archivoSeleccionado != null) {
            try {
                if (Files.size(archivoSeleccionado.toPath()) > 65000) {
                    Alert alerta = generarVentana(AlertType.ERROR, "Elige una imagen más pequeña", "ERROR");
                    alerta.show();
                } else {
                    Image image = new Image(archivoSeleccionado.toURI().toString());
                    ruta = archivoSeleccionado.getAbsolutePath();
                    imageView.setImage(image); // Mostrar la imagen seleccionada
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Valida los campos del formulario.
     * @return Una cadena con los errores de validación, o una cadena vacía si todo está correcto.
     */
    private String validarCampos() {
        StringBuilder errores = new StringBuilder();

        if (txtNombre.getText().isEmpty()) {
            errores.append("El campo NOMBRE debe contener texto\n");
        }
        if (txtPais.getText().isEmpty()) {
            errores.append("El campo PAÍS debe contener texto\n");
        }
        if (txtCiudad.getText().isEmpty()) {
            errores.append("El campo CIUDAD debe contener texto\n");
        }
        if (txtCalle.getText().isEmpty()) {
            errores.append("El campo CALLE debe contener texto\n");
        }
        if (txtNumero.getText().isEmpty()) {
            errores.append("El campo NÚMERO debe contener texto\n");
        } else if (!esNumeroEntero(txtNumero.getText())) {
            errores.append("El campo NÚMERO debe ser un número\n");
        }
        if (txtAnioInaguracion.getText().isEmpty()) {
            errores.append("El campo AÑO DE INAUGURACIÓN debe contener texto\n");
        } else if (!esNumeroEntero(txtAnioInaguracion.getText())) {
            errores.append("El campo AÑO DE INAUGURACIÓN debe ser un número\n");
        }
        if (txtCapaciad.getText().isEmpty()) {
            errores.append("El campo CAPACIDAD debe contener texto\n");
        } else if (!esNumeroEntero(txtCapaciad.getText())) {
            errores.append("El campo CAPACIDAD debe ser un número\n");
        }
        if (rbPublico.isSelected()) {
            if (txtFinanciacion.getText().isEmpty()) {
                errores.append("El campo FINANCIACIÓN debe contener texto\n");
            } else if (!esNumeroDecimal(txtFinanciacion.getText())) {
                errores.append("El campo FINANCIACIÓN debe ser un número decimal\n");
            }
            if (txtNumTrabajadores.getText().isEmpty()) {
                errores.append("El campo NÚMERO DE TRABAJADORES debe contener texto\n");
            } else if (!esNumeroEntero(txtNumTrabajadores.getText())) {
                errores.append("El campo NÚMERO DE TRABAJADORES debe ser un número\n");
            }
        } else {
            if (txtSocios.getText().isEmpty()) {
                errores.append("El campo SOCIOS debe contener texto\n");
            } else if (!esNumeroEntero(txtSocios.getText())) {
                errores.append("El campo SOCIOS debe ser un número\n");
            }
        }

        return errores.toString();
    }

    /**
     * Verifica si una cadena es un número entero.
     * @param texto La cadena a verificar.
     * @return True si es un número entero, false si no.
     */
    private boolean esNumeroEntero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica si una cadena es un número decimal.
     * @param texto La cadena a verificar.
     * @return True si es un número decimal, false si no.
     */
    private boolean esNumeroDecimal(String texto) {
        try {
            Float.parseFloat(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Crea una alerta de tipo especificado con el título y el contenido proporcionados.
     * @param tipo El tipo de alerta.
     * @param contenido El contenido de la alerta.
     * @param titulo El título de la alerta.
     * @return El objeto Alert.
     */
    private Alert generarVentana(AlertType tipo, String contenido, String titulo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        return alerta;
    }

    /**
     * Configura el file chooser para seleccionar imágenes.
     * @param fileChooser El objeto FileChooser a configurar.
     */
    private void configureFileChooser(FileChooser fileChooser) {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
    }
}
