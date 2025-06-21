package com.example.teamap_ecopay.Controllers.Forms;

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

public class NavigationControllerForm implements Initializable {

    @FXML
    private Pane contentPane;

    @FXML Button homeBtn,investmentBtn;

    // Keep a reference to the dashboard controller so we can pass it to other controllers
    private DashboardControllerForm dashboardController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDashboardView();
    }

    private void loadDashboardView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/teamap_ecopay/fxml/dashboard-view.fxml"));
            Parent dashboardView = loader.load();

            dashboardController = loader.getController();

            contentPane.getChildren().setAll(dashboardView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SwitchAction(ActionEvent e) throws IOException {
        Button sourceBtn = (Button) e.getSource();

        if (sourceBtn == homeBtn) {
            loadDashboardView();
        } else if (sourceBtn == investmentBtn) {
            loadInvestmentView();
        }
    }

    private void loadInvestmentView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/teamap_ecopay/fxml/investment-view.fxml"));
            Parent investmentView = loader.load();

            InvestmentControllerForm investmentController = loader.getController();
            if (dashboardController != null) {
                investmentController.setDashboardController(dashboardController);
            }

            contentPane.getChildren().setAll(investmentView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}