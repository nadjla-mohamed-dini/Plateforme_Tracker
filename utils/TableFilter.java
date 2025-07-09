package com.example.utils;

import com.example.model.StudentFX;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TableFilter {

    public static boolean matches(StudentFX student, String filter) {
        if (filter == null || filter.isEmpty()) return true;
        String lower = filter.toLowerCase();
        return student.getFirstName().toLowerCase().contains(lower)
            || student.getLastName().toLowerCase().contains(lower)
            || String.valueOf(student.getId()).contains(lower)
            || String.valueOf(student.getAge()).contains(lower)
            || String.valueOf(student.getGrade()).contains(lower);
    }

    public static TextField createSearchField(TableView<StudentFX> table, ObservableList<StudentFX> masterList) {
        TextField searchField = new TextField();
        searchField.setPromptText("Search a student...");

        FilteredList<StudentFX> filteredData = new FilteredList<>(masterList, p -> true);

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredData.setPredicate(student -> matches(student, newVal));
        });

        table.setItems(filteredData);
        return searchField;
    }
}

