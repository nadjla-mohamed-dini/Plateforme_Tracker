package com.example.utils;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.model.StudentFX;


public class TableFilterTest {

    @Test
    public void testMatch_FilterMatchesFirstName() {
        StudentFX s = new StudentFX(1, "Alex", "Durand", 20, 15.0);
        assertTrue(TableFilter.matches(s, "alex"));
    }

    @Test
    public void testMatch_FilterMatchesLastName() {
        StudentFX s = new StudentFX(1, "Marie", "Dupont", 21, 16.0);
        assertTrue(TableFilter.matches(s, "dup"));
    }

    @Test
    public void testMatch_NoMatch() {
        StudentFX s = new StudentFX(1, "Luc", "Martin", 19, 14.0);
        assertFalse(TableFilter.matches(s, "zzz"));
    }

    @Test
    public void testMatch_EmptyFilter() {
        StudentFX s = new StudentFX(1, "Luc", "Martin", 19, 14.0);
        assertTrue(TableFilter.matches(s, ""));
    }
}
