package com.example.controller;

import java.io.File;
import java.util.List;

import com.example.converter.StudentConverter;
import com.example.model.Student;
import com.example.model.StudentFX;
import com.example.service.StudentIOService; 
import com.example.service.StudentService;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImportExportController {

    private final StudentService studentService;
    private final StudentIOService ioService;
    private final Stage primaryStage;

    // Constructeur
    public ImportExportController(StudentService studentService, StudentIOService ioService, Stage primaryStage) {
        this.studentService = studentService;
        this.ioService = ioService;
        this.primaryStage = primaryStage;
    }

    public void handleExportCSV() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exporter en CSV");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                List<StudentFX> studentsFX = studentService.getAllStudents();
                List<Student> students = StudentConverter.toModelList(studentsFX);
                ioService.exportToCsv(students, file.getAbsolutePath());
                showAlert("Succes", "Export CSV successfully !");
            }
        } catch (Exception e) {
            showError("CSV export error", e.getMessage());
        }
    }

    public void handleImportCSV() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Import a CSV file");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showOpenDialog(primaryStage);

            if (file != null) {
                List<Student> students = ioService.importFromCsv(file.getAbsolutePath());
                List<StudentFX> studentsFX = StudentConverter.toFXList(students);
                for (StudentFX sfx : studentsFX) {
                    studentService.addStudent(sfx);
                }
                showAlert("Succes", "Successful CSV import!");
            }
        } catch (Exception e) {
            showError("CSV export error", e.getMessage());
        }
    }

    private void showAlert(String titre, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String titre, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titre);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
