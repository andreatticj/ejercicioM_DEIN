package eu.andreatt.ejerciciol_dein.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class L_AddAvionController {

    @FXML
    private Button btnCancelarAviones;

    @FXML
    private Button btnGuardarAviones;

    @FXML
    private ComboBox<?> cmbAeropuerto;

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

    @FXML
    void actCancelarAviones(ActionEvent event) {

    }

    @FXML
    void actGuardarAviones(ActionEvent event) {

    }

}
