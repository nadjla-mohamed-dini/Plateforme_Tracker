package com.example.service;


import java.util.List;

import com.example.dao.StudentDAO;
import com.example.model.Student;

public class StudentService {

    private final StudentDAO dao = new StudentDAO();

    public boolean addStudent(Student student) {
        return dao.addStudent(student);
    }

    public List<Student> getAllStudents() {
        return dao.getAllStudents();
    }

    public boolean updateStudent(Student student) {
        return dao.updateStudent(student);
    }

    public boolean deleteStudent(int id) {
        return dao.deleteStudentById(id);
    }
}
