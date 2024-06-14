module com.ed.cinemamanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.desktop;
    requires java.sql;
    requires kotlinx.serialization.core;
    requires kotlinx.serialization.json;


    opens com.ed.cinemamanagementsystem to javafx.fxml;
    exports com.ed.cinemamanagementsystem;
}