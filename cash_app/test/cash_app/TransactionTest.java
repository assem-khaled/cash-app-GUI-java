/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cash_app;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Assem
 */
public class TransactionTest {

    @Test
    public void testemp_show_trans_logTrue() throws SQLException {
        // arrange
        Transaction obj = new Transaction();
        
        // act
        ResultSet actual = obj.emp_show_trans_log();
        
        // assert
        assertNotNull(actual);
    }

    
    @Test
    public void testcust_send_moneyTrue() throws SQLException {
        // arrange
        Transaction obj = new Transaction();
        
        // act
        String actual = obj.cust_send_money("15223814", "15223975", "50");
        
        // assert
        assertEquals("Transaction Completed Successfully", actual);
    }
    @Test
    public void testcust_send_moneyNoBalance() throws SQLException {
        // arrange
        Transaction obj = new Transaction();
        
        // act
        String actual = obj.cust_send_money("15223814", "15223975", "50000000");
        
        // assert
        assertEquals("Insufficient Balance", actual);
    }  
    @Test
    public void testcust_send_moneySameId() throws SQLException {
        // arrange
        Transaction obj = new Transaction();
        
        // act
        String actual = obj.cust_send_money("15223814", "15223814", "50");
        
        // assert
        assertEquals("Can't send money to yourself !!", actual);
    }
    @Test (expected = SQLException.class)
    public void testcust_send_moneySQLException() throws SQLException {
        // arrange
        Transaction obj = new Transaction();
        
        // act
        String actual = obj.cust_send_money("164", "13814", "50");// id doesn't exist
    } 
    @Test (expected = NumberFormatException.class)
    public void testcust_send_moneyNumberFormatException() throws SQLException {
        // arrange
        Transaction obj = new Transaction();
        
        // act
        String actual = obj.cust_send_money("15223814", "15223975", "50aas");// amount contains characters
    } 
    
    
    @Test
    public void testcust_donate_moneyTrue() throws SQLException {
        // arrange
        Transaction obj = new Transaction();
        
        // act
        String actual = obj.cust_donate_money("15223814", "14217", "50");
        
        // assert
        assertEquals("Transaction Completed Successfully", actual);
    }  
    @Test
    public void testcust_donate_moneyNoBalance() throws SQLException {
        // arrange
        Transaction obj = new Transaction();
        
        // act
        String actual = obj.cust_donate_money("15223814", "14217", "50000000");
        
        // assert
        assertEquals("Insufficient Balance", actual);
    }
    @Test (expected = SQLException.class)
    public void testcust_donate_moneySQLException() throws SQLException {
        // arrange
        Transaction obj = new Transaction();
        
        // act
        String actual = obj.cust_donate_money("15814", "14", "50");// id doesn't exist
    }  
    @Test (expected = NumberFormatException.class)
    public void testcust_donate_moneyNumberFormatException() throws SQLException {
        // arrange
        Transaction obj = new Transaction();
        
        // act
        String actual = obj.cust_donate_money("15223814", "14217", "50aAA");// amount contains characters
    }     
}
