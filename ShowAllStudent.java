package com.example.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ShowAllStudent {
    public static HBox create() {
        Button showAllButton = new Button("Show All");
        showAllButton.setOnAction(e -> {
            System.out.println("");
        });
        HBox container = new HBox(showAllButton);
        container.setSpacing(10);
        container.setStyle("-fs-alignment: center-left;");
        return container;
    }
}
