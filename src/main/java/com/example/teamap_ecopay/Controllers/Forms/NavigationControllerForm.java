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

    @FXML
    private Button homeBtn, investmentBtn, footprintBtn;

    private DashboardControllerForm dashboardController;
    private Parent dashboardView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDashboardView();
    }

    public void SwitchAction(ActionEvent e) {
        Button sourceBtn = (Button) e.getSource();

        if (sourceBtn == homeBtn) {
            showDashboard();
        } else if (sourceBtn == investmentBtn) {
            loadInvestmentView();
        } else if (sourceBtn == footprintBtn) {
            loadCarbonFootprint();
        }
    }

    private void loadDashboardView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/teamap_ecopay/fxml/dashboard-view.fxml"));
            dashboardView = loader.load(); // Load once
            dashboardController = loader.getController();
            contentPane.getChildren().setAll(dashboardView); // Initial display
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDashboard() {
        if (dashboardView != null) {
            contentPane.getChildren().setAll(dashboardView);
        }
    }

    private void loadInvestmentView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/teamap_ecopay/fxml/investment-view.fxml"));
            Parent investmentView = loader.load();
            InvestmentControllerForm investmentController = loader.getController();

            investmentController.setDashboardController(dashboardController);

            contentPane.getChildren().setAll(investmentView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCarbonFootprint() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/teamap_ecopay/fxml/carbonfootprint.fxml"));
            Parent footprintView = loader.load();
            contentPane.getChildren().setAll(footprintView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
