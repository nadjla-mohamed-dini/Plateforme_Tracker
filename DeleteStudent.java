package com.example.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class DeleteStudent {
    public static HBox create() {
        Button deleteButton = new Button ("Delete🚮");
        deleteButton.setOnAction( e-> {
            System.out.println("clic delete");

        });
        HBox container = new HBox(deleteButton);
        container.setSpacing(10);
        container.setStyle("-fx-alignment: center-right;");
        return container;
    }
}
