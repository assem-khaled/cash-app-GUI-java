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
public class DonationTest {
    
    @Test
    public void testshow_charitiesTrue() throws SQLException {
        // arrange
        Donation obj = new Donation();
        
        // act
        ResultSet actual = obj.show_charities();
        
        // assert
        assertNotNull(actual);
    }
    
}
