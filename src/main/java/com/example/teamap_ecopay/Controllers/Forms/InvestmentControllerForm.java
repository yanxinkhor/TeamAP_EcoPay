package com.example.teamap_ecopay.Controllers.Forms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InvestmentControllerForm {
    @FXML
    private Button investBtn1,investBtn2,investBtn3;
    private DashboardControllerForm dashboardController;

    private final double INVEST1 = 3000.00;
    private final double INVEST2 = 1000.00;
    private final double INVEST3 = 4300.00;

    public void setDashboardController(DashboardControllerForm dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void investAction(ActionEvent e) {
        if (dashboardController == null) {
            System.out.println("Dashboard controller is not set!");
            return;
        }

        Button sourceBtn = (Button) e.getSource();
        double amount = 0;

        if (sourceBtn == investBtn1) {
            amount = INVEST1;
        } else if (sourceBtn == investBtn2) {
            amount = INVEST2;
        } else if (sourceBtn == investBtn3) {
            amount = INVEST3;
        }

        dashboardController.deductInvestment(amount);
    }
}
