package library;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MakeConnection {
    static Connection con=null;
    public static Connection connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MakeConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
