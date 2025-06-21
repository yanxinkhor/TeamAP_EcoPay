package com.example.teamap_ecopay.Controllers.Forms;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CarbonTrackingControllerForm {
    // Transport fields
    @FXML
    private ComboBox<String> TransportType;
    @FXML
    private TextField EnterDistance;
    @FXML
    private TextField EnterTime;
    @FXML
    private Label transportResult;

    // Utility fields
    @FXML
    private ComboBox<String> UtilityType;
    @FXML
    private TextField EnterServingSize;
    @FXML
    private Label utilitiesResult;

    // Food fields
    @FXML
    private ComboBox<String> FoodType;
    @FXML
    private TextField EnterFoodPrice;
    @FXML
    private Label foodUtilities;

    private final DoubleProperty todayTotalKg = new SimpleDoubleProperty(0);

    @FXML
    private ImageView treeImage;

    private final Image healthy = new Image(
            getClass().getResource("/com/example/teamap_ecopay/image/healthytree.png").toExternalForm()
    );
    private final Image midhealthy = new Image(
            getClass().getResource("/com/example/teamap_ecopay/image/2healthytree.png").toExternalForm()
    );
    private final Image okay = new Image(
            getClass().getResource("/com/example/teamap_ecopay/image/okaytree.png").toExternalForm()
    );
    private final Image wilting = new Image(
            getClass().getResource("/com/example/teamap_ecopay/image/wiltingtree.png").toExternalForm()
    );
    private final Image wilted = new Image(
            getClass().getResource("/com/example/teamap_ecopay/image/wiltedtree.png").toExternalForm()
    );


    // Initialize combo boxes
    @FXML
    public void initialize() {
        TransportType.getItems().addAll("Car", "Bus", "Train", "Motorcycle");
        UtilityType.getItems().addAll("Electricity", "Water");
        FoodType.getItems().addAll("Meats", "Vegetable", "Fruits", "Dairy");

        EnterDistance.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty()) {
                transportResult.setText("");
            }
        });
        EnterServingSize.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty()) {
                transportResult.setText("");
            }
        });

        EnterFoodPrice.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty()) {
                transportResult.setText("");
            }
        });

        EnterTime.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty()) {
                transportResult.setText("");
            }
        });

        todayTotalKg.addListener((obs, oldV, newV) -> updateTree(newV.doubleValue()));

    }

    private void updateTree(double totalKg) {
        if      (totalKg <  50) treeImage.setImage(healthy);
        else if (totalKg < 100) treeImage.setImage(midhealthy);
        else if (totalKg < 150) treeImage.setImage(okay);
        else if (totalKg < 200) treeImage.setImage(wilting);
        else                    treeImage.setImage(wilted);

    }


    // Calculate Transport Emissions
    @FXML
    private void addTransportEmissions() {
        String type = TransportType.getValue();
        if (type == null || EnterDistance.getText().isEmpty()) {
            transportResult.setText("Please enter transport details.");
            return;
        }

        double distance = Double.parseDouble(EnterDistance.getText());
        double emissionFactor = 0;

        switch (type) {
            case "Car":
                emissionFactor = 0.21;
                break;
            case "Bus":
                emissionFactor = 0.11;
                break;
            case "Train":
                emissionFactor = 0.06;
                break;
            case "Flight":
                emissionFactor = 0.18;
                break;
        }

        double emissions = distance * emissionFactor;
        transportResult.setText(String.format("%.2f kg CO₂", emissions));
        todayTotalKg.set(todayTotalKg.get() + emissions);
    }

    // Calculate Utility Emissions
    @FXML
    private void addUtilityEmissions() {
        String utility = UtilityType.getValue();
        if (utility == null || EnterServingSize.getText().isEmpty()) {
            utilitiesResult.setText("Please enter utility details.");
            return;
        }

        double kWh = Double.parseDouble(EnterServingSize.getText());
        double emissionFactor = 0;

        switch (utility) {
            case "Electricity":
                emissionFactor = 0.42;
                break;
            case "Water":
                emissionFactor = 0.001;
                break;
        }

        double emissions = kWh * emissionFactor;
        utilitiesResult.setText(String.format("%.2f kg CO₂", emissions));

        todayTotalKg.set(todayTotalKg.get() + emissions);
    }

    // Calculate Food Emissions based on spending price (RM)
    @FXML
    private void addFoodEmissions() {
        String food = FoodType.getValue();
        String foodprice = EnterFoodPrice.getText();

        if (food == null || EnterFoodPrice.getText().isEmpty()) {
            foodUtilities.setText("Please enter food details.");
            return;
        }

        double price;
        try{
            price = Double.parseDouble(EnterFoodPrice.getText());
        } catch (NumberFormatException e){
            foodUtilities.setText("Please enter food details.");
            return;
        }

        double emissionFactor = 0.0;

        switch (food) {
            case "Meats":
                emissionFactor = 0.8;
                break;
            case "Vegetable":
                emissionFactor = 0.3;
                break;
            case "Fruits":
                emissionFactor = 0.2;
                break;
            case "Dairy":
                emissionFactor = 0.5;
                break;
        }

        double emissions = price * emissionFactor;
        foodUtilities.setText(String.format("%.2f kg CO₂", emissions));

        todayTotalKg.set(todayTotalKg.get() + emissions);
    }
}
