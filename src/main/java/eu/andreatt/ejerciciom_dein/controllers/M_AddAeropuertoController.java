package eu.andreatt.ejerciciom_dein.controllers;

import eu.andreatt.ejerciciom_dein.dao.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;

/**
 * Controlador para la ventana de añadir un aeropuerto.
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
    private TextField txtAnioInauguracion;

    @FXML
    private TextField txtCalle;

    @FXML
    private TextField txtCapacidad;

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
     * Maneja el evento de selección del botón de aeropuerto privado.
     * Muestra los campos de número de socios y oculta los de financiación y número de trabajadores.
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

    /**
     * Maneja el evento de selección del botón de aeropuerto público.
     * Muestra los campos de financiación y número de trabajadores, y oculta el campo de número de socios.
     * @param event El evento de acción.
     */
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
     * Maneja el evento de cancelación y cierra la ventana actual.
     * @param event El evento de acción.
     */
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Maneja el evento de guardar la información del nuevo aeropuerto.
     * Valida los campos, inserta la dirección y el aeropuerto, y muestra un mensaje de alerta.
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

        // Crear Aeropuerto
        aeropuertosDao.insertarAeropuerto(txtNombre.getText(), Integer.parseInt(txtAnioInauguracion.getText()), Integer.parseInt(txtCapacidad.getText()), direccionId);
        aeropuertosDao.insertarImagen(ruta, direccionId);

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
     * Maneja el evento de selección de imagen para el aeropuerto.
     * Abre un diálogo de selección de archivos para elegir una imagen y valida su tamaño.
     * @param event El evento de acción.
     */
    @FXML
    void actionImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        System.out.println("BUTTIN IMG");
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
                    // Asumir que hay un ImageView para mostrar la imagen
                     imageView.setImage(image); // Si hay un ImageView, descomentar
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Valida los datos introducidos en los campos de texto.
     * @return Un string que contiene los errores de validación. Si no hay errores, retorna una cadena vacía.
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
        if (txtAnioInauguracion.getText().isEmpty()) {
            errores.append("El campo AÑO DE INAUGURACIÓN debe contener texto\n");
        } else if (!esNumeroEntero(txtAnioInauguracion.getText())) {
            errores.append("El campo AÑO DE INAUGURACIÓN debe ser un número\n");
        }
        if (txtCapacidad.getText().isEmpty()) {
            errores.append("El campo CAPACIDAD debe contener texto\n");
        } else if (!esNumeroEntero(txtCapacidad.getText())) {
            errores.append("El campo CAPACIDAD debe ser un número\n");
        }
        if (rbPublico.isSelected()) {
            if (txtFinanciacion.getText().isEmpty()) {
                errores.append("El campo FINANCIACIÓN debe contener texto\n");
            } else if (!esNumeroDecimal(txtFinanciacion.getText())) {
                errores.append("El campo FINANCIACIÓN debe ser un número\n");
            }
            if (txtNumTrabajadores.getText().isEmpty()) {
                errores.append("El campo NÚMERO DE TRABAJADORES debe contener texto\n");
            } else if (!esNumeroEntero(txtNumTrabajadores.getText())) {
                errores.append("El campo NÚMERO DE TRABAJADORES debe ser un número\n");
            }
        }
        if (rbPrivado.isSelected()) {
            if (txtSocios.getText().isEmpty()) {
                errores.append("El campo NÚMERO DE SOCIOS debe contener texto\n");
            } else if (!esNumeroEntero(txtSocios.getText())) {
                errores.append("El campo NÚMERO DE SOCIOS debe ser un número\n");
            }
        }
        return errores.toString();
    }

    /**
     * Crea y devuelve una alerta con el mensaje y título proporcionados.
     * @param tipo El tipo de alerta.
     * @param mensaje El mensaje que se mostrará en la alerta.
     * @param titulo El título de la alerta.
     * @return Un objeto Alert configurado.
     */
    private Alert generarVentana(AlertType tipo, String mensaje, String titulo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        return alerta;
    }

    /**
     * Configura el FileChooser para seleccionar archivos de imagen.
     * @param fileChooser El FileChooser a configurar.
     */
    private void configureFileChooser(FileChooser fileChooser) {
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        fileChooser.setTitle("Seleccione una imagen");
    }

    /**
     * Verifica si la cadena proporcionada es un número entero.
     * @param cadena La cadena a verificar.
     * @return true si la cadena es un número entero; false en caso contrario.
     */
    private boolean esNumeroEntero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica si la cadena proporcionada es un número decimal.
     * @param cadena La cadena a verificar.
     * @return true si la cadena es un número decimal; false en caso contrario.
     */
    private boolean esNumeroDecimal(String cadena) {
        try {
            Float.parseFloat(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Inicializa el controlador. Este metodo se llama automáticamente
     * después de que se cargue el FXML.
     */
    @FXML
    void initialize() {
        btnGuardar.setDefaultButton(true);
        btnCancelar.setCancelButton(true);
    }
}
