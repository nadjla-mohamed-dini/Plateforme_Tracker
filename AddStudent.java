package com.example.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class AddStudent {
    public static HBox create() {
        Button addButton = new Button("Add a new student");
        addButton.setOnAction( e-> {
            System.out.println("clic ajouter");//enlever quand le formulaire sera ajouter
            //ajout du formulaire 
        });
        HBox container = new HBox(addButton);
        container.setSpacing(10);
        container.setStyle("-fs-alignment: center-left;");
        return container;
    }
}
