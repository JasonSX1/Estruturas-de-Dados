module com.example.trabalhoedgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens com.example.trabalhoedgui to javafx.fxml;
    exports com.example.trabalhoedgui;
}