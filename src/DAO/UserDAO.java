package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Utils.DBConnection;

public class UserDAO {
    public void insertUser(String name) {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";


        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("User inserted successfully!");
            } else{
                System.out.println("User not inserted!");
            }


        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
