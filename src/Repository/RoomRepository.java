package Repository;

import Enums.RoomType;
import Model.Room;
import Utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomRepository {
    public void insertRoom(Room room){
        String sql = "INSERT INTO rooms (type, price) VALUES (?,?)";
        try(Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1,room.getType().name());
                stmt.setDouble(2,room.getPrice());
                stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

//    public void insertAuto(){
//       insertRoom(new Room(RoomType.SINGLE, 50.00));
//         insertRoom(new Room(RoomType.DOUBLE, 75.00));
//            insertRoom(new Room(RoomType.TRIPLE, 100.00));
//            insertRoom(new Room(RoomType.SINGLE, 75.00));
//    }
}
