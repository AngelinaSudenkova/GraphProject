package com.graph.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GraphController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}