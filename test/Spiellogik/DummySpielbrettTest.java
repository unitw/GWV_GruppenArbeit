/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

import java.util.Set;
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
public class DummySpielbrettTest {
    
    private DummySpielbrett _2SpielerBrett;
    
    public DummySpielbrettTest() {
        _2SpielerBrett = new DummySpielbrett(2);
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
     * Test of pruefe method, of class DummySpielbrett.
     */
    @Test
    public void testPruefe() {
        // Darf nicht aus der Homebase ziehen
        for (int zahl = 1; zahl < 6; ++zahl) {
            assertTrue(_2SpielerBrett.pruefe(0, zahl).isEmpty());
        }
        // Darf aus der Homebase ziehen
        assertFalse(_2SpielerBrett.pruefe(0, 6).isEmpty());
    }

    /**
     * Test of setze method, of class DummySpielbrett.
     */
    @Test
    public void testSetze() {
        // TODO review the generated test code and remove the default call to fail.
        _2SpielerBrett.setze(0, new Zug(-1, 3));
        assertTrue("Spieler wurde nicht korrekt gesetzt", _2SpielerBrett.istSpielerFeld(0, 3));
        assertEquals("Die Basis wurde nicht angepasst", (DummySpielbrett.FIGUREN_PRO_SPIELER - 1), 
                _2SpielerBrett.getHeimBasen().basisBesetzung(0));
        _2SpielerBrett.setze(1, new Zug(-1, 3));
        assertTrue("Spieler wurde nicht korrekt geschlagen", _2SpielerBrett.istSpielerFeld(1, 3));
        assertEquals("Geschlagener Spieler nicht in Homebase", (DummySpielbrett.FIGUREN_PRO_SPIELER), 
                _2SpielerBrett.getHeimBasen().basisBesetzung(0));
    }

    /**
     * Test of spielfeldGroesse method, of class DummySpielbrett.
     */
    @Test
    public void testSpielfeldGroesse() {
        // TODO review the generated test code and remove the default call to fail.
    }
    
}