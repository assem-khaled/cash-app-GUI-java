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
public class EmployeeTest {

    @Test
    public void testemp_sign_inTrue() throws SQLException {
        // arrange
        Employee obj = new Employee();
        
        // act
        boolean actual = obj.emp_sign_in("1522", "789");
        
        // assert
        assertTrue(actual);
    }
    @Test
    public void testemp_sign_inFalse() throws SQLException {
        // arrange
        Employee obj = new Employee();
        
        // act
        boolean actual = obj.emp_sign_in("1522", "000");
        
        // assert
        assertFalse(actual);
    }
     @Test (expected = SQLException.class)
    public void testemp_sign_inSQLException() throws SQLException {
        // arrange
        Employee obj = new Employee();
        
        // act
        obj.emp_sign_in("172", "1111");// ID doesn't exist
    }     
    
    @Test
    public void testview_customer_dataTrue() throws SQLException {
        // arrange
        Employee obj = new Employee();
        
        // act
        String[] actual = obj.view_customer_data("15226386");
        String temp [] = new String[5];
        temp[0] = "15226386";
        temp[1] = "468";
        temp[2] = "Ahmed fathy";
        temp[3] = "2000";
        temp[4] = "Nasr City, P.O. Box: 11371";
        
        // assert
        assertArrayEquals(temp, actual);
    }
    @Test (expected = SQLException.class)
    public void testview_customer_dataSQLException() throws SQLException {
        // arrange
        Employee obj = new Employee();
        
        // act
        obj.view_customer_data("566644");// id doesn't exist
    }


    @Test 
    public void testdelete_customerTrue() throws SQLException {
        // arrange
        Employee obj = new Employee();
        
        // act
        obj.delete_customer("11111111", "1111");
    }
    @Test (expected = SQLException.class)
    public void testdelete_customerSQLException() throws SQLException {
        // arrange
        Employee obj = new Employee();
        
        // act
        obj.delete_customer("566644", "1111");// id doesn't exist
    }    
}
