package Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5433/Hotel"; // Update with your database URL
    private static final String USER = "Hotel"; // Database username
    private static final String PASSWORD = ""; // Database password
    private static Connection conn = null;
    public static Connection connect() {

        try {
            // Load PostgreSQL JDBC Driver (optional, usually not required for modern JDBC)
            Class.forName("org.postgresql.Driver");

            // Establish connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to PostgreSQL successfully!");
            return conn;

        } catch (SQLException e) {
            System.out.println("Connection failure!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found!");
            e.printStackTrace();
        }
        return conn;
    }
}
