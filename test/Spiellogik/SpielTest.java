/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

import Spiellogik.KI.KI;
import Spiellogik.KI.DecisionNetworkKI;
import static Spiellogik.Spiel.WUERFELGROESSE;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
public class SpielTest {

    private Mensch _mensch1;
    private Mensch _mensch2;
    private Mensch _mensch3;
    private KI _ki;
    private Spiel _spiel1; // Mit KI
    private Spiel _spiel2; // Ohne KI
    private Spiel _spiel3; // Ohne KI

    private boolean _oberserMitMenschAufgerufen = false;
    private boolean _observerMitNullAufgerufen = false;
    private boolean _observerMitAnderemAufgerufen = false;

    public SpielTest() {
        _mensch1 = new Mensch();
        _mensch2 = new Mensch();
        _mensch3 = new Mensch();
        _ki = new DecisionNetworkKI();

        Spieler[] spielerSpiel1 = {_mensch1, _ki, _mensch2};
        Spieler[] spielerSpiel2 = {_mensch1, _mensch2, _mensch3};
        Spieler[] spielerSpiel3 = {_mensch1, _mensch2};

        _spiel1 = new Spiel(spielerSpiel1, new ArraySpielbrett(spielerSpiel1.length));
        _spiel2 = new Spiel(spielerSpiel2, new ArraySpielbrett(spielerSpiel2.length));
        _spiel3 = new Spiel(spielerSpiel3, new ArraySpielbrett(spielerSpiel3.length));
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

    /*
    @Test
    public void testObserver() {

        Spiel testSpiel = _spiel2;
        Observer oberserver = new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                if (arg instanceof Mensch) {
                    SpielTest.this._oberserMitMenschAufgerufen = true;
                } else if (arg == null) {
                    SpielTest.this._observerMitNullAufgerufen = true;
                } else {
                    SpielTest.this._observerMitAnderemAufgerufen = true;
                }
            }
        };
        testSpiel.addObserver(oberserver);
        // Hier werden ein paar Zuege durchprobiert um die Setzmechanik 
        // und die Interaktion mit der UI zu testen.
        for (int i = 0; i < 10; ++i) {
            testSpiel.naechsterZug();
            Spieler aktuellerSpieler = testSpiel.getAktuellerSpieler();
            boolean sechsGewuerfelt = 6 == testSpiel.getAktuelleAugenzahl();
            if (aktuellerSpieler instanceof Mensch && !sechsGewuerfelt) {
                assertTrue("Observer wurde nicht über Zug von Mensch benachrichtigt",
                        _oberserMitMenschAufgerufen);
                Set<Zug> moeglicheZuege = testSpiel.getMoeglicheZuege();
                for (Zug zug : moeglicheZuege) {
                    testSpiel.ziehe(zug);
                    break;
                }
            } else if (aktuellerSpieler instanceof KI && !sechsGewuerfelt) {
                assertFalse(_oberserMitMenschAufgerufen);
            }
            assertTrue("Observer wurde nicht aufgefordert UI zu updaten",
                    _observerMitNullAufgerufen);
            assertFalse(_observerMitAnderemAufgerufen);
            _oberserMitMenschAufgerufen = false;
            _observerMitNullAufgerufen = false;
            _observerMitAnderemAufgerufen = false;
        }
    }
    */
    


    /**
     * Test of ziehe method, of class Spiel.
     */
    @Test
    public void testZiehe() {
        System.out.println("ziehe");
        Zug zug = null;
        Spiel instance = null;
        instance.ziehe(zug);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMoeglicheZuege method, of class Spiel.
     */
    @Test
    public void testGetMoeglicheZuege() {
        System.out.println("getMoeglicheZuege");
        Spiel instance = null;
        List<Zug> expResult = null;
        List<Zug> result = instance.getMoeglicheZuege();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAktuellerSpieler method, of class Spiel.
     */
    @Test
    public void testGetAktuellerSpieler() {
        System.out.println("getAktuellerSpieler");
        Spiel instance = null;
        Spieler expResult = null;
        Spieler result = instance.getAktuellerSpieler();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAktuellerSpielerIndex method, of class Spiel.
     */
    @Test
    public void testGetAktuellerSpielerIndex() {
        System.out.println("getAktuellerSpielerIndex");
        Spiel instance = null;
        int expResult = 0;
        int result = instance.getAktuellerSpielerIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNaechsterSpieler method, of class Spiel.
     */
    @Test
    public void testGetNaechsterSpieler() {
        System.out.println("getNaechsterSpieler");
        Spiel instance = null;
        Spieler expResult = null;
        Spieler result = instance.getNaechsterSpieler();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNaechsterSpielerIndex method, of class Spiel.
     */
    @Test
    public void testGetNaechsterSpielerIndex() {
        System.out.println("getNaechsterSpielerIndex");
        Spiel instance = null;
        int expResult = 0;
        int result = instance.getNaechsterSpielerIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAktuelleAugenzahl method, of class Spiel.
     */
    @Test
    public void testGetAktuelleAugenzahl() {
        System.out.println("getAktuelleAugenzahl");
        Spiel instance = null;
        int expResult = 0;
        int result = instance.getAktuelleAugenzahl();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testWuerfeln() {
        Map<Integer, Integer> wurfVerteilung = new HashMap<>();
        for (int i = 1; i <= WUERFELGROESSE; ++i) {
            wurfVerteilung.put(i, 0);
        }
        
        for (int i = 0; i < 1000000; ++i) {
            Random wuerfel = new Random();
            int augenzahl = wuerfel.nextInt(WUERFELGROESSE) + 1;
            int haeufigkeit = wurfVerteilung.get(augenzahl);
            wurfVerteilung.put(augenzahl, haeufigkeit + 1);
        }
        System.out.println(wurfVerteilung.toString());
    }
}
