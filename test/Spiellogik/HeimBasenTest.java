/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chris
 */
public class HeimBasenTest {
    
    public HeimBasenTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of zieheAusBasis method, of class HeimBasen.
     */
    @Test
    public void testZieheAusBasis() {
//        System.out.println("zieheAusBasis");
//        int spieler = 0;
//        HeimBasen instance = null;
//        instance.zieheAusBasis(spieler);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of zieheInBasis method, of class HeimBasen.
     */
    @Test
    public void testZieheInBasis() {
//        System.out.println("zieheInBasis");
//        int spieler = 0;
//        HeimBasen instance = null;
//        instance.zieheInBasis(spieler);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of basisBesetzung method, of class HeimBasen.
     */
    @Test
    public void testBasisBesetzung() {
//        System.out.println("basisBesetzung");
//        int spieler = 0;
//        HeimBasen instance = null;
//        int expResult = 0;
//        int result = instance.basisBesetzung(spieler);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnzahlSpieler method, of class HeimBasen.
     */
    @Test
    public void testGetAnzahlSpieler() {
//        System.out.println("getAnzahlSpieler");
//        HeimBasen instance = null;
//        int expResult = 0;
//        int result = instance.getAnzahlSpieler();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class HeimBasen.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        HeimBasen original = new HeimBasen(2, 4);
        HeimBasen copy = original.clone();
        original.zieheAusBasis(1);
        original.zieheAusBasis(1);
        assertFalse(original.basisBesetzung(1) == copy.basisBesetzung(1));
        
    }

    /**
     * Test of toString method, of class HeimBasen.
     */
    @Test
    public void testToString() {
//        System.out.println("toString");
//        HeimBasen instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
