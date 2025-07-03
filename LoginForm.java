package com.example.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
                root.getChildren().add(ToolBar.create());
                root.getChildren().add(StudentTable.create());
                
            }
        });

        box.getChildren().addAll(loginLabel, usernameField, passwordField, loginButton);

        return box;
    }
}
