package com.example.service;

import com.example.model.Student;
import com.example.model.StudentFX;
import com.example.view.ToolBar;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdvancedSearchForm {

    public static void show() {
        Stage stage = new Stage();
        stage.setTitle("Recherche avancée");

        Label minGradeLabel = new Label("Note minimale :");
        TextField minGradeField = new TextField();

        Label maxAgeLabel = new Label("Âge maximal :");
        TextField maxAgeField = new TextField();

        Button searchButton = new Button("Rechercher");

        searchButton.setOnAction(e -> {
            try {
                double minGrade = Double.parseDouble(minGradeField.getText());
                int maxAge = Integer.parseInt(maxAgeField.getText());

                var students = ToolBar.getStudentService().searchStudents(minGrade, maxAge);

                var fxList = FXCollections.<StudentFX>observableArrayList();
                for (Student s : students) {
                    fxList.add(new StudentFX(
                            s.getId(),
                            s.getFirstName(),
                            s.getLastName(),
                            s.getAge(),
                            s.getGrade()
                    ));
                }

                StudentTable.getStudentList().setAll(fxList);

                stage.close();

            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Valeurs invalides").showAndWait();
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(minGradeLabel, 0, 0);
        grid.add(minGradeField, 1, 0);
        grid.add(maxAgeLabel, 0, 1);
        grid.add(maxAgeField, 1, 1);
        grid.add(searchButton, 0, 2, 2, 1);

        Scene scene = new Scene(grid, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}
