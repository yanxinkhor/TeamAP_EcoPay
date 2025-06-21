package com.example.teamap_ecopay.Controllers.Services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NavigationController {
    public void handleSwitchAction(String sourceBtn, Pane contentPane) throws IOException {
        switch (sourceBtn) {
            case "HomePage":
                switchPage("home-view.fxml", contentPane);
                break;
            case "TreePage":
                switchPage("tree-view.fxml", contentPane);
                break;
        }
    }

    public void switchPage(String fileName, Pane contentPane) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/teamap_ecopay/fxml/" + fileName));
        Parent View = loader.load();
        contentPane.getChildren().clear();
        contentPane.getChildren().add(View);
    }
}