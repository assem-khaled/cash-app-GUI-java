/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cash_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author Assem
 */
public class Donation {
    
    ResultSet show_charities() throws SQLException
    {
        String url= "jdbc:derby://localhost:1527/cash_db";
        String username= "team";
        String password="1234";
        
        Connection con = DriverManager.getConnection(url,username,password);
        PreparedStatement pstmt= con.prepareStatement("SELECT * FROM donations");
        ResultSet rs= pstmt.executeQuery();
        
        return rs;
    }
    
    
}
