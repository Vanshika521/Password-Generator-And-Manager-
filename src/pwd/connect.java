/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pwd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class connect {
    public static Connection ConnetDB(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/pwd";
            String username = "root";
            String password = "computer";

            // Establish a connection
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            return con;
        }catch(ClassNotFoundException |SQLException e){
        JOptionPane.showMessageDialog(null, e);
        return null;
        }
    }
}

   

        
    
    

