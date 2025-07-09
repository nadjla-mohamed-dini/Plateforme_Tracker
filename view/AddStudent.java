package com.example.view;

import com.example.service.StudentService;

import javafx.scene.control.Button;

public class AddStudent {
    public static Button create(StudentService studentService, Runnable refreshTable) {
        Button addButton = new Button("Add a new student ➕");
        addButton.setOnAction(e -> {
            AddStudentForm.setStudentService(studentService);
            AddStudentForm.show();
            refreshTable.run();
        });
        return addButton;
    }
}
