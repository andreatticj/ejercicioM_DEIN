package eu.andreatt.ejerciciom_dein.controllers;

import eu.andreatt.ejerciciom_dein.application.*;
import eu.andreatt.ejerciciom_dein.dao.*;
import eu.andreatt.ejerciciom_dein.model.Aviones;
import eu.andreatt.ejerciciom_dein.model.InformacionAeropuertosPrivados;
import eu.andreatt.ejerciciom_dein.model.InformacionAeropuertosPublicos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class M_ListadoAeropuertosController {

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
    private TableView<InformacionAeropuertosPublicos> tvAeropuertosPublicos;

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
        new M_ActivarDesactivarAvion();
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
        new M_AddAeropuerto();
    }

    @FXML
    void aniadirAviones(ActionEvent event) {
        new M_AddAvion();
    }

    @FXML
    void borrarAeropuerto(ActionEvent event) {
        //Verificar si se seleccionó una fila en la tabla de aeropuertos privados
        InformacionAeropuertosPrivados aeropuertoPrivadoSeleccionado = tvAeropuertosPrivados.getSelectionModel().getSelectedItem();

        // Verificar si se seleccionó una fila en la tabla de aeropuertos públicos
        InformacionAeropuertosPublicos aeropuertoPublicoSeleccionado = tvAeropuertosPublicos.getSelectionModel().getSelectedItem();

        //Validar que se ha seleccionado un aeropuerto
        if(aeropuertoPrivadoSeleccionado == null && aeropuertoPublicoSeleccionado == null) {
            Alert alerta = generarVentana(Alert.AlertType.ERROR, "No se ha seleccionado ningún aeropuerto", "ERROR");
            alerta.show();
        }else{
            //Solicitar confirmación
            Alert a = generarVentana(Alert.AlertType.CONFIRMATION, "¿Desea BORRAR el aeropuerto?", "CONFIRMACIÓN");
            a.showAndWait();

            //Borrar aeropuerto en confirmación
            if(a.getResult().getButtonData().toString().equals("OK_DONE")) {
                if (aeropuertoPrivadoSeleccionado != null) {
                    String pais = aeropuertoPrivadoSeleccionado.getPais();
                    String ciudad = aeropuertoPrivadoSeleccionado.getCiudad();
                    String calle = aeropuertoPrivadoSeleccionado.getCalle();
                    int numero = aeropuertoPrivadoSeleccionado.getNumero();
                    int direccion = direccionesDao.existeDireccion(pais, ciudad, calle, numero);
                    int id = aeropuertoPrivadoSeleccionado.getId();
                    String nombre = aeropuertoPrivadoSeleccionado.getNombre();
                    int anioInauguracion = aeropuertoPrivadoSeleccionado.getAnioInauguracion();
                    int capacidad = aeropuertoPrivadoSeleccionado.getCapacidad();

                    aeropuertosPrivadosDao.borrarAeropuertoPrivado(id);
                    aeropuertosDao.borrarAeropuerto(nombre, anioInauguracion, capacidad, direccion);

                } else{
                    String pais = aeropuertoPublicoSeleccionado.getPais();
                    String ciudad = aeropuertoPublicoSeleccionado.getCiudad();
                    String calle = aeropuertoPublicoSeleccionado.getCalle();
                    int numero = aeropuertoPublicoSeleccionado.getNumero();
                    int direccion = direccionesDao.existeDireccion(pais, ciudad, calle, numero);
                    int id = aeropuertoPublicoSeleccionado.getId();
                    String nombre = aeropuertoPublicoSeleccionado.getNombre();
                    int anioInauguracion = aeropuertoPublicoSeleccionado.getAnioInauguracion();
                    int capacidad = aeropuertoPublicoSeleccionado.getCapacidad();

                    aeropuertosPublicosDao.borrarAeropuertoPublico(id);
                    aeropuertosDao.borrarAeropuerto(nombre, anioInauguracion, capacidad, direccion);
                }

                //Mensaje de alerta
                Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "Se ha BORRADO del aeropuerto", "INFO");
                alerta.show();
            }else {
                Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "Se ha cancelado el BORRADO del aeropuerto", "INFO");
                alerta.show();
            }
        }
    }

    @FXML
    void borrarAviones(ActionEvent event) {
        new M_BorrarAvion();
    }

    @FXML
    void editarAeropuerto(ActionEvent event) {
        // Verificar si se seleccionó una fila en la tabla de aeropuertos privados
        InformacionAeropuertosPrivados aeropuertoPrivadoSeleccionado = tvAeropuertosPrivados.getSelectionModel().getSelectedItem();

        // Verificar si se seleccionó una fila en la tabla de aeropuertos públicos
        InformacionAeropuertosPublicos aeropuertoPublicoSeleccionado = tvAeropuertosPublicos.getSelectionModel().getSelectedItem();

        // Validar que se ha seleccionado un aeropuerto
        if (aeropuertoPrivadoSeleccionado == null && aeropuertoPublicoSeleccionado == null) {
            Alert alerta = generarVentana(Alert.AlertType.ERROR, "No se ha seleccionado ningún aeropuerto", "ERROR");
            alerta.show();
        } else {
            // Determinar el tipo de aeropuerto basado en el aeropuerto seleccionado
            if (aeropuertoPrivadoSeleccionado != null) {
                // Datos de aeropuerto privado
                String nombre = aeropuertoPrivadoSeleccionado.getNombre();
                String pais = aeropuertoPrivadoSeleccionado.getPais();
                String ciudad = aeropuertoPrivadoSeleccionado.getCiudad();
                String calle = aeropuertoPrivadoSeleccionado.getCalle();
                int numero = aeropuertoPrivadoSeleccionado.getNumero();
                int anioInauguracion = aeropuertoPrivadoSeleccionado.getAnioInauguracion();
                int capacidad = aeropuertoPrivadoSeleccionado.getCapacidad();
                int numSocios = aeropuertoPrivadoSeleccionado.getNumeroSocios();
                int direccion = direccionesDao.existeDireccion(pais, ciudad, calle, numero);
                int id = aeropuertoPrivadoSeleccionado.getId();

                // Instanciar ventana de edición para aeropuerto privado
                M_EditarAeropuerto ventanaEditar = new M_EditarAeropuerto(nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, false, true, numSocios, 0, 0, direccion, id);
            } else {
                // Datos de aeropuerto público
                String nombre = aeropuertoPublicoSeleccionado.getNombre();
                String pais = aeropuertoPublicoSeleccionado.getPais();
                String ciudad = aeropuertoPublicoSeleccionado.getCiudad();
                String calle = aeropuertoPublicoSeleccionado.getCalle();
                int numero = aeropuertoPublicoSeleccionado.getNumero();
                int anioInauguracion = aeropuertoPublicoSeleccionado.getAnioInauguracion();
                int capacidad = aeropuertoPublicoSeleccionado.getCapacidad();
                float financiacion = aeropuertoPublicoSeleccionado.getFinanciacion();
                int numTrabajadores = aeropuertoPublicoSeleccionado.getNumTrabajadores();
                int direccion = direccionesDao.existeDireccion(pais, ciudad, calle, numero);
                int id = aeropuertoPublicoSeleccionado.getId();

                // Instanciar ventana de edición para aeropuerto público
                M_EditarAeropuerto ventanaEditar = new M_EditarAeropuerto(nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, true, false, 0, financiacion, numTrabajadores, direccion, id);
            }
        }
    }



    @FXML
    void mostrarAeropuerto(ActionEvent event) {
        //Verificar si se seleccionó una fila en la tabla de aeropuertos privados
        InformacionAeropuertosPrivados aeropuertoPrivadoSeleccionado = tvAeropuertosPrivados.getSelectionModel().getSelectedItem();

        // Verificar si se seleccionó una fila en la tabla de aeropuertos públicos
        InformacionAeropuertosPublicos aeropuertoPublicoSeleccionado = tvAeropuertosPublicos.getSelectionModel().getSelectedItem();

        //Validar que se ha seleccionado un aeropuerto
        if(aeropuertoPrivadoSeleccionado == null && aeropuertoPublicoSeleccionado == null) {
            Alert alerta = generarVentana(Alert.AlertType.ERROR, "No se ha seleccionado ningún aeropuerto", "ERROR");
            alerta.show();
        }else{
            //Declarar variables
            String pais, ciudad, calle, nombre;
            int numero, anioInauguracion, capacidad, numSocios=0, numTrabajadores=0, id;
            float financiacion=0;

            //Cargar variables
            if (aeropuertoPrivadoSeleccionado != null) {
                pais = aeropuertoPrivadoSeleccionado.getPais();
                ciudad = aeropuertoPrivadoSeleccionado.getCiudad();
                calle = aeropuertoPrivadoSeleccionado.getCalle();
                numero = aeropuertoPrivadoSeleccionado.getNumero();
                nombre = aeropuertoPrivadoSeleccionado.getNombre();
                anioInauguracion = aeropuertoPrivadoSeleccionado.getAnioInauguracion();
                capacidad = aeropuertoPrivadoSeleccionado.getCapacidad();
                numSocios = aeropuertoPrivadoSeleccionado.getNumeroSocios();
                id = aeropuertoPrivadoSeleccionado.getId();
            } else{
                pais = aeropuertoPublicoSeleccionado.getPais();
                ciudad = aeropuertoPublicoSeleccionado.getCiudad();
                calle = aeropuertoPublicoSeleccionado.getCalle();
                numero = aeropuertoPublicoSeleccionado.getNumero();
                nombre = aeropuertoPublicoSeleccionado.getNombre();
                anioInauguracion = aeropuertoPublicoSeleccionado.getAnioInauguracion();
                capacidad = aeropuertoPublicoSeleccionado.getCapacidad();
                financiacion = aeropuertoPublicoSeleccionado.getFinanciacion();
                numTrabajadores = aeropuertoPublicoSeleccionado.getNumTrabajadores();
                id = aeropuertoPublicoSeleccionado.getId();
            }

            //Generar información dirección y aeropuertos
            String informacion = "Nombre: "+nombre+"\nPaís: "+pais+"\nDirección: C/"+calle+" "+numero+", "+ciudad+"\nAño de inauguración: "+anioInauguracion+"\nCapacidad: "+capacidad+"\nAviones:\n";

            //Generar información aviones
            ObservableList<Aviones> aviones = avionesDao.dameAvionesPorAeropuerto(id);
            if(aviones.size()==0) {
                informacion+="\tNO TIENE AVIONES\n";
            }else {
                for(Aviones avion:aviones) {
                    String estado = (avion.getActivado() == 1) ? "Activado" : "Desactivado";
                    informacion+="\tModelo: "+avion.getModelo()+"\n\tNúmero de asientos: "+avion.getNumeroAsientos()+"\n\tVelocidad Máxima: "+avion.getVelocidadMaxima()+"\n\t"+estado+"\n";
                }
            }

            //Generar información público o privado
            if(aeropuertoPrivadoSeleccionado==null) {
                informacion+="Público\nFinanciación: "+financiacion+"\nNúmero de trabajadores: "+numTrabajadores;
            }else {
                informacion+="Privado\nNúmero de Socios: "+numSocios;
            }

            Alert alerta = generarVentana(Alert.AlertType.INFORMATION, informacion, "INFO");
            alerta.show();
        }
    }

    /** GENERAR VENTANA DE ALERTA */
    private Alert generarVentana(Alert.AlertType tipoDeAlerta, String mensaje, String title) {
        Alert alerta = new Alert(tipoDeAlerta);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.setTitle(title);
        return alerta;
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

        aeropuertosPrivadosExistentes.setAll(aeropuertosPrivadosDao.cargarAeropuertosPrivados());
        aeropuertosPublicosExistentes.setAll(aeropuertosPublicosDao.cargarAeropuertosPublicos());

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
        tvAeropuertosPublicos.setItems(aeropuertosPublicosExistentes);

        // Añadir manejador de doble clic para aeropuertos privados
        tvAeropuertosPrivados.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                mostrarAeropuerto(new ActionEvent(tvAeropuertosPrivados, null));
            }
        });

        // Añadir manejador de doble clic para aeropuertos públicos
        tvAeropuertosPublicos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                mostrarAeropuerto(new ActionEvent(tvAeropuertosPublicos, null));
            }
        });
        // Llamar al método de filtrado
        configurarFiltradoAeropuertos();
    }

    private void configurarFiltradoAeropuertos() {
        // Filtrado para aeropuertos privados
        FilteredList<InformacionAeropuertosPrivados> filteredPrivados = new FilteredList<>(aeropuertosPrivadosExistentes, b -> true);
        txtFiltrar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPrivados.setPredicate(aeropuerto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                return aeropuerto.getNombre().toLowerCase().contains(lowerCaseFilter)
                        || aeropuerto.getCiudad().toLowerCase().contains(lowerCaseFilter)
                        || aeropuerto.getPais().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<InformacionAeropuertosPrivados> sortedPrivados = new SortedList<>(filteredPrivados);
        sortedPrivados.comparatorProperty().bind(tvAeropuertosPrivados.comparatorProperty());
        tvAeropuertosPrivados.setItems(sortedPrivados);

        // Filtrado para aeropuertos públicos
        FilteredList<InformacionAeropuertosPublicos> filteredPublicos = new FilteredList<>(aeropuertosPublicosExistentes, b -> true);
        txtFiltrar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPublicos.setPredicate(aeropuerto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                return aeropuerto.getNombre().toLowerCase().contains(lowerCaseFilter)
                        || aeropuerto.getCiudad().toLowerCase().contains(lowerCaseFilter)
                        || aeropuerto.getPais().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<InformacionAeropuertosPublicos> sortedPublicos = new SortedList<>(filteredPublicos);
        sortedPublicos.comparatorProperty().bind(tvAeropuertosPublicos.comparatorProperty());
        tvAeropuertosPublicos.setItems(sortedPublicos);
    }

}