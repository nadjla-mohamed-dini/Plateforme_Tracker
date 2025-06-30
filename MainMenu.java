package com.example.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainMenu {
    public static VBox create(String username) {
        VBox box = new VBox(15);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-padding: 20;");

        Label title = new Label("Menu Principal");
        Label welcome = new Label("Bienvenue, " + username + "!");

        box.getChildren().addAll(title, welcome);
        return box;
    }
}
