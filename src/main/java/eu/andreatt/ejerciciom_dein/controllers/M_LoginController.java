package eu.andreatt.ejerciciom_dein.controllers;

import eu.andreatt.ejerciciom_dein.application.M_ListadoAeropuertos;
import eu.andreatt.ejerciciom_dein.dao.UsuariosDAO;
import eu.andreatt.ejerciciom_dein.model.Usuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class M_LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassw;

    @FXML
    private TextField txtUser;


    @FXML
    void initialize() {
        // Establecer el botón de login como el botón por defecto
        btnLogin.setDefaultButton(true);
    }

    @FXML
    void login(ActionEvent event) {
        // Obtener los valores ingresados
        String usuario = txtUser.getText().trim();
        String password = txtPassw.getText().trim();

        // Validaciones
        if (usuario.isEmpty()) {
            mostrarAlerta("Introduce Usuario", Alert.AlertType.WARNING);
        } else if (password.isEmpty()) {
            mostrarAlerta("Introduce Password", Alert.AlertType.WARNING);
        } else {
            // Crear objeto Usuarios y verificar en la base de datos
            Usuarios usuarioObj = new Usuarios(usuario, password);
            UsuariosDAO usuarioDAO = new UsuariosDAO();

            if (usuarioDAO.verificarUsuario(usuarioObj)) {
                System.out.println("LOGIN CORRECTO");

                // Cerrar ventana modal
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.close();

                new M_ListadoAeropuertos();
            } else {
                mostrarAlerta("El usuario o la contraseña son incorrectos", Alert.AlertType.ERROR);
            }
        }
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}