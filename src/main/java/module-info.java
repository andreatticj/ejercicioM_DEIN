module eu.andreatt.ejerciciom_dein {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens eu.andreatt.ejerciciom_dein.controllers to javafx.fxml;
    opens eu.andreatt.ejerciciom_dein.application to javafx.graphics;
    opens eu.andreatt.ejerciciom_dein.model to javafx.base;

    exports eu.andreatt.ejerciciom_dein.application;
}