package Repository;

import Enums.RoomType;
import Model.Room;
import Utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomRepository {
    public void insertRoom(Room room){
        String sql = "INSERT INTO rooms (room_type, price) VALUES (?,?)";
        try(Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1,room.getType().name());
                stmt.setDouble(2,room.getPrice());
                stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean alreadyInserted(){
       String sql = "SELECT COUNT(*) FROM rooms";
       try(Connection conn = DBConnection.getInstance().getConnection();
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery()){
           if(rs.next()){
               return rs.getInt(1)> 0;
           }
       }catch(SQLException e){
           e.printStackTrace();
       }
       return false;
    }
}
