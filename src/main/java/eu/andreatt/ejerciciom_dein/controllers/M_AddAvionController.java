package eu.andreatt.ejerciciom_dein.controllers;

import eu.andreatt.ejerciciom_dein.dao.AeropuertosDao;
import eu.andreatt.ejerciciom_dein.dao.AvionesDao;
import eu.andreatt.ejerciciom_dein.model.Aeropuertos;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class M_AddAvionController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Aeropuertos> cmbAeropuerto;

    @FXML
    private RadioButton rbActivado;

    @FXML
    private RadioButton rbDesactivado;

    @FXML
    private ToggleGroup rbGroup;

    @FXML
    private TextField txtAsientos;

    @FXML
    private TextField txtModelo;

    @FXML
    private Label txtVelMax;

    private AeropuertosDao aeropuertosDao;
    private AvionesDao avionesDao;

    private ObservableList<Aeropuertos> elementosCombo;

    /**
     * Maneja la acción de cancelar y cierra la ventana modal.
     *
     * @param event El evento de acción que dispara este método.
     */
    @FXML
    void actCancelarAviones(ActionEvent event) {
        // Cerrar ventana modal
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Maneja la acción de guardar los datos del avión.
     * Valida los datos introducidos y si son correctos, los guarda en la base de datos.
     * Muestra mensajes de alerta en caso de errores o éxito.
     *
     * @param event El evento de acción que dispara este método.
     */
    @FXML
    void actGuardarAviones(ActionEvent event) {
        String errores = validarDatos();

        if (errores.length() != 0) {
            // Mensaje de alerta
            Alert alerta = generarVentana(Alert.AlertType.ERROR, errores, "ERROR");
            alerta.show();

            // Cerrar ventana modal
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
        } else {
            // Validar que no existe el modelo en el aeropuerto
            Aeropuertos aeropuertoSeleccionado = cmbAeropuerto.getSelectionModel().getSelectedItem();
            int idAeropuerto = aeropuertosDao.dameIdDeAeropuerto(aeropuertoSeleccionado.getNombre(),
                    aeropuertoSeleccionado.getAnioInauguracion(),
                    aeropuertoSeleccionado.getCapacidad(),
                    aeropuertoSeleccionado.getId_direccion());
            boolean existeModelo = avionesDao.existeModeloEnAeropuerto(txtModelo.getText(), idAeropuerto);

            if (existeModelo) {
                // Mensaje de alerta
                Alert alerta = generarVentana(Alert.AlertType.ERROR, "El MODELO existe en el AEROPUERTO seleccionado", "ERROR");
                alerta.show();
            } else {
                int activado = rbActivado.isSelected() ? 1 : 0;
                avionesDao.insertarAvion(txtModelo.getText(), Integer.parseInt(txtAsientos.getText()),
                        Float.parseFloat(txtVelMax.getText()), activado, idAeropuerto);

                // Mensaje de alerta
                Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "Se ha AÑADIDO un avión", "INFO");
                alerta.show();

                limpiarVentana();
            }
        }
    }

    /**
     * Valida los datos ingresados en los campos de texto y verifica si hay errores.
     *
     * @return Un String con los mensajes de error, o vacío si no hay errores.
     */
    private String validarDatos() {
        String errores = "";

        // Validar campos numéricos
        if (!esNumeroEntero(txtAsientos.getText())) {
            errores += "El NÚMERO DE ASIENTOS debe ser un número\n";
        }

        if (!esNumeroDecimal(txtVelMax.getText())) {
            errores += "La VELOCIDAD MÁXIMA debe ser un número decimal\n";
        }

        // Validar modelo
        if (txtModelo.getText().length() == 0) {
            errores += "Se debe asignar el MODELO del avión\n";
        }

        // Validar Radios
        if (rbActivado.isSelected() == false && rbDesactivado.isSelected() == false) {
            errores += "Se debe SELECCIONAR uno de los radios\n";
        }

        return errores;
    }

    /**
     * Verifica si la cadena proporcionada es un número decimal.
     *
     * @param valor La cadena a verificar.
     * @return true si la cadena es un número decimal; false en caso contrario.
     */
    private static boolean esNumeroDecimal(String valor) {
        return valor.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Verifica si la cadena proporcionada es un número entero.
     *
     * @param valor La cadena a verificar.
     * @return true si la cadena es un número entero; false en caso contrario.
     */
    private static boolean esNumeroEntero(String valor) {
        return valor.matches("-?\\d+");
    }

    /**
     * Crea y devuelve una alerta con el mensaje y título proporcionados.
     *
     * @param tipoDeAlerta El tipo de alerta a generar.
     * @param mensaje El mensaje que se mostrará en la alerta.
     * @param title El título de la alerta.
     * @return Un objeto Alert configurado.
     */
    private Alert generarVentana(Alert.AlertType tipoDeAlerta, String mensaje, String title) {
        Alert alerta = new Alert(tipoDeAlerta);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.setTitle(title);
        return alerta;
    }

    /**
     * Limpia los datos de los campos de texto y restablece los valores predeterminados.
     */
    private void limpiarVentana() {
        txtAsientos.setText("");
        txtModelo.setText("");
        txtVelMax.setText("");
        rbActivado.setSelected(false);
        rbDesactivado.setSelected(false);
        cmbAeropuerto.setValue(elementosCombo.get(0));
    }
}
