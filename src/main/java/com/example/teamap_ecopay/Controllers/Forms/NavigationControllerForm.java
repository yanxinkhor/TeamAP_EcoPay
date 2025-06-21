package com.example.teamap_ecopay.Controllers.Forms;

import com.example.teamap_ecopay.Controllers.Services.NavigationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class NavigationControllerForm {

    private Pane contentPane;

    @FXML
    private final NavigationController navigationService = new NavigationController();
    public void SwitchAction(ActionEvent e) throws IOException {
        String sourceBtn = ((Button) e.getSource()).getText();
        navigationService.handleSwitchAction(sourceBtn, contentPane);
    }

}
