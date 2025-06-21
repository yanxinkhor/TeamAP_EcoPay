module com.example.teamap_ecopay {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.teamap_ecopay to javafx.fxml;
    opens com.example.teamap_ecopay.Controllers.Forms to javafx.fxml;

    exports com.example.teamap_ecopay;
    exports com.example.teamap_ecopay.Controllers.Forms;
}