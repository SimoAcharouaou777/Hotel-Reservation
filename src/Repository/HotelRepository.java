// src/Repository/HotelRepository.java
package Repository;

import Model.Hotel;
import Utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelRepository {
    public int insertHotelIfNotExists(Hotel hotel) {
        String checkSql = "SELECT id FROM hotel WHERE name = ?";
        String insertSql = "INSERT INTO hotel (name) VALUES (?) RETURNING id";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, hotel.getName());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, hotel.getName());
                    rs = insertStmt.executeQuery();
                    if (rs.next()) {
                        return rs.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}