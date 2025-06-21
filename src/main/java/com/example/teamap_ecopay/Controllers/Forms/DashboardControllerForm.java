package com.example.teamap_ecopay.Controllers.Forms;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class DashboardControllerForm {
    @FXML
    private Button addBalanceBtn,backBtn;

    @FXML
    private TextField amountField;

    @FXML
    private AnchorPane addMoneyPane,dashboardPane;

    @FXML
    private Label currentBalanceLabel,incomeLabel,spentLabel;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    public void initialize() {
        handleTransition(addBalanceBtn, "Add Balance", 120, 40);
        setupWeeklyCarbonChart();
    }

    public void increaseAction(){
        try {
            double current = Double.parseDouble(amountField.getText());
            current += 10;


            amountField.setText(String.format("%.2f", current));
        } catch(NumberFormatException e){
            amountField.setText("0.00");
        }
    }

    public void decreaseAction() {
        try {
            double current = Double.parseDouble(amountField.getText());
            if (current > 0) {
                current -= 10;
                if (current < 0) current = 0;
                amountField.setText(String.format("%.2f", current));
            }
        } catch (NumberFormatException e) {
            amountField.setText("0.00");
        }
    }

    public void addToBalance(){
        try {
            String currentText = currentBalanceLabel.getText().replace("RM", "").trim();
            String incomeText = incomeLabel.getText().replace("RM", "").trim();

            double currentBalance = Double.parseDouble(currentText);
            double currentIncome = Double.parseDouble(incomeText);

            double amount = Double.parseDouble(amountField.getText());
            double newBalance = currentBalance + amount;
            double newIncome = currentIncome + amount;

            currentBalanceLabel.setText(String.format("RM%.2f", newBalance));
            incomeLabel.setText((String.format("RM%.2f", newIncome)));


            amountField.setText("0.00");
            backAction();
        } catch (NumberFormatException e) {
            amountField.setText("0.00");
        }
    }

    public void deductInvestment(double amount) {
        try {
            double balance = Double.parseDouble(currentBalanceLabel.getText().replace("RM", "").trim());
            double spent = Double.parseDouble(spentLabel.getText().replace("RM", "").trim());
            System.out.println(spent);

            if (balance >= amount) {
                balance -= amount;
                spent += amount;

                currentBalanceLabel.setText(String.format("RM%.2f", balance));
                spentLabel.setText(String.format("RM%.2f", spent));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("The investment has been made");
                alert.setContentText("You have invested RM" + String.format("%.2f", amount));
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Insufficient Balance");
                alert.setContentText("You do not have enough balance to invest RM" + String.format("%.2f", amount));
                alert.showAndWait();

            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void handleSwitchAction(){
        dashboardPane.setVisible(false);
        addMoneyPane.setVisible(true);
    }

    public void backAction(){
        dashboardPane.setVisible(true);
        addMoneyPane.setVisible(false);
        amountField.setText("0.00");
    }


    public void handleTransition(Button button, String text, double expandedWidth, double originalWidth) {
        button.setOnMouseEntered(event -> {
            button.setText(text);
            Timeline expand = new Timeline(
                    new KeyFrame(Duration.millis(150),
                            new KeyValue(button.prefWidthProperty(), expandedWidth)
                    )
            );
            expand.play();
        });

        button.setOnMouseExited(event -> {
            Timeline shrink = new Timeline(
                    new KeyFrame(Duration.millis(300),
                            new KeyValue(button.prefWidthProperty(), originalWidth)
                    )
            );
            shrink.setOnFinished(e -> button.setText(""));
            shrink.play();
        });
    }

    private void setupWeeklyCarbonChart() {
        XYChart.Series<String, Number> carbonSeries = new XYChart.Series<>();
        carbonSeries.setName("Weekly Carbon Emissions (kg CO₂)");

        // Sample data - replace with real data later
        carbonSeries.getData().add(new XYChart.Data<>("Mon", 1.5));
        carbonSeries.getData().add(new XYChart.Data<>("Tue", 2.0));
        carbonSeries.getData().add(new XYChart.Data<>("Wed", 3.0));
        carbonSeries.getData().add(new XYChart.Data<>("Thu", 3.1));
        carbonSeries.getData().add(new XYChart.Data<>("Fri", 4.2));
        carbonSeries.getData().add(new XYChart.Data<>("Sat", 5.0));
        carbonSeries.getData().add(new XYChart.Data<>("Sun", 7.5));

        lineChart.getData().clear();
        lineChart.getData().add(carbonSeries);
    }


}
