package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connections {
    private static String user = "root";
    private static String password = "";
    private static String url = "jdbc:mysql://localhost:3306/tubespbo?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static Connection c;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                c = DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                Logger.getLogger(Connections.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connections.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (c != null) {


            System.out.println("Database connected");
        } else {

            System.out.println("Database not connected");
        }
        return c;
    }

    public void CloseConnection(){
        try{
            c.close();
        }catch(Exception e){
            Logger.getLogger(Connections.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
