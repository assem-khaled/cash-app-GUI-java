package cash_app;
import java.sql.*;
/**
 *
 * @author Assem
 */
public class Employee {
    boolean emp_sign_in(String id, String passw) throws SQLException
    {
        String path = "jdbc:derby://localhost:1527/cash_db";
        String user = "team";
        String pass = "1234";
        
        Connection con = DriverManager.getConnection(path, user, pass);
        Statement st = con.createStatement();
        
        ResultSet sss = st.executeQuery("SELECT * FROM employee WHERE eid = "+ id);
        sss.next();
        
        if ( passw.equals(sss.getString("password")))
            return true;
        else
            return false;
    }
    
    String[] view_customer_data(String id) throws SQLException
    {
        String path = "jdbc:derby://localhost:1527/cash_db";
        String user = "team";
        String pass = "1234";  
        
        Connection con = DriverManager.getConnection(path, user, pass);
        Statement st = con.createStatement();
        
        ResultSet sss = st.executeQuery("SELECT * FROM customer WHERE cid = "+ id);
        
        sss.next();
        String temp [] = new String[5];
        
        temp[0] = sss.getString("cid");
        temp[1] = sss.getString("password");
        temp[2] = sss.getString("name");
        temp[3] = sss.getString("balance");
        temp[4] = sss.getString("address");

        return temp;
    }
    
    void delete_customer(String id, String emp_id) throws SQLException
    {
        String path = "jdbc:derby://localhost:1527/cash_db";
        String user = "team";
        String pass = "1234";        
        
        Connection con = DriverManager.getConnection(path, user, pass);
        
        Statement st = con.createStatement();
        ResultSet sss = st.executeQuery("SELECT * FROM customer WHERE cid = "+ id);
        sss.next();
        sss.getString(1);
        
        PreparedStatement pst = con.prepareStatement("DELETE FROM customer WHERE CID = "+ id);
        pst.execute();
        
        PreparedStatement pst2 = con.prepareStatement("INSERT INTO modify_customer VALUES(?, ?)");
        
        pst2.setInt(1, Integer.valueOf(emp_id));
        pst2.setInt(2, Integer.valueOf(id));
        pst2.execute();        
    }
    
      
}
