package main.java.resources.SQLConnection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private static final String CONNECTION_URL = "jdbc:sqlite:users.db";
    // Crear tabla si no existe
        static {
            String createTable = """
                CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY,
                nombreUsuario TEXT NOT NULL,
                grupo TEXT NOT NULL,
                banned INTEGER DEFAULT 0
                );
                """;
        try (var conn = DriverManager.getConnection(CONNECTION_URL);
             var stmt = conn.createStatement()) {
            stmt.execute(createTable);
            System.out.println("Connected to SQLite and table ready.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertUser(int id,String nombre, String grupo, boolean baneado) {
        String sql = "INSERT INTO users (id,nombreUsuario, grupo, banned) VALUES (?, ?, ?, ?)";
        try (var conn = DriverManager.getConnection("jdbc:sqlite:users.db");
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, nombre);
            pstmt.setString(3, grupo);
            pstmt.setInt(4, baneado ? 1 : 0);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE nombreUsuario = ?";
        try (var conn = DriverManager.getConnection("jdbc:sqlite:users.db");
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void banUser(String username) {
        String sql = "UPDATE users SET banned = ? WHERE nombreUsuario = ?";
        try (var conn = DriverManager.getConnection("jdbc:sqlite:users.db");
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, 1); // 1 = baneado
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
