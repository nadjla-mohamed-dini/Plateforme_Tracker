package com.example.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class ToolBar {
    public static HBox create() {
        Button addButton = new Button("Add a new student ➕");
        addButton.setOnAction(e -> {
            AddStudentForm.show();
            
        });

        Button deleteButton = new Button("Delete 🚮");
        deleteButton.setOnAction(e -> {
            
        });

        Button modifyButton = new Button("Modify ✏️");
        modifyButton.setOnAction(e -> {
            ModifyStudent.showModifyWindow();
        });
        Button showAllButton = new Button("Show All");
        showAllButton.setOnAction(e -> {
            System.out.println("");
        });

        HBox container = new HBox(15);
        container.getChildren().addAll(addButton, deleteButton, modifyButton, showAllButton);
        container.setStyle("-fx-alignment: center;");

        return container;

    }
}
