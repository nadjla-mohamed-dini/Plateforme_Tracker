package com.example.view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class LoginForm {
    public static VBox create(VBox root) {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);

        Label loginLabel = new Label("Formulaire de connexion");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Nom d'utilisateur");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe");

        Button loginButton = new Button("Se connecter");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            if (!username.isEmpty()) {
                root.getChildren().clear();
                root.getChildren().add(MainMenu.create(username));
            }
        });

        box.getChildren().addAll(loginLabel, usernameField, passwordField, loginButton);

        return box;
    }
}
