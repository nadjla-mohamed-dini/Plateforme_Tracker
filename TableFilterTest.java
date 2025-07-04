package com.exemple.utils;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.model.Student;
import com.example.utils.TableFilter;

public class TableFilterTest {

    @Test
    public void testMatch_FilterMatchesFirstName() {
        Student s = new Student(1, "Alex", "Durand", 20, 15.0);
        assertTrue(TableFilter.matches(s, "alex"));
    }

    @Test
    public void testMatch_FilterMatchesLastName() {
        Student s = new Student(1, "Marie", "Dupont", 21, 16.0);
        assertTrue(TableFilter.matches(s, "dup"));
    }

    @Test
    public void testMatch_NoMatch() {
        Student s = new Student(1, "Luc", "Martin", 19, 14.0);
        assertFalse(TableFilter.matches(s, "zzz"));
    }

    @Test
    public void testMatch_EmptyFilter() {
        Student s = new Student(1, "Luc", "Martin", 19, 14.0);
        assertTrue(TableFilter.matches(s, ""));
    }
}
