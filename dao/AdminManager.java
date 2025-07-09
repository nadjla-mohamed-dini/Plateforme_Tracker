package com.example.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminManager {

    public static class User {
        private int id;
        private String username;
        private String role;

        public User(int id, String username, String role) {
            this.id = id;
            this.username = username;
            this.role = role;
        }

        public int getId() { return id; }
        public String getUsername() { return username; }
        public String getRole() { return role; }
    }

    // ✅ Hachage SHA-256 UTF-8
    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // ✅ Connexion utilisateur
    public static User login(String username, String password) {
        String sql = "SELECT id, username, password_hash, role FROM users WHERE username = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    String inputHash = hashPassword(password);

                    // Affiche les hashes pour déboguer
                    System.out.println("👉 Input password hash: " + inputHash);
                    System.out.println("🗃️ Stored hash:         " + storedHash);

                    if (storedHash.equals(inputHash)) {
                        return new User(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("role")
                        );
                    } else {
                        System.out.println("❌ Mot de passe incorrect !");
                    }
                } else {
                    System.out.println("❌ Utilisateur introuvable !");
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error during login: " + e.getMessage());
        }
        return null;
    }
}