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

/**
 *
 * @author Assem
 */
public class Currency {
    ResultSet show_currency() throws SQLException
    {
        String url= "jdbc:derby://localhost:1527/cash_db";
        String username= "team";
        String password="1234";
        
        Connection con = DriverManager.getConnection(url,username,password);
        PreparedStatement pstmt= con.prepareStatement("SELECT * FROM currency");
        ResultSet rs= pstmt.executeQuery();
        
        return rs;
    }
    
    void emp_update_currency(String code, String emp_id, String b_price, String s_price) throws SQLException
    {
        String path = "jdbc:derby://localhost:1527/cash_db";
        String user = "team";
        String pass = "1234";        
        
        Connection con = DriverManager.getConnection(path, user, pass);
        
        Statement st = con.createStatement();
        ResultSet sss = st.executeQuery("SELECT * FROM currency WHERE code = "+ code);
        sss.next();
        sss.getString(1);
        
        PreparedStatement pst = con.prepareStatement("UPDATE currency SET buying_price = ?, selling_price = ? WHERE code = "+ code);
        pst.setFloat(1, Float.valueOf(b_price));
        pst.setFloat(2, Float.valueOf(s_price));
        
        pst.execute();

        PreparedStatement pst2 = con.prepareStatement("INSERT INTO modify_currency VALUES(?, ?)");
        
        pst2.setInt(1, Integer.valueOf(code));
        pst2.setInt(2, Integer.valueOf(emp_id));
        pst2.execute();         
    }    
}
