/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cash_app;

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Assem
 */
public class CustomerTest {

    @Test
    public void testcustomer_sign_inTrue() throws SQLException {
        // arrange
        Customer obj = new Customer();
        
        // act
        boolean actual = obj.customer_sign_in("15226386", "468");
        
        // assert
        assertTrue(actual);
    }
    @Test
    public void testcustomer_sign_inFalse() throws SQLException {
        // arrange
        Customer obj = new Customer();
        
        // act
        boolean actual = obj.customer_sign_in("15226386", "000");
        
        // assert
        assertFalse(actual);
    }
     @Test (expected = SQLException.class)
    public void testcustomer_sign_inSQLException() throws SQLException {
        // arrange
        Customer obj = new Customer();
        
        // act
        obj.customer_sign_in("172", "1111");// ID doesn't exist
    } 
    
    
    @Test
    public void testview_balanceTrue() throws SQLException {
        // arrange
        Customer obj = new Customer();
        
        // act
        String actual = obj.view_balance("15226386");
        
        // assert
        assertEquals("2000", actual);
    }  
    @Test
    public void testview_balanceFalse() throws SQLException {
        // arrange
        Customer obj = new Customer();
        
        // act
        String actual = obj.view_balance("15226386");
        
        // assert
        assertNotEquals("3000", actual);
    }    
  
    
    @Test
    public void testcustomer_sign_upTrue() throws SQLException 
    {
        // arrange
        Customer obj = new Customer();
        
        // act
        obj.customer_sign_up("15268436", "548", "Seif", "3200", "14st, nozha");
    }     
    @Test (expected = NumberFormatException.class)
    public void testcustomer_sign_upNumberFormatException() throws SQLException {
        // arrange
        Customer obj = new Customer();
        
        // act
        obj.customer_sign_up("15268436", "548", "Seif", "3200aAAs", "14st, nozha");// balance contains characters
    } 
    @Test (expected = SQLException.class)
    public void testcustomer_sign_upSQLException() throws SQLException 
    {
        // arrange
        Customer obj = new Customer();
        
        // act
        obj.customer_sign_up("15226386", "548", "Seif", "3200", "14st, nozha");// id already exists
    }     
}
