package pwd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class connect {
    public static Connection ConnetDB(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/pwd";
            String username = "root";
            String password = "computer";
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            return con;
        }catch(ClassNotFoundException |SQLException e){
        JOptionPane.showMessageDialog(null, e);
        return null;
        }
    }
}
