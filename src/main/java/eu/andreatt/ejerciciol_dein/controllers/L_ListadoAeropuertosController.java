package eu.andreatt.ejerciciol_dein.controllers;

import eu.andreatt.ejerciciol_dein.application.L_AddAeropuerto;
import eu.andreatt.ejerciciol_dein.application.L_AddAvion;
import eu.andreatt.ejerciciol_dein.application.L_BorrarAvion;
import eu.andreatt.ejerciciol_dein.dao.*;
import eu.andreatt.ejerciciol_dein.model.AeropuertosPublicos;
import eu.andreatt.ejerciciol_dein.model.InformacionAeropuertosPrivados;
import eu.andreatt.ejerciciol_dein.model.InformacionAeropuertosPublicos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class L_ListadoAeropuertosController {

    @FXML
    private TableColumn<InformacionAeropuertosPrivados, String> colCallePrivado;

    @FXML
    private TableColumn<InformacionAeropuertosPublicos, String> colCallePublico;

    @FXML
    private TableColumn<InformacionAeropuertosPrivados, Integer> colCapacidadPrivado;

    @FXML
    private TableColumn<InformacionAeropuertosPublicos, Integer> colCapacidadPublico;

    @FXML
    private TableColumn<InformacionAeropuertosPrivados, String> colCiudadPrivado;

    @FXML
    private TableColumn<InformacionAeropuertosPublicos, String> colCiudadPublico;

    @FXML
    private TableColumn<InformacionAeropuertosPublicos, Float> colFinanciacionPublico;

    @FXML
    private TableColumn<InformacionAeropuertosPrivados, Integer> colIDPrivado;

    @FXML
    private TableColumn<InformacionAeropuertosPublicos, Integer> colIDPublico;

    @FXML
    private TableColumn<InformacionAeropuertosPrivados, Integer> colNSociosPrivado;

    @FXML
    private TableColumn<InformacionAeropuertosPublicos, Integer> colNTrabajadoresPublico;

    @FXML
    private TableColumn<InformacionAeropuertosPrivados, String> colNombrePrivado;

    @FXML
    private TableColumn<InformacionAeropuertosPublicos, String> colNombrePublico;

    @FXML
    private TableColumn<InformacionAeropuertosPrivados, Integer> colNumPrivado;

    @FXML
    private TableColumn<InformacionAeropuertosPublicos, Integer> colNumPublico;

    @FXML
    private TableColumn<InformacionAeropuertosPrivados, String> colPaisPrivado;

    @FXML
    private TableColumn<InformacionAeropuertosPublicos, String> colPaisPublico;

    @FXML
    private TableColumn<InformacionAeropuertosPrivados, Integer> colAnioPrivado;

    @FXML
    private TableColumn<InformacionAeropuertosPublicos, Integer> colAnioPublico;

    @FXML
    private Menu menuAeropuerto;

    @FXML
    private Menu menuAviones;

    @FXML
    private MenuItem miActDesAviones;

    @FXML
    private MenuItem miAniadirAeropuerto;

    @FXML
    private MenuItem miAniadirAviones;

    @FXML
    private MenuItem miBorrarAeropuerto;

    @FXML
    private MenuItem miBorrarAviones;

    @FXML
    private MenuItem miEditarAeropuerto;

    @FXML
    private MenuItem miMostrarAeropuerto;

    @FXML
    private ToggleGroup rbGroup;

    @FXML
    private RadioButton rbPrivados;

    @FXML
    private RadioButton rbPublicos;

    @FXML
    private TableView<InformacionAeropuertosPrivados> tvAeropuertosPrivados;

    @FXML
    private TableView<AeropuertosPublicos> tvAeropuertosPublicos;

    @FXML
    private TextField txtFiltrar;

    private AeropuertosPrivadosDao aeropuertosPrivadosDao;
    private AeropuertosPublicosDao aeropuertosPublicosDao;
    private AeropuertosDao aeropuertosDao;
    private DireccionesDao direccionesDao;
    private AvionesDao avionesDao;

    private ObservableList<InformacionAeropuertosPrivados> aeropuertosPrivadosExistentes;
    private ObservableList<InformacionAeropuertosPublicos>aeropuertosPublicosExistentes;

    @FXML
    void actDesAviones(ActionEvent event) {
        // Acción para activar/desactivar aviones
    }

    /** EVENTO - AL PULSAR PRIVADOS */
    @FXML
    void actPrivados(ActionEvent event) {
        tvAeropuertosPublicos.setVisible(false);
        tvAeropuertosPrivados.setVisible(true);
    }

    /** EVENTO - AL PULSAR PÚBLICOS */
    @FXML
    void actPublicos(ActionEvent event) {
        tvAeropuertosPrivados.setVisible(false);
        tvAeropuertosPublicos.setVisible(true);
    }

    @FXML
    void aniadirAeropuerto(ActionEvent event) {
        new L_AddAeropuerto();
    }

    @FXML
    void aniadirAviones(ActionEvent event) {
        new L_AddAvion();
    }

    @FXML
    void borrarAeropuerto(ActionEvent event) {
        // Acción para borrar un aeropuerto
    }

    @FXML
    void borrarAviones(ActionEvent event) {
        new L_BorrarAvion();
    }

    @FXML
    void editarAeropuerto(ActionEvent event) {
        // Acción para editar un aeropuerto
    }

    @FXML
    void mostrarAeropuerto(ActionEvent event) {
        // Acción para mostrar detalles de un aeropuerto
    }

    @FXML
    void initialize() {
        aeropuertosDao = new AeropuertosDao();
        direccionesDao = new DireccionesDao();
        avionesDao = new AvionesDao();

        //Cargar instancias de aeropuertos
        aeropuertosPrivadosDao = new AeropuertosPrivadosDao();
        aeropuertosPublicosDao = new AeropuertosPublicosDao();

        //Instanciar listas observables y cargarlas
        aeropuertosPrivadosExistentes = FXCollections.observableArrayList();
        aeropuertosPublicosExistentes = FXCollections.observableArrayList();

        aeropuertosPrivadosExistentes = aeropuertosPrivadosDao.cargarAeropuertosPrivados();
        aeropuertosPublicosExistentes = aeropuertosPublicosDao.cargarAeropuertosPublicos();

        // Configuración de las columnas de la tabla de aeropuertos públicos
        colIDPublico.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombrePublico.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCallePublico.setCellValueFactory(new PropertyValueFactory<>("calle"));
        colNumPublico.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colCiudadPublico.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colPaisPublico.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colCapacidadPublico.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        colNTrabajadoresPublico.setCellValueFactory(new PropertyValueFactory<>("numTrabajadores"));
        colFinanciacionPublico.setCellValueFactory(new PropertyValueFactory<>("financiacion"));
        colAnioPublico.setCellValueFactory(new PropertyValueFactory<>("anioInauguracion"));

        // Configuración de las columnas de la tabla de aeropuertos privados
        colIDPrivado.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombrePrivado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCallePrivado.setCellValueFactory(new PropertyValueFactory<>("calle"));
        colNumPrivado.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colCiudadPrivado.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colPaisPrivado.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colCapacidadPrivado.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        colNSociosPrivado.setCellValueFactory(new PropertyValueFactory<>("numeroSocios"));
        colAnioPrivado.setCellValueFactory(new PropertyValueFactory<>("anioInauguracion"));

        tvAeropuertosPrivados.setItems(aeropuertosPrivadosExistentes);
    }
}
