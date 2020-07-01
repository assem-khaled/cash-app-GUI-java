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
public class Transaction {

    ResultSet emp_show_trans_log() throws SQLException
    {
        String url= "jdbc:derby://localhost:1527/cash_db";
        String username= "team";
        String password="1234";
        
        Connection con = DriverManager.getConnection(url,username,password);
        PreparedStatement pstmt= con.prepareStatement("SELECT * FROM transaction_log");
        ResultSet rs= pstmt.executeQuery();
        
        return rs;
    }      
    
    String cust_send_money(String signed_id, String id, String amount) throws SQLException
    {   
        if (signed_id.matches(id))
        {
            return "Can't send money to yourself !!";
        }
        
        String path = "jdbc:derby://localhost:1527/cash_db";
        String user = "team";
        String pass = "1234";        
        
        Connection con = DriverManager.getConnection(path, user, pass);
        // if ID exists
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT balance FROM customer WHERE cid = "+ id);
        rs.next();
        int temp_balance = rs.getInt("balance");
        
        // check if balance contains the amount
        ResultSet rs2 = st.executeQuery("SELECT balance FROM customer WHERE cid = "+ signed_id);
        rs2.next();
        
        if (Integer.valueOf(amount) > rs2.getInt("balance"))
        {
            return "Insufficient Balance";
        }
        // sending money:       
        PreparedStatement pst = con.prepareStatement("UPDATE customer SET balance = ? WHERE cid = "+ signed_id);
        pst.setInt(1, rs2.getInt("balance") - Integer.valueOf(amount));
        pst.execute();
        
        PreparedStatement pst2 = con.prepareStatement("UPDATE customer SET balance = ? WHERE cid = "+ id);
        pst2.setInt(1, temp_balance + Integer.valueOf(amount));        
        pst2.execute();
        // saving transaction in Database
        
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
        
        PreparedStatement pst3 = con.prepareStatement("INSERT INTO transaction_log(name, trans_cid, amount, date) VALUES(?, ? ,? , ?)");
        pst3.setString(1, "Agent Cash Out");
        pst3.setInt(2, Integer.valueOf(signed_id));
        pst3.setInt(3, Integer.valueOf(amount));
        pst3.setTimestamp(4, ourJavaTimestampObject);

        pst3.execute();
        
        return "Transaction Completed Successfully";
    }    
    
    String cust_donate_money(String signed_id, String org_id, String amount) throws SQLException
    {   
        String path = "jdbc:derby://localhost:1527/cash_db";
        String user = "team";
        String pass = "1234";        
        
        Connection con = DriverManager.getConnection(path, user, pass);
        // if ID exists
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT money_collected FROM donations WHERE charity_id = "+ org_id);
        rs.next();
        int temp_balance = rs.getInt("money_collected");
        
        // check if balance contains the amount
        ResultSet rs2 = st.executeQuery("SELECT balance FROM customer WHERE cid = "+ signed_id);
        rs2.next();
        
        if (Integer.valueOf(amount) > rs2.getInt("balance"))
        {
            return "Insufficient Balance";
        }
        // sending money:       
        PreparedStatement pst = con.prepareStatement("UPDATE customer SET balance = ? WHERE cid = "+ signed_id);
        pst.setInt(1, rs2.getInt("balance") - Integer.valueOf(amount));
        pst.execute();
        
        PreparedStatement pst2 = con.prepareStatement("UPDATE donations SET money_collected = ? WHERE charity_id = "+ org_id);
        pst2.setInt(1, temp_balance + Integer.valueOf(amount));        
        pst2.execute();
        // saving transaction in Database
        
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
        
        PreparedStatement pst3 = con.prepareStatement("INSERT INTO transaction_log(name, trans_cid, amount, date) VALUES(?, ? ,? , ?)");
        pst3.setString(1, "Donation");
        pst3.setInt(2, Integer.valueOf(signed_id));
        pst3.setInt(3, Integer.valueOf(amount));
        pst3.setTimestamp(4, ourJavaTimestampObject);

        pst3.execute();
        
        PreparedStatement pst4 = con.prepareStatement("INSERT INTO donate VALUES(?, ?)");
        
        pst4.setInt(1, Integer.valueOf(signed_id));
        pst4.setInt(2, Integer.valueOf(org_id));
        pst4.execute();  
        
        return "Transaction Completed Successfully";
    }    
}
