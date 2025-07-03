package com.example.utils;

import com.example.model.Student;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TableFilter {
    public static TextField createSearchField(TableView<Student> table, ObservableList<Student> masterList) {
        TextField searchField = new TextField();
        searchField.setPromptText("Search a student...");

        FilteredList<Student> filteredData = new FilteredList<>(masterList, p -> true);

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            String filter = newVal.toLowerCase();
            filteredData.setPredicate(student ->
                student.getFirstname().toLowerCase().contains(filter) ||
                student.getLastname().toLowerCase().contains(filter) ||
                String.valueOf(student.getId()).contains(filter) ||
                String.valueOf(student.getAge()).contains(filter) ||
                String.valueOf(student.getGrade()).contains(filter)
            );
        });

        table.setItems(filteredData);
        return searchField;
    }
}
