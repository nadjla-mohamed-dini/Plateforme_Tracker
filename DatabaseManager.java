//pour gerer les connerctions a la base de donnees
package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/plateforme tracker";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Alfr3d";

    private static Connection connection;

    // Établit la connexion
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Connexion à la base de données réussie !");
            } catch (SQLException e) {
                System.err.println("❌ Erreur de connexion : " + e.getMessage());
            }
        }
        return connection;
    }

    // Ferme la connexion
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("🔒 Connexion fermée.");
            } catch (SQLException e) {
                System.err.println("❌ Erreur lors de la fermeture : " + e.getMessage());
            }
        }
    }

    // Test de requête simple
    public static void testQuery() {
        String sql = "SELECT NOW() as current_time";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                System.out.println("🕒 Heure actuelle depuis la base : " + rs.getString("current_time"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la requête de test : " + e.getMessage());
        }
    }
}
