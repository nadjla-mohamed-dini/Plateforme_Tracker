package com.example.view;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class AddStudentFormTest {

    @Test
    void testValidNames() {
        assertTrue(AddStudentForm.isValidName("Jean"));
        assertTrue(AddStudentForm.isValidName("Élodie"));
        assertTrue(AddStudentForm.isValidName("Jean Dupont"));
        assertTrue(AddStudentForm.isValidName("Éléonore Lévesque"));
        assertTrue(AddStudentForm.isValidName("María José")); 
    }

    @Test
    void testInvalidNames() {
        assertFalse(AddStudentForm.isValidName("")); 
        assertFalse(AddStudentForm.isValidName(null)); 
        assertFalse(AddStudentForm.isValidName("John123")); 
        assertFalse(AddStudentForm.isValidName("Marie_Dupont"));
        assertFalse(AddStudentForm.isValidName("Luc@")); 
    }
}
