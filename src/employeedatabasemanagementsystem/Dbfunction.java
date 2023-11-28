package employeedatabasemanagementsystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Dbfunction {
    public Connection connect_to_db(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection failed!!!");
            }
        } catch (SQLException e) {
            // Handle or log the exception properly
            e.printStackTrace();
        }
        return conn;
    }



    public static void main(String[] args) {
        Dbfunction db = new Dbfunction();
        Connection conn = db.connect_to_db("EmployeeDatabase", "postgres", "Alvisrohit@7250");

     
    }
}
