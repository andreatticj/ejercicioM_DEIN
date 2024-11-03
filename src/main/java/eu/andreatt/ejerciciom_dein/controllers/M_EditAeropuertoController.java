package eu.andreatt.ejerciciom_dein.controllers;

import eu.andreatt.ejerciciom_dein.dao.AeropuertosDao;
import eu.andreatt.ejerciciom_dein.dao.AeropuertosPrivadosDao;
import eu.andreatt.ejerciciom_dein.dao.AeropuertosPublicosDao;
import eu.andreatt.ejerciciom_dein.dao.DireccionesDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class M_EditAeropuertoController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

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
        if (isPrivadoSelected) {
            aeropuertosPrivadosDao.actualizarAeropuertoPrivado(idAeropuerto, Integer.parseInt(txtSocios.getText()));
        } else {
            aeropuertosPublicosDao.actualizarAeropuertoPublico(idAeropuerto, Float.parseFloat(txtFinanciacion.getText()), Integer.parseInt(txtNumTrabajadores.getText()));
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
     * Getter para el RadioButton privado.
     *
     * @return RadioButton correspondiente a aeropuertos privados.
     */
    public RadioButton getRadioPrivado() {
        return rbPrivado;
    }

    /**
     * Setter para el RadioButton privado.
     *
     * @param radioPrivado RadioButton correspondiente a aeropuertos privados.
     */
    public void setRadioPrivado(RadioButton radioPrivado) {
        this.rbPrivado = radioPrivado;
    }

    /**
     * Getter para el RadioButton público.
     *
     * @return RadioButton correspondiente a aeropuertos públicos.
     */
    public RadioButton getRadioPublico() {
        return rbPublico;
    }

    /**
     * Setter para el RadioButton público.
     *
     * @param radioPublico RadioButton correspondiente a aeropuertos públicos.
     */
    public void setRadioPublico(RadioButton radioPublico) {
        this.rbPublico = radioPublico;
    }

    /**
     * Getter para el campo de texto del año de inauguración.
     *
     * @return Campo de texto del año de inauguración.
     */
    public TextField getTextFieldAnioInauguracion() {
        return txtAnioInaguracion;
    }

    /**
     * Setter para el campo de texto del año de inauguración.
     *
     * @param textFieldAnioInauguracion Valor a establecer en el campo de texto del año de inauguración.
     */
    public void setTextFieldAnioInauguracion(String textFieldAnioInauguracion) {
        this.txtAnioInaguracion.setText(textFieldAnioInauguracion);
    }

    /**
     * Getter para el campo de texto de la calle.
     *
     * @return Campo de texto de la calle.
     */
    public TextField getTextFieldCalle() {
        return txtCalle;
    }

    /**
     * Setter para el campo de texto de la calle.
     *
     * @param textFieldCalle Valor a establecer en el campo de texto de la calle.
     */
    public void setTextFieldCalle(String textFieldCalle) {
        this.txtCalle.setText(textFieldCalle);
    }

    /**
     * Getter para el campo de texto de la capacidad.
     *
     * @return Campo de texto de la capacidad.
     */
    public TextField getTextFieldCapacidad() {
        return txtCapaciad;
    }

    /**
     * Setter para el campo de texto de la capacidad.
     *
     * @param textFieldCapacidad Valor a establecer en el campo de texto de la capacidad.
     */
    public void setTextFieldCapacidad(String textFieldCapacidad) {
        this.txtCapaciad.setText(textFieldCapacidad);
    }

    /**
     * Getter para el campo de texto de la ciudad.
     *
     * @return Campo de texto de la ciudad.
     */
    public TextField getTextFieldCiudad() {
        return txtCiudad;
    }

    /**
     * Setter para el campo de texto de la ciudad.
     *
     * @param textFieldCiudad Valor a establecer en el campo de texto de la ciudad.
     */
    public void setTextFieldCiudad(String textFieldCiudad) {
        this.txtCiudad.setText(textFieldCiudad);
    }

    /**
     * Getter para el campo de texto de la financiación.
     *
     * @return Campo de texto de la financiación.
     */
    public TextField getTextFieldFinanciacion() {
        return txtFinanciacion;
    }

    /**
     * Setter para el campo de texto de la financiación.
     *
     * @param textFieldFinanciacion Valor a establecer en el campo de texto de la financiación.
     */
    public void setTextFieldFinanciacion(String textFieldFinanciacion) {
        this.txtFinanciacion.setText(textFieldFinanciacion);
    }

    /**
     * Getter para el campo de texto del nombre.
     *
     * @return Campo de texto del nombre.
     */
    public TextField getTextFieldNombre() {
        return txtNombre;
    }

    /**
     * Setter para el campo de texto del nombre.
     *
     * @param textFieldNombre Valor a establecer en el campo de texto del nombre.
     */
    public void setTextFieldNombre(String textFieldNombre) {
        this.txtNombre.setText(textFieldNombre);
    }

    /**
     * Getter para el campo de texto del número de trabajadores.
     *
     * @return Campo de texto del número de trabajadores.
     */
    public TextField getTextFieldNumTrabajadores() {
        return txtNumTrabajadores;
    }

    /**
     * Setter para el campo de texto del número de trabajadores.
     *
     * @param textFieldNumTrabajadores Valor a establecer en el campo de texto del número de trabajadores.
     */
    public void setTextFieldNumTrabajadores(String textFieldNumTrabajadores) {
        this.txtNumTrabajadores.setText(textFieldNumTrabajadores);
    }

    /**
     * Getter para el campo de texto del número.
     *
     * @return Campo de texto del número.
     */
    public TextField getTextFieldNumero() {
        return txtNumero;
    }

    /**
     * Setter para el campo de texto del número.
     *
     * @param textFieldNumero Valor a establecer en el campo de texto del número.
     */
    public void setTextFieldNumero(String textFieldNumero) {
        this.txtNumero.setText(textFieldNumero);
    }

    /**
     * Getter para el campo de texto del país.
     *
     * @return Campo de texto del país.
     */
    public TextField getTextFieldPais() {
        return txtPais;
    }

    /**
     * Setter para el campo de texto del país.
     *
     * @param textFieldPais Valor a establecer en el campo de texto del país.
     */
    public void setTextFieldPais(String textFieldPais) {
        this.txtPais.setText(textFieldPais);
    }

    /**
     * Getter para el campo de texto de los socios.
     *
     * @return Campo de texto de los socios.
     */
    public TextField getTextFieldSocios() {
        return txtSocios;
    }

    /**
     * Setter para el campo de texto de los socios.
     *
     * @param textFieldSocios Valor a establecer en el campo de texto de los socios.
     */
    public void setTextFieldSocios(String textFieldSocios) {
        this.txtSocios.setText(textFieldSocios);
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
}
