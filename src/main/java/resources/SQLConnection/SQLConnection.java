package main.java.resources.SQLConnection;

import java.sql.*;

public class SQLConnection {
    public static void Connection(String Query){
        /*
        QUERY EXAMPLE
        var sqlSelect = "SELECT * FROM Lesson";
        */
        var ConnectionURL = "jdbc:mysql://localhost/managementSystem";
        try (var conn = DriverManager.getConnection(ConnectionURL, "root", "");
            var ps = conn.prepareStatement(Query);
            var rs = ps.executeQuery();){
            
            // RETURN DATA FROM TABLE
            while(rs.next()){
                var id = rs.getString("id");
                var nombreUsuario = rs.getString("nombreUsuario");
                var banned = rs.getString("banned");
                System.out.println("ID: "+id+" Username: "+nombreUsuario+" Banned: "+banned);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
