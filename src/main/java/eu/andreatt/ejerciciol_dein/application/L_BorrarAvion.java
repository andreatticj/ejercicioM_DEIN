package eu.andreatt.ejerciciol_dein.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class L_BorrarAvion extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(L_BorrarAvion.class.getResource("/eu/andreatt/ejerciciol_dein/fxml/L_BorrarAvion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("AVIONES - BORRAR AVION");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}