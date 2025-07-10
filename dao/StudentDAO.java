package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.model.Student;

public class StudentDAO {

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT id, first_name, last_name, age, grade FROM student";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getInt("age"),
                    rs.getDouble("grade")
                ));
            }

        } catch (SQLException e) {
            System.err.println("❌ Students list error: " + e.getMessage());
        }
        return list;
    }
    //pagination
    public List<Student> getStudents(int pageNumber, int pageSize) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT id, first_name, last_name, age, grade FROM student ORDER BY id LIMIT ? OFFSET ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pageSize);
            stmt.setInt(2, (pageNumber - 1) * pageSize);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Student(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getInt("age"),
                            rs.getDouble("grade")
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching students: " + e.getMessage());
        }
        return list;
    }

    public boolean addStudent(Student student) {
        String sql = "INSERT INTO student (first_name, last_name, age, grade) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setInt(3, student.getAge());
            stmt.setDouble(4, student.getGrade());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                System.err.println("Adding student failed, no rows affected.");
                return false;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setId(generatedKeys.getInt(1));
                } else {
                    System.err.println("Adding student failed, no ID obtained.");
                    return false;
                }
            }

            return true;

        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteStudentById(int id) {
        String sql = "DELETE FROM student WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    public Student findStudentById(int id) {
        String sql = "SELECT id, first_name, last_name, age, grade FROM student WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getDouble("grade")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error finding student: " + e.getMessage());
        }
        return null;
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE student SET first_name = ?, last_name = ?, age = ?, grade = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setInt(3, student.getAge());
            stmt.setDouble(4, student.getGrade());
            stmt.setInt(5, student.getId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error updating student: " + e.getMessage());
            return false;
        }
    }

    public Map<String, Integer> getStudentCountByAgeRange() {
        Map<String, Integer> ageRanges = new LinkedHashMap<>();

        String sql = "SELECT " +
                "CASE " +
                " WHEN age < 18 THEN '< 18' " +
                " WHEN age BETWEEN 18 AND 25 THEN '18-25' " +
                " WHEN age BETWEEN 26 AND 35 THEN '26-35' " +
                " WHEN age BETWEEN 36 AND 42 THEN '36-42' " +
                " ELSE '> 42' " +
                "END AS age_range, COUNT(*) AS total " +
                "FROM student " +
                "GROUP BY age_range " +
                "ORDER BY age_range";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String range = rs.getString("age_range");
                int count = rs.getInt("total");
                ageRanges.put(range, count);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des données : " + e.getMessage());
        }

        return ageRanges;
    }
}