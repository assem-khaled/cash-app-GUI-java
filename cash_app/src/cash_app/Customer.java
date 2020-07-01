/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cash_app;

import java.sql.*;
import java.util.Calendar;


/**
 *
 * @author Assem
 */
public class Customer {
    
    boolean customer_sign_in(String id, String passw) throws SQLException
    {
        String path = "jdbc:derby://localhost:1527/cash_db";
        String user = "team";
        String pass = "1234";
        
        Connection con = DriverManager.getConnection(path, user, pass);
        Statement st = con.createStatement();
        
        ResultSet sss = st.executeQuery("SELECT * FROM customer WHERE cid = "+ id);
        sss.next();
        
        if ( passw.equals(sss.getString("password")))
            return true;
        else
            return false;

    }
    
    void customer_sign_up(String id, String passw, String name, String balance, String address) throws SQLException
    {
        String path = "jdbc:derby://localhost:1527/cash_db";
        String user = "team";
        String pass = "1234";        
        
        Connection con = DriverManager.getConnection(path, user, pass);
        PreparedStatement st = con.prepareStatement("INSERT INTO customer VALUES(?, ? ,? ,? ,?)");
        
        st.setInt(1, Integer.valueOf(id));
        st.setString(2, passw);
        st.setString(3, name);
        st.setInt(4, Integer.valueOf(balance));
        st.setString(5, address);
        st.execute();
        
    }
 
    
    String view_balance(String id) throws SQLException
    {
        String path = "jdbc:derby://localhost:1527/cash_db";
        String user = "team";
        String pass = "1234";  
        
        Connection con = DriverManager.getConnection(path, user, pass);
        Statement st = con.createStatement();
        
        ResultSet sss = st.executeQuery("SELECT balance FROM customer WHERE cid = "+ id);
        sss.next();
        
        return sss.getString("balance");
    }   
    
}
