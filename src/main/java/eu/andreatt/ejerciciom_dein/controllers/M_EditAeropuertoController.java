package eu.andreatt.ejerciciom_dein.controllers;

import eu.andreatt.ejerciciom_dein.dao.AeropuertosDao;
import eu.andreatt.ejerciciom_dein.dao.AeropuertosPrivadosDao;
import eu.andreatt.ejerciciom_dein.dao.AeropuertosPublicosDao;
import eu.andreatt.ejerciciom_dein.dao.DireccionesDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

public class M_EditAeropuertoController {

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
    private ImageView imageView;

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

    private boolean isPrivadoSelected;

    private boolean isPublicSelected;

    private int idDireccion;

    private int idAeropuerto;

    private AeropuertosDao aeropuertosDao;
    private DireccionesDao direccionesDao;
    private AeropuertosPrivadosDao aeropuertosPrivadosDao;
    private AeropuertosPublicosDao aeropuertosPublicosDao;
    /** Imagen del animal en formato Blob. */
    private Blob imagen;

    /**
     * Inicializa la carga de datos y los objetos DAO necesarios.
     */
    @FXML
    void initialize() {
        // Instanciar DAO
        aeropuertosDao = new AeropuertosDao();
        direccionesDao = new DireccionesDao();
        aeropuertosPrivadosDao = new AeropuertosPrivadosDao();
        aeropuertosPublicosDao = new AeropuertosPublicosDao();
        btnGuardar.setDefaultButton(true);
        btnCancelar.setCancelButton(true);
    }

    /**
     * Evento que se ejecuta al pulsar el botón "Cancelar".
     * Muestra un mensaje de alerta y cierra la ventana modal.
     *
     * @param event Evento de acción.
     */
    @FXML
    void cancelar(ActionEvent event) {
        // Mensaje de alerta
        Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "Se ha CANCELADO la edición del aeropuerto", "INFO");
        alerta.show();

        // Cerrar ventana modal
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Evento que se ejecuta al pulsar el botón "Guardar".
     * Valida y actualiza la información del aeropuerto y su dirección.
     * Muestra un mensaje de alerta según el resultado de la operación.
     *
     * @param event Evento de acción.
     */
    @FXML
    void guardar(ActionEvent event) {
        String errores = "";

        // Existe Dirección
        if (direccionesDao.existeDireccion(txtPais.getText(), txtCiudad.getText(), txtCalle.getText(), Integer.parseInt(txtNumero.getText())) != -1) {
            errores += "-La DIRECCIÓN, porque ya existe\n";
        }

        // Existe Aeropuerto
        if (aeropuertosDao.existeNombreAeropuerto(txtNombre.getText())) {
            errores += "-El AEROPUERTO, porque ya existe\n";
        }

        // Actualizar
        direccionesDao.actualizarDireccion(idDireccion, txtPais.getText(), txtCiudad.getText(), txtCalle.getText(), Integer.parseInt(txtNumero.getText()));
        aeropuertosDao.actualizarAeropuerto(idAeropuerto, txtNombre.getText(), Integer.parseInt(txtAnioInaguracion.getText()), Integer.parseInt(txtCapaciad.getText()), idDireccion);

        if (imagen != null){
            aeropuertosDao.insertarImagen(imagen,idAeropuerto);
        }

        if (isPrivadoSelected) {
            aeropuertosPrivadosDao.actualizarAeropuertoPrivado(idAeropuerto, Integer.parseInt(txtSocios.getText()));
        } else {
            aeropuertosPublicosDao.actualizarAeropuertoPublico(idAeropuerto, Double.parseDouble(txtFinanciacion.getText()), Integer.parseInt(txtNumTrabajadores.getText()));
        }

        // Mostrar información
        if (errores.trim().length() == 0) {
            // Mensaje de alerta
            Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "Se han ACTUALIZADO los datos del aeropuerto", "INFO");
            alerta.show();
        } else {
            // Mensaje de alerta
            Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "Se han ACTUALIZADO los datos del aeropuerto a excepción de:\n" + errores, "INFO");
            alerta.show();
        }

        // Cerrar ventana modal
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();

    }

    /**
     * Metodos getter y setter para los atributos privados.
     * Se utilizan para acceder y modificar el estado de los atributos desde fuera de la clase.
     */

    public RadioButton getRadioPrivado() {
        return rbPrivado;
    }

    public void setRadioPrivado(RadioButton radioPrivado) {
        this.rbPrivado = radioPrivado;
    }

    public RadioButton getRadioPublico() {
        return rbPublico;
    }

    public void setRadioPublico(RadioButton radioPublico) {
        this.rbPublico = radioPublico;
    }

    public TextField getTextFieldAnioInauguracion() {
        return txtAnioInaguracion;
    }

    public void setTextFieldAnioInauguracion(String textFieldAnioInauguracion) {
        this.txtAnioInaguracion.setText(textFieldAnioInauguracion);
    }

    public TextField getTextFieldCalle() {
        return txtCalle;
    }

    public void setTextFieldCalle(String textFieldCalle) {
        this.txtCalle.setText(textFieldCalle);
    }

    public TextField getTextFieldCapacidad() {
        return txtCapaciad;
    }

    public void setTextFieldCapacidad(String textFieldCapacidad) {
        this.txtCapaciad.setText(textFieldCapacidad);
    }

    public TextField getTextFieldCiudad() {
        return txtCiudad;
    }

    public void setTextFieldCiudad(String textFieldCiudad) {
        this.txtCiudad.setText(textFieldCiudad);
    }

    public TextField getTextFieldFinanciacion() {
        return txtFinanciacion;
    }

    public void setTextFieldFinanciacion(String textFieldFinanciacion) {
        this.txtFinanciacion.setText(textFieldFinanciacion);
    }

    public TextField getTextFieldNombre() {
        return txtNombre;
    }

    public void setTextFieldNombre(String textFieldNombre) {
        this.txtNombre.setText(textFieldNombre);
    }

    public TextField getTextFieldNumSocios() {
        return txtSocios;
    }

    public void setTextFieldNumSocios(String textFieldNumSocios) {
        this.txtSocios.setText(textFieldNumSocios);
    }

    public TextField getTextFieldNumTrabajadores() {
        return txtNumTrabajadores;
    }

    public void setTextFieldNumTrabajadores(String textFieldNumTrabajadores) {
        this.txtNumTrabajadores.setText(textFieldNumTrabajadores);
    }

    public TextField getTextFieldNumero() {
        return txtNumero;
    }

    public void setTextFieldNumero(String textFieldNumero) {
        this.txtNumero.setText(textFieldNumero);
    }

    public TextField getTextFieldPais() {
        return txtPais;
    }

    public void setTextFieldPais(String textFieldPais) {
        this.txtPais.setText(textFieldPais);
    }

    public boolean isPrivadoSelected() {
        return isPrivadoSelected;
    }

    public boolean isPublicSelected() {
        return isPublicSelected;
    }

    public void setPrivadoSelected(boolean isPrivadoSelected) {
        this.isPrivadoSelected = isPrivadoSelected;
    }

    public void setPublicSelected(boolean isPublicSelected) {
        this.isPublicSelected = isPublicSelected;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getIdAeropuerto() {
        return idAeropuerto;
    }

    public void setIdAeropuerto(int idAeropuerto) {
        this.idAeropuerto = idAeropuerto;
    }

    /**
     * Establece el radio de Aeropuerto Privado como seleccionado
     * y ajusta la visibilidad de los componentes relacionados.
     */
    public void habilitarRadioPrivados() {
        rbPrivado.setSelected(true);
        rbPublico.setSelected(false);
        lblSocios.setVisible(true);
        txtSocios.setVisible(true);
        lblFinanciacion.setVisible(false);
        txtFinanciacion.setVisible(false);
        lblNumTrabajadores.setVisible(false);
        txtNumTrabajadores.setVisible(false);
    }

    /**
     * Establece el radio de Aeropuerto Público como seleccionado
     * y ajusta la visibilidad de los componentes relacionados.
     */
    public void habilitarRadioPublicos() {
        rbPrivado.setSelected(false);
        rbPublico.setSelected(true);
        lblSocios.setVisible(false);
        txtSocios.setVisible(false);
        lblFinanciacion.setVisible(true);
        txtFinanciacion.setVisible(true);
        lblNumTrabajadores.setVisible(true);
        txtNumTrabajadores.setVisible(true);
    }

    /**
     * Metodo para generar y devolver una ventana de alerta.
     *
     * @param tipo Tipo de alerta (INFORMATION, WARNING, ERROR, etc.).
     * @param mensaje Mensaje que se mostrará en la alerta.
     * @param titulo Título de la alerta.
     * @return Objeto de alerta configurado.
     */
    private Alert generarVentana(Alert.AlertType tipo, String mensaje, String titulo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        return alerta;
    }

    /**
     * Acción que se ejecuta cuando el usuario selecciona una imagen para el animal. Permite cargar una imagen desde
     * el sistema de archivos y mostrarla en el {@link ImageView}.
     *
     * @param event El evento de acción generado por el usuario.
     */
    @FXML
    void actionImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg", "*.gif"));

        // Muestra un cuadro de diálogo para seleccionar un archivo de imagen.
        File archivo = fileChooser.showOpenDialog(btnImagen.getScene().getWindow());

        if (!esTamanoValido(archivo)) {
            Alert alerta = generarVentana(Alert.AlertType.ERROR, "La imagen no puede tener un tamaño mayor a 64kb", "ERROR");
            alerta.show();
            return;
        }

        if (archivo != null) {
            try {

                // Intentar convertir el archivo a un Blob
                Blob blob = aeropuertosDao.convertFileToBlob(archivo);
                this.imagen = blob; // Asignar el Blob a la variable de instancia

                // Abrir un InputStream para la imagen y establecerla en el ImageView
                try (InputStream imagenStream = new FileInputStream(archivo)) {
                    imageView.setImage(new Image(imagenStream));
                }
            }  catch (IOException e) {
                // Muestra una alerta en caso de error al cargar la imagen.
                generarVentana(Alert.AlertType.ERROR, "Error al cargar la imagen: " + e.getMessage(), "ERROR").show();
            }

            try {
                // Convierte la imagen seleccionada en un formato adecuado para el ImageView.
                BufferedImage bufferedImage = ImageIO.read(archivo);
                Image imagenFX = SwingFXUtils.toFXImage(bufferedImage, null);
                imageView.setImage(imagenFX);
            } catch (IOException e) {
                // Muestra una alerta en caso de error al cargar la imagen.
                generarVentana(Alert.AlertType.ERROR, "Error al cargar la imagen: " + e.getMessage(), "ERROR").show();
            }
        }
    }

    private boolean esTamanoValido(File file) {
        double kbs = (double) file.length() / 1024;
        return kbs <= 64; // Tamaño máximo de 64 KB
    }




}
