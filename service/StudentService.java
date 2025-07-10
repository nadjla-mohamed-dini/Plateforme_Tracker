package com.example.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.converter.StudentConverter;
import com.example.dao.StudentDAO;
import com.example.model.Student;
import com.example.model.StudentFX;

public class StudentService {

    private final StudentDAO dao;

    // Constructeur qui reçoit une DAO (réelle ou mockée)
    public StudentService(StudentDAO dao) {
        this.dao = dao;
    }

    public boolean addStudent(StudentFX studentFX) {
        Student student = StudentConverter.toModel(studentFX);
        return dao.addStudent(student);
    }

    public List<StudentFX> getAllStudents() {
        List<Student> students = dao.getAllStudents();
        return students.stream()
                       .map(StudentConverter::toFX)
                       .collect(Collectors.toList());
    }

    public boolean updateStudent(StudentFX studentFX) {
        Student student = StudentConverter.toModel(studentFX);
        return dao.updateStudent(student);
    }

    public boolean deleteStudent(int id) {
        return dao.deleteStudentById(id);
    }

    public Map<String, Integer> getAgeRangeStats() {
    return dao.getStudentCountByAgeRange();
    }
}
