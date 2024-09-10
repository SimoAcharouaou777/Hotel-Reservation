package Repository;

import Model.Reservation;
import Utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {
    public void insertReservation(Reservation reservation) {
        String sql = "INSERT INTO reservation (userId,roomId,StartDate,EndDate) VALUES(?,?,?,?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getUserId());
            stmt.setInt(2, reservation.getRoomId());
            stmt.setObject(3, reservation.getStartDate());
            stmt.setObject(4, reservation.getEndDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setUserId(rs.getInt("userId"));
                reservation.setRoomId(rs.getInt("roomId"));
                reservation.setStartDate(rs.getDate("StartDate"));
                reservation.setEndDate(rs.getDate("EndDate"));
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
}
