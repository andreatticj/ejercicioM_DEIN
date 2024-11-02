package eu.andreatt.ejerciciom_dein.controllers;

import eu.andreatt.ejerciciom_dein.dao.AeropuertosDao;
import eu.andreatt.ejerciciom_dein.dao.AvionesDao;
import eu.andreatt.ejerciciom_dein.model.Aeropuertos;
import eu.andreatt.ejerciciom_dein.model.Aviones;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class M_BorrarAvionController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<Aeropuertos> cmbAeropuertos;

    @FXML
    private ComboBox<Aviones> cmbAviones;

    private AeropuertosDao aeropuertosDao;
    private AvionesDao avionesDao;

    private ObservableList<Aeropuertos> elementosComboAeropuertos;
    private ObservableList<Aviones> elementosComboAviones;

    /** INITIALIZE - INICIALIZAR CARGA DE DATOS */
    @FXML
    void initialize() {
        // Instanciar Dao
        aeropuertosDao = new AeropuertosDao();
        avionesDao = new AvionesDao();


        //Cargar combos
        elementosComboAeropuertos = aeropuertosDao.cargarAeropuertos();
        cmbAeropuertos.setItems(elementosComboAeropuertos);
        cmbAeropuertos.setValue(elementosComboAeropuertos.get(0));

        elementosComboAviones = avionesDao.dameAvionesPorAeropuerto(elementosComboAeropuertos.get(0).getId());
        cmbAviones.setItems(elementosComboAviones);
        cmbAviones.setValue(elementosComboAviones.get(0));

        //Actualizar combo de aviones en base al aeropuerto seleccionado
        cmbAeropuertos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                elementosComboAviones = avionesDao.dameAvionesPorAeropuerto(newValue.getId());
                if(elementosComboAviones.size()!=0) {
                    cmbAviones.setItems(elementosComboAviones);
                    cmbAviones.setValue(elementosComboAviones.get(0));
                }else {
                    cmbAviones.setItems(null);
                }
            }
        });
        btnGuardar.setDefaultButton(true);
    }



    @FXML
    void actBorrarAvion(ActionEvent event) {
        if (cmbAviones.getSelectionModel().getSelectedItem() != null) {
            int id = cmbAviones.getSelectionModel().getSelectedItem().getId();
            avionesDao.borrarAvion(id);


            // Mensaje de alerta
            Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "Se ha BORRADO el AVIÓN", "INFO");
            alerta.show();
        } else {
            // Mensaje de alerta
            Alert alerta = generarVentana(Alert.AlertType.ERROR, "No se ha seleccionado ningún AVIÓN", "ERROR");
            alerta.show();
        }
    }

    @FXML
    void btnCancelarAvion(ActionEvent event) {
        // Cerrar ventana modal
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /** Generar ventana de alerta */
    private Alert generarVentana(Alert.AlertType tipoDeAlerta, String mensaje, String title) {
        Alert alerta = new Alert(tipoDeAlerta);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.setTitle(title);
        return alerta;
    }
}
