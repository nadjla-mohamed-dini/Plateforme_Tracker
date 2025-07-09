package com.example.view;

import com.example.model.StudentFX;
import com.example.service.StudentService;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class DeleteStudent {

    public static Button create(StudentService studentService, Runnable refreshTable) {
        Button deleteButton = new Button("Delete 🚮");
        deleteButton.setOnAction(e -> {
            StudentFX selected = StudentTable.getSelectedStudent();
            if (selected == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a student to delete.").showAndWait();
                return;
            }

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Deletion");
            confirm.setHeaderText(null);
            confirm.setContentText("Are you sure you want to delete student: "
                    + selected.getFirstName() + " " + selected.getLastName() + "?");

            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    boolean success = studentService.deleteStudent(selected.getId());

                    Alert feedback = new Alert(success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                    feedback.setTitle(success ? "Deleted" : "Error");
                    feedback.setHeaderText(null);
                    feedback.setContentText(success
                        ? "Student deleted successfully."
                        : "Failed to delete student.");
                    feedback.showAndWait();

                    if (success) {
                        refreshTable.run();
                    }
                }
            });
        });
        return deleteButton;
    }
}
