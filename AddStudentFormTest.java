package com.exemple.view;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.view.AddStudentForm;

public class AddStudentFormTest {

    @Test
    void testValidNames() {
        assertTrue(AddStudentForm.isValideName("Jean"));
        assertTrue(AddStudentForm.isValideName("Élodie"));
        assertTrue(AddStudentForm.isValideName("Jean Dupont"));
        assertTrue(AddStudentForm.isValideName("Éléonore Lévesque"));
        assertTrue(AddStudentForm.isValideName("María José")); 
    }

    @Test
    void testInvalidNames() {
        assertFalse(AddStudentForm.isValideName("")); 
        assertFalse(AddStudentForm.isValideName(null)); 
        assertFalse(AddStudentForm.isValideName("John123")); 
        assertFalse(AddStudentForm.isValideName("Marie_Dupont"));
        assertFalse(AddStudentForm.isValideName("Luc@")); 
    }
}
