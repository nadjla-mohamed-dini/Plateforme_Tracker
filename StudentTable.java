package com.example.view;

import com.example.model.Student;
import com.example.utils.TableFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class StudentTable {

    private static final ObservableList<Student> studentList = FXCollections.observableArrayList(
        new Student(1, "Alice", "Dupont", 20, 15.5),
        new Student(2, "Bob", "Martin", 22, 13.7),
        new Student(3, "Claire", "Durand", 19, 17.2)
    );

    public static VBox create() {
        TableView<Student> table = new TableView<>(studentList);

        TableColumn<Student, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<Student, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        TableColumn<Student, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        TableColumn<Student, Number> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty());

        TableColumn<Student, Number> gradeCol = new TableColumn<>("Grade");
        gradeCol.setCellValueFactory(cellData -> cellData.getValue().gradeProperty());

        table.getColumns().addAll(idCol, firstNameCol, lastNameCol, ageCol, gradeCol);

        TextField searchField = TableFilter.createSearchField(table, studentList);

        VBox box = new VBox(10, new Label("Liste des étudiants :"), searchField, table );
        box.setStyle("-fx-padding: 20;");
        return box;
    }


     public static boolean isIdUsed(int id) {
        return studentList.stream().anyMatch(s -> s.getId() == id);
    }

    public static boolean addStudent(Student s) {
        if (isIdUsed(s.getId())) return false;
        studentList.add(s);
        return true;
    }
}
