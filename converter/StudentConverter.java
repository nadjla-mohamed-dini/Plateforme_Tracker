package com.example.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.example.model.Student;
import com.example.model.StudentFX;

public class StudentConverter {

    public static StudentFX toFX(Student s) {
        return new StudentFX(s.getId(), s.getFirstName(), s.getLastName(), s.getAge(), s.getGrade());
    }

    public static Student toModel(StudentFX fx) {
        return new Student(fx.getId(), fx.getFirstName(), fx.getLastName(), fx.getAge(), fx.getGrade());
    }

    // Convertir une liste de Student en liste de StudentFX
    public static List<StudentFX> toFXList(List<Student> students) {
        return students.stream()
                       .map(StudentConverter::toFX)
                       .collect(Collectors.toList());
    }

    // Convertir une liste de StudentFX en liste de Student
    public static List<Student> toModelList(List<StudentFX> studentsFX) {
        return studentsFX.stream()
                         .map(StudentConverter::toModel)
                         .collect(Collectors.toList());
    }
}
