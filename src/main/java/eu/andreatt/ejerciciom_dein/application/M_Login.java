package eu.andreatt.ejerciciom_dein.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class M_Login extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(M_Login.class.getResource("/eu/andreatt/ejerciciom_dein/fxml/M_Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/ejerciciom_dein/css/M.css").toExternalForm());
        stage.setTitle("AVIONES - LOGIN");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}