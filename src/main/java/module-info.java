module eu.andreatt.ejerciciol_dein {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens eu.andreatt.ejerciciol_dein.controllers to javafx.fxml;
    opens eu.andreatt.ejerciciol_dein.application to javafx.graphics;
    opens eu.andreatt.ejerciciol_dein.model to javafx.base;

    exports eu.andreatt.ejerciciol_dein.application;
}