package com.example;

import com.example.controller.ImportExportController;
import com.example.dao.StudentDAO;
import com.example.service.StudentIOService;
import com.example.service.StudentIOServiceImpl;
import com.example.service.StudentService;
import com.example.view.LoginForm;
import com.example.view.ToolBar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        StudentService studentService = new StudentService(new StudentDAO());

        StudentIOService ioService = new StudentIOServiceImpl();

        ImportExportController importExportController = new ImportExportController(studentService, ioService, primaryStage);
        ToolBar.setStudentService(studentService);
        ToolBar.setImportExportController(importExportController);

        VBox root = new VBox(20);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");

        Label welcomeLabel = new Label("Welcome to our student manager");
        Button startButton = new Button("Start");

        startButton.setOnAction(e -> {
            root.getChildren().clear();
            root.getChildren().add(LoginForm.create(root));
        });

        root.getChildren().addAll(welcomeLabel, startButton);

        Scene scene = new Scene(root, 600, 300);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setTitle("Plateforme Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
