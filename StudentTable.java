package com.example.view;

import com.example.model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class StudentTable {
    public static VBox create() {
        TableView<Student> table =  new TableView<>();

        TableColumn<Student, Number> idCol =  new TableColumn<>("ID");
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<Student, String> firstNameCol = new TableColumn<>("Frist Name");
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().fristNameProperty());

        TableColumn<Student, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        TableColumn<Student, Number> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty());

        TableColumn<Student, Number> gradeCol = new TableColumn<>("Grade");
        gradeCol.setCellValueFactory(cellData -> cellData.getValue().gradeProperty());

        table.getColumns().addAll(idCol, firstNameCol, lastNameCol, ageCol, gradeCol);

        table.setItems(getSampleData());

        VBox box = new VBox (10, new Label (""), 
        AddStudent.create(),
        ModifyStudent.create(),
        table);
        box.setStyle("-fx-padding: 5;");
        return box;
    }
    private static ObservableList<Student> getSampleData() {
        return FXCollections.observableArrayList();
    }
}
