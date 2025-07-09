package com.example.view;

import com.example.model.StudentFX;
import com.example.service.StudentService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddStudentForm {

    // Le service sera injecté avant d'afficher le formulaire
    private static StudentService studentService;

    public static void setStudentService(StudentService service) {
        studentService = service;
    }

    public static void show() {
        VBox form = new VBox(10);
        form.setPadding(new Insets(20));
        form.setStyle("-fx-background-color: #f9f9f9;");
        form.setAlignment(Pos.CENTER);

        TextField firstnameField = new TextField();
        firstnameField.setPromptText("First Name");

        TextField lastnameField = new TextField();
        lastnameField.setPromptText("Last Name");

        TextField ageField = new TextField();
        ageField.setPromptText("Age");

        TextField gradeField = new TextField();
        gradeField.setPromptText("Grade");

        Label message = new Label();

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            try {
                String firstName = firstnameField.getText();
                String lastName = lastnameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double grade = Double.parseDouble(gradeField.getText());

                if (!isValidName(lastName) || !isValidName(firstName)) {
                    message.setText("First name and last name must contain only letters");
                    return;
                }

                if (age < 0 || grade < 0 || grade > 20) {
                    message.setText("❌ Age or grade is out of valid range.");
                    return;
                }

                StudentFX student = new StudentFX(0, firstName, lastName, age, grade);

                // Utilisation du service injecté
                boolean success = studentService.addStudent(student);

                if (success) {
                    StudentTable.loadStudentsFromDB(); 
                    message.setText("✅ Student added to the Table!");
                    firstnameField.clear();
                    lastnameField.clear();
                    ageField.clear();
                    gradeField.clear();
                } else {
                    message.setText("❌ Failed to add student to the table.");
                }
            } catch (NumberFormatException ex) {
                message.setText("❌ Please enter valid numeric values for age and grade");
            }
        });

        form.getChildren().addAll(
            new Label("Add a student"),
            firstnameField, lastnameField, ageField, gradeField,
            saveButton, message
        );

        Stage popup = new Stage();
        popup.setTitle("New Student");
        popup.setScene(new Scene(form, 350, 400));
        popup.show();
    }

    public static boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-ZÀ-ÿ\\s]+");
    }
}
