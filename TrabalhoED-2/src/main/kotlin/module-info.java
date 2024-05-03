module com.example.trabalhoed2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    opens com.example.trabalhoed2 to javafx.fxml;
    exports com.example.trabalhoed2;
}