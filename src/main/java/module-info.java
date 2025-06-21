module com.example.teamap_ecopay {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.teamap_ecopay to javafx.fxml;
    exports com.example.teamap_ecopay;
}