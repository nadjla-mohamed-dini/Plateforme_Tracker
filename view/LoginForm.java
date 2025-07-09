package com.example.view;

import com.example.dao.AdminManager;

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

        Label loginLabel = new Label("Login Form");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            AdminManager.User user = AdminManager.login(username, password);
            if (user != null) {
                System.out.println("✅ Connexion : " + user.getUsername());

                root.getChildren().clear();
                root.getChildren().add(ToolBar.create());
                root.getChildren().add(StudentTable.create());
                
            } else {
                System.out.println("❌ Nom d'utilisateur ou mot de passe incorrect !");
            }
        });

        box.getChildren().addAll(loginLabel, usernameField, passwordField, loginButton);

        return box;
    }
}
