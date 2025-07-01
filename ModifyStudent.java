package com.example.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ModifyStudent {
    public static HBox create() {
        Button modifyButton = new Button("Modify");
        modifyButton.setOnAction(e-> {
            System.out.println("clic modifier"); // enlever des que c'est bon
            //ajouter une fenetre pour pouvoir modifier 
            //ajouter un message box 
        });
        HBox container = new HBox(modifyButton);
        container.setSpacing(10);
        container.setStyle("-fx-alignment: center-left");
        return container;
    }    
}
