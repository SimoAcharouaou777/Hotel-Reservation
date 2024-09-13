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
        String sql = "INSERT INTO reservations (user_id,room_id,start_date,end_date, price) VALUES(?,?,?,?,?)";
        String updateRoomStatus = "UPDATE rooms SET available = false WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement updateRoomStmt = conn.prepareStatement(updateRoomStatus)) {
            conn.setAutoCommit(false);
            stmt.setInt(1, reservation.getUserId());
            stmt.setInt(2, reservation.getRoomId());
            stmt.setObject(3, reservation.getStartDate());
            stmt.setObject(4, reservation.getEndDate());
            stmt.setDouble(5, reservation.getPrice());
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
                reservation.setPrice(rs.getDouble("price"));
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

    public Reservation getReservationById(int reservationId){
        String sql = "SELECT * FROM reservations WHERE id = ?";
        try(Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,reservationId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setUserId(rs.getInt("user_id"));
                reservation.setRoomId(rs.getInt("room_id"));
                reservation.setStartDate(rs.getDate("start_date"));
                reservation.setEndDate(rs.getDate("end_date"));
                reservation.setPrice(rs.getDouble("price"));
                return reservation;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void updateReservation(Reservation reservation, int oldRoomId) {
        String updateReservationSql = "UPDATE reservations SET room_id = ?, start_date = ?, end_date = ? WHERE id = ?";
        String updateOldRoomStatus = "UPDATE rooms SET available = true WHERE id = ?";
        String updateNewRoomStatus = "UPDATE rooms SET available = false WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement updateReservationStmt = conn.prepareStatement(updateReservationSql);
             PreparedStatement updateOldRoomStmt = conn.prepareStatement(updateOldRoomStatus);
             PreparedStatement updateNewRoomStmt = conn.prepareStatement(updateNewRoomStatus)) {
            conn.setAutoCommit(false);

            updateReservationStmt.setInt(1, reservation.getRoomId());
            updateReservationStmt.setDate(2, reservation.getStartDate());
            updateReservationStmt.setDate(3, reservation.getEndDate());
            updateReservationStmt.setInt(4, reservation.getId());
            updateReservationStmt.executeUpdate();

            updateOldRoomStmt.setInt(1, oldRoomId);
            updateOldRoomStmt.executeUpdate();

            updateNewRoomStmt.setInt(1, reservation.getRoomId());
            updateNewRoomStmt.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelReservation(int reservationId, int roomId) {
        String sql = "DELETE FROM reservations WHERE id = ?";
        String updateRoomStatus = "UPDATE rooms SET available = true WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement updateRoomStmt = conn.prepareStatement(updateRoomStatus)) {
            conn.setAutoCommit(false);

            stmt.setInt(1, reservationId);
            stmt.executeUpdate();
            updateRoomStmt.setInt(1, roomId);
            updateRoomStmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getRoomTypeById(int roomId) {
        String roomType = "";
        String query = "SELECT room_type FROM rooms WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                roomType = rs.getString("room_type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomType;
    }

    public double getRoomPriceById(int roomId) {
        double roomPrice = 0.0;
        String query = "SELECT price FROM rooms WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                roomPrice = rs.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomPrice;
    }

    public List<Reservation> getReservationsByUserId(int userId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE user_id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
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
}
