package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.Student;

public class StudentService {

    // Afficher tous les étudiants
    public void getAllStudents() {
        String sql = "SELECT id, first_name, last_name, age, grade FROM student";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("📋 Students list:");
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getDouble("grade")
                );
                System.out.println(student);
            }

        } catch (SQLException e) {
            System.err.println("❌ Students list error: " + e.getMessage());
        }
    }

    // Ajouter un étudiant
    public void addStudent(Student student) {
        String sql = "INSERT INTO student (first_name, last_name, age, grade) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setInt(3, student.getAge());
            stmt.setDouble(4, student.getGrade());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Student added successfully!");
            } else {
                System.out.println("Failed to add student.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    // Supprimer un étudiant par ID
    public void deleteStudentById(int id) {
        String sql = "DELETE FROM student WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("No student found with ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
    }

    // Trouver un étudiant par ID
    public void findStudentById(int id) {
        String sql = "SELECT id, first_name, last_name, age, grade FROM student WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getInt("age"),
                            rs.getDouble("grade")
                    );
                    System.out.println("Student found:");
                    System.out.println(student);
                } else {
                    System.out.println("No student found with ID: " + id);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error finding student: " + e.getMessage());
        }
    }
}
