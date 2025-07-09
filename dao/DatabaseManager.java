//pour gerer les connerctions à la base de donnees
package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/plateforme_tracker";
    private static final String USER = "postgres";
    private static final String PASSWORD = "";

    // Établit la connexion
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.err.println("❌ Error connexion : " + e.getMessage());
            return null;
        }
    }

    // Test de requête simple
    public static void testQuery() {
        String sql = "SELECT NOW() as current_time";
        Connection conn = getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                System.out.println("🕒 Current time from database: " + rs.getString("current_time"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error test query: " + e.getMessage());
        }
    }
}
