module com.ed.cinemamanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.desktop;


    opens com.ed.cinemamanagementsystem to javafx.fxml;
    exports com.ed.cinemamanagementsystem;
}