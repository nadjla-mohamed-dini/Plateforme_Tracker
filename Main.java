package com.example;

import com.example.view.LoginForm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        VBox root = new VBox(20);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");

        Label welcomeLabel = new Label("Bienvenue dans le gestionnaire des étudiants");
        Button startButton = new Button("Démarrer");
        
        startButton.setOnAction(e -> {
            root.getChildren().clear();
            root.getChildren().add(LoginForm.create(root));
            
        });

        root.getChildren().addAll(welcomeLabel, startButton);

        Scene scene = new Scene(root, 600, 300);
        primaryStage.setTitle("Fenêtre d'accueil");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

    }

    public static void main(String[] args) {
        launch(args);
    }
}
