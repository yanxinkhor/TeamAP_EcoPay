package com.example.teamap_ecopay.Controllers.Forms;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
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
    private Label currentBalanceLabel;


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
            double currentBalance = Double.parseDouble(currentText);
            double amount = Double.parseDouble(amountField.getText());
            double newBalance = currentBalance + amount;

            currentBalanceLabel.setText(String.format("RM%.2f", newBalance));


            amountField.setText("0.00");
            backAction();
        } catch (NumberFormatException e) {
            amountField.setText("0.00");
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


    @FXML
    public void initialize() {
        handleTransition(addBalanceBtn, "Add Balance", 120, 40); // <- width in pixels
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
}
