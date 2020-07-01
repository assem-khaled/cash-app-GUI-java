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

/**
 *
 * @author Assem
 */
public class CurrencyTest {
    
    @Test
    public void testshow_currencyTrue() throws SQLException {
        // arrange
        Currency obj = new Currency();
        
        // act
        ResultSet actual = obj.show_currency();
        
        // assert
        assertNotNull(actual);
    }
    
 
    @Test 
    public void testemp_update_currencyTrue() throws SQLException {
        // arrange
        Currency obj = new Currency();
        
        // act
        obj.emp_update_currency("78681", "1111", "16.5", "16.9");
    } 
    @Test (expected = SQLException.class)
    public void testemp_update_currencySQLException() throws SQLException {
        // arrange
        Currency obj = new Currency();
        
        // act
        obj.emp_update_currency("172", "1111", "16.5", "16.9");// code doesn't exist
    }  
}
