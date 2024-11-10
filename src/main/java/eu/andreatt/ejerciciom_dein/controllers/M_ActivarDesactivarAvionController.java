package eu.andreatt.ejerciciom_dein.controllers;

import eu.andreatt.ejerciciom_dein.dao.AeropuertosDao;
import eu.andreatt.ejerciciom_dein.dao.AvionesDao;
import eu.andreatt.ejerciciom_dein.model.Aeropuertos;
import eu.andreatt.ejerciciom_dein.model.Aviones;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Controlador para la ventana de activación/desactivación de aviones.
 * Gestiona la carga de aeropuertos y aviones, así como la actualización
 * del estado de activación de un avión seleccionado.
 */
public class M_ActivarDesactivarAvionController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Aeropuertos> cmbAeropuertos;

    @FXML
    private ComboBox<Aviones> cmbAviones;

    @FXML
    private RadioButton rbActivado;

    @FXML
    private RadioButton rbDesactivado;

    @FXML
    private ToggleGroup grpRadioBtn;

    private AeropuertosDao aeropuertosDao;
    private AvionesDao avionesDao;

    private ObservableList<Aeropuertos> elementosComboAeropuertos;
    private ObservableList<Aviones> elementosComboAviones;

    /**
     * Inicializa el controlador. Este metodo se llama automáticamente
     * después de que se cargue el FXML. Carga los aeropuertos en el
     * ComboBox y establece los listeners necesarios para la selección.
     */
    @FXML
    void initialize() {
        // Instanciar Dao
        aeropuertosDao = new AeropuertosDao();
        avionesDao = new AvionesDao();

        // Cargar combos
        elementosComboAeropuertos = aeropuertosDao.cargarAeropuertos();
        cmbAeropuertos.setItems(elementosComboAeropuertos);

        // Seleccionar el primer aeropuerto por defecto
        if (!elementosComboAeropuertos.isEmpty()) {
            cmbAeropuertos.setValue(elementosComboAeropuertos.get(0));
            cargarAvionesPorAeropuerto(elementosComboAeropuertos.get(0));
        }

        // Listener para el ComboBox de aeropuertos
        cmbAeropuertos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarAvionesPorAeropuerto(newValue);
            }
        });

        // Listener para el ComboBox de aviones
        cmbAviones.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            actualizarEstadoRadioButton();
        });
        btnGuardar.setDefaultButton(true);
        btnCancelar.setCancelButton(true);
    }

    /**
     * Carga los aviones disponibles para el aeropuerto seleccionado en el ComboBox.
     *
     * @param aeropuerto El aeropuerto del cual se quieren cargar los aviones.
     */
    private void cargarAvionesPorAeropuerto(Aeropuertos aeropuerto) {
        elementosComboAviones = avionesDao.dameAvionesPorAeropuerto(aeropuerto.getId());
        cmbAviones.setItems(elementosComboAviones);

        if (!elementosComboAviones.isEmpty()) {
            cmbAviones.setValue(elementosComboAviones.get(0)); // Seleccionar el primer avión
        } else {
            cmbAviones.setValue(null); // Si no hay aviones, asegurarse de que esté vacío
        }
        // Actualizar el estado del radio button
        actualizarEstadoRadioButton();
    }

    /**
     * Actualiza el estado de los radio buttons según el avión seleccionado.
     * Si el avión está activado, se selecciona el radio correspondiente.
     * Si no hay avión seleccionado, ambos radio buttons se desmarcan.
     */
    private void actualizarEstadoRadioButton() {
        if (cmbAviones.getSelectionModel().getSelectedItem() != null) {
            int activado = cmbAviones.getSelectionModel().getSelectedItem().getActivado();
            if (activado == 0) {
                rbDesactivado.setSelected(true);
                rbActivado.setSelected(false);
            } else {
                rbDesactivado.setSelected(false);
                rbActivado.setSelected(true);
            }
        } else {
            // Si no hay avión seleccionado, desmarcar ambos
            rbActivado.setSelected(false);
            rbDesactivado.setSelected(false);
        }
    }

    /**
     * Maneja el evento de cancelación. Cierra la ventana modal cuando se
     * hace clic en el botón "Cancelar".
     *
     * @param event El evento de acción del botón.
     */
    @FXML
    void actCancelar(ActionEvent event) {
        // Cerrar ventana modal
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Maneja el evento de guardar. Actualiza el estado de activación del avión
     * seleccionado y muestra un mensaje de alerta sobre el resultado de la operación.
     *
     * @param event El evento de acción del botón.
     */
    @FXML
    void actGuardar(ActionEvent event) {
        // Actualizar campo activado en base al radio seleccionado
        if (cmbAviones.getSelectionModel().getSelectedItem() != null) {
            int id = cmbAviones.getSelectionModel().getSelectedItem().getId();
            int activado = rbActivado.isSelected() ? 1 : 0;
            avionesDao.actualizarAvionActivo(id, activado);

            // Mensaje de alerta
            Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "Se ha actualizado el estado del AVIÓN", "INFO");
            alerta.show();
        } else {
            // Mensaje de alerta
            Alert alerta = generarVentana(Alert.AlertType.ERROR, "No se ha seleccionado ningún AVIÓN", "ERROR");
            alerta.show();
        }
    }

    /**
     * Genera una ventana de alerta personalizada.
     *
     * @param tipoDeAlerta El tipo de alerta a crear.
     * @param mensaje El mensaje que se mostrará en la alerta.
     * @param title El título de la ventana de alerta.
     * @return La alerta creada.
     */
    private Alert generarVentana(Alert.AlertType tipoDeAlerta, String mensaje, String title) {
        Alert alerta = new Alert(tipoDeAlerta);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.setTitle(title);
        return alerta;
    }
}
