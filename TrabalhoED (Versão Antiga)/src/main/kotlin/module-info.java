module com.ed.trabalhoedeusabo {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens com.ed.trabalhoedeusabo to javafx.fxml;
    exports com.ed.trabalhoedeusabo;
}