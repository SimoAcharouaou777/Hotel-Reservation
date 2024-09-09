package Repository;

import Model.User;
import Utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public void insertUser(User user){
        String sql = "INSERT INTO users (name, email , password) VALUES (?,?,?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public User getUserByEmailAndPassword(String email, String password){
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        User user = null;

        try(Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
