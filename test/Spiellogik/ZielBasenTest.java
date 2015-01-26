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
public class ZielBasenTest {
    
    public ZielBasenTest() {
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
     * Test of zieheInsZiel method, of class ZielBasen.
     */
    @Test
    public void testZieheInsZiel() {
//        System.out.println("zieheInsZiel");
//        int spieler = 0;
//        ZielBasen instance = null;
//        instance.zieheInsZiel(spieler);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of basisVoll method, of class ZielBasen.
     */
    @Test
    public void testBasisVoll() {
//        System.out.println("basisVoll");
//        int spieler = 0;
//        ZielBasen instance = null;
//        boolean expResult = false;
//        boolean result = instance.basisVoll(spieler);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of basisBesetzung method, of class ZielBasen.
     */
    @Test
    public void testBasisBesetzung() {
//        System.out.println("basisBesetzung");
//        int spieler = 0;
//        ZielBasen instance = null;
//        int expResult = 0;
//        int result = instance.basisBesetzung(spieler);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class ZielBasen.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        ZielBasen original = new ZielBasen(2, 4);
        ZielBasen copy = original.clone();
        
        original.zieheInsZiel(1);
        original.zieheInsZiel(1);
        
        assertFalse(original.basisBesetzung(1) == copy.basisBesetzung(1));
    }

    /**
     * Test of toString method, of class ZielBasen.
     */
    @Test
    public void testToString() {
//        System.out.println("toString");
//        ZielBasen instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
