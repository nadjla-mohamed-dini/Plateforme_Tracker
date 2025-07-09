package com.example.view;

import com.example.dao.StudentDAO;
import com.example.model.Student;
import com.example.model.StudentFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModifyStudent {

    private static final StudentDAO studentDAO = new StudentDAO();

    public static void showModifyWindow(StudentFX student, Runnable onSuccess) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modify Student");

        TextField firstNameField = new TextField(student.getFirstName());
        TextField lastNameField = new TextField(student.getLastName());
        TextField ageField = new TextField(String.valueOf(student.getAge()));
        TextField gradeField = new TextField(String.valueOf(student.getGrade()));
        Label message = new Label();

        Button modifyButton = new Button("Modify");
        modifyButton.setOnAction(e -> {
            try {
                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                double grade = Double.parseDouble(gradeField.getText().trim());

                if (!isValidName(firstName) || !isValidName(lastName)) {
                    message.setText("❌ First name and last name must contain only letters.");
                    return;
                }

                // Conversion en Student (objet métier)
                Student updatedStudent = new Student(student.getId(), firstName, lastName, age, grade);
                boolean success = studentDAO.updateStudent(updatedStudent);

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("✅ Student updated successfully!");
                    alert.showAndWait();

                    onSuccess.run(); // recharge table
                    window.close();
                } else {
                    message.setText("❌ Failed to update student.");
                }
            } catch (NumberFormatException ex) {
                message.setText("❌ Age must be an integer and grade a decimal.");
            }
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
            new Label("Modify student"),
            firstNameField, lastNameField, ageField, gradeField,
            modifyButton, message
        );

        Scene scene = new Scene(layout, 350, 400);
        window.setScene(scene);
        window.showAndWait();
    }

    private static boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-ZÀ-ÿ\\s]+");
    }
}
