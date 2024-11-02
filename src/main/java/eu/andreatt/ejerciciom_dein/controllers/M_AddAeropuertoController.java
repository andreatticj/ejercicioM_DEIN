package eu.andreatt.ejerciciom_dein.controllers;

import eu.andreatt.ejerciciom_dein.dao.AeropuertosDao;
import eu.andreatt.ejerciciom_dein.dao.AeropuertosPrivadosDao;
import eu.andreatt.ejerciciom_dein.dao.AeropuertosPublicosDao;
import eu.andreatt.ejerciciom_dein.dao.DireccionesDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;

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

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

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

    @FXML
    void actionImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);

        Stage stage = (Stage) btnImagen.getScene().getWindow();
        File archivoSeleccionado = fileChooser.showOpenDialog(stage);

        if (archivoSeleccionado != null) {
            try {
                if (Files.siz
                e(archivoSeleccionado.toPath()) > 65000) {
                    Alert alerta = generarVentana(AlertType.ERROR, "Elige una imagen más pequeña", "ERROR");
                    alerta.show();
                } else {
                    Image image = new Image(archivoSeleccionado.toURI().toString());
                    ruta = archivoSeleccionado.getAbsolutePath();
                    // Asumir que hay un ImageView para mostrar la imagen
                    // imageView.setImage(image); // Si hay un ImageView, descomentar
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** VALIDAR DATOS INTRODUCIDOS */
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
        } else if (rbPrivado.isSelected()) {
            if (txtSocios.getText().isEmpty()) {
                errores.append("El campo NÚMERO DE SOCIOS debe contener texto\n");
            } else if (!esNumeroEntero(txtSocios.getText())) {
                errores.append("El campo NÚMERO DE SOCIOS debe ser un número\n");
            }
        } else {
            errores.append("Seleccione uno de los botones Público/Privado\n");
        }

        return errores.toString();
    }

    /** VALIDAR SI ES UN NÚMERO ENTERO */
    private static boolean esNumeroEntero(String valor) {
        return valor.matches("-?\\d+");
    }

    /** VALIDAR SI ES UN NÚMERO DECIMAL */
    private static boolean esNumeroDecimal(String valor) {
        return valor.matches("-?\\d+(\\.\\d+)?");
    }

    /** GENERAR VENTANA DE ALERTA */
    private Alert generarVentana(AlertType tipo, String mensaje, String titulo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        return alert;
    }

    /** CONFIGURAR FILECHOOSER */
    private void configureFileChooser(FileChooser fileChooser) {
        fileChooser.setTitle("Seleccionar imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg")
        );
    }
}
