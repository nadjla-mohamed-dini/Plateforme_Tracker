package com.example.view;

import java.util.List;

import com.example.controller.ImportExportController;
import com.example.dao.StudentDAO;
import com.example.model.StudentFX;
import com.example.service.StudentService;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ToolBar {

   
    private static StudentService studentService = new StudentService(new StudentDAO());

    // Ajout : déclaration de la variable ImportExportController (non statique)
    private static ImportExportController importExportController;

    // Setter pour injecter un autre StudentService (mock ou autre)
    public static void setStudentService(StudentService service) {
        studentService = service;
    }

    // Setter pour injecter l'ImportExportController
    public static void setImportExportController(ImportExportController controller) {
        importExportController = controller;
    }

    // Getter pour récupérer le StudentService
    public static StudentService getStudentService() {
        return studentService;
    }

    public static HBox create() {
        Button addButton = AddStudent.create(studentService, ToolBar::refreshTable);
        Button deleteButton = DeleteStudent.create(studentService, ToolBar::refreshTable);


        Button modifyButton = new Button("Modify ✏️");
        modifyButton.setOnAction(e -> {
            StudentFX selected = StudentTable.getSelectedStudent();
            if (selected != null) {
                ModifyStudent.showModifyWindow(selected, ToolBar::refreshTable);
            } else {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("No Selection");
                warning.setHeaderText(null);
                warning.setContentText("Please select a student to modify.");
                warning.showAndWait();
            }
        });

        Button showAllButton = new Button("Show All");
        showAllButton.setOnAction(e -> refreshTable());

        Button importCSVButton = new Button ("Import CSV 📥");
        importCSVButton.setOnAction(e -> {
            if (importExportController != null) {
                importExportController.handleImportCSV();
                StudentTable.loadStudentsFromDB();
                refreshTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "ImportExportController not initialized").showAndWait();
            }
        });

        Button exportCSVButton = new Button("Export CSV 📤");
        exportCSVButton.setOnAction(e -> {
            if (importExportController != null) {
                importExportController.handleExportCSV();
            } else {
                new Alert(Alert.AlertType.ERROR, "ImportExportController not initialized").showAndWait();
            }
        });

        HBox container = new HBox(15);
        container.getChildren().addAll(addButton, deleteButton, modifyButton, showAllButton, importCSVButton, exportCSVButton);
        container.setStyle("-fx-alignment: center;");

        return container;
    }

    private static void refreshTable() {
        List<StudentFX> studentsFromDB = studentService.getAllStudents();
        StudentTable.getStudentList().setAll(studentsFromDB);
    }
}
