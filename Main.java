package com.example;

import java.util.Scanner;

import com.example.dao.DatabaseManager;
import com.example.dao.StudentService;
import com.example.model.Student;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.testQuery();

        StudentService service = new StudentService();
        Scanner scanner = new Scanner(System.in);
        

        //Ajouter étudiant
        System.out.println("Add a new student:");
        System.out.println("First name:");
        String firstName = scanner.nextLine();

        System.out.println("Last name:");
        String lastName = scanner.nextLine();

        System.out.println("Age:");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("Grade:");
        double grade = Double.parseDouble(scanner.nextLine());

        Student newStudent = new Student(0, firstName, lastName, age, grade);
        service.addStudent(newStudent); // Ajout

        service.deleteStudentById(3); // Suppression
        service.findStudentById(2); // Recherche

    }
}