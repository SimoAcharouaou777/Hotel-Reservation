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
        String sql = "INSERT INTO reservations (user_id,room_id,start_date,end_date) VALUES(?,?,?,?)";
        String updateRoomStatus = "UPDATE rooms SET available = false WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement updateRoomStmt = conn.prepareStatement(updateRoomStatus)) {
            conn.setAutoCommit(false);
            stmt.setInt(1, reservation.getUserId());
            stmt.setInt(2, reservation.getRoomId());
            stmt.setObject(3, reservation.getStartDate());
            stmt.setObject(4, reservation.getEndDate());
            stmt.executeUpdate();
            updateRoomStmt.setInt(1, reservation.getRoomId());
            updateRoomStmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isRoomAvailable(int roomId){
        String sql = "SELECT available FROM rooms WHERE id = ?";
        try(Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,roomId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getBoolean("available");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setUserId(rs.getInt("user_id"));
                reservation.setRoomId(rs.getInt("room_id"));
                reservation.setStartDate(rs.getDate("start_date"));
                reservation.setEndDate(rs.getDate("end_date"));
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
    public int getUserIdByCin(String cin) {
        String sql = "SELECT id FROM users WHERE cin = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
