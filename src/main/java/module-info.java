module com.example.customermanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.customermanager to javafx.fxml;
    exports com.example.customermanager;
}