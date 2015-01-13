/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

/**
 * Ein Spielbrett Objekt repraesentiert ein Spielbrett, das aus den Home Bases
 * der Spieler besteht und dem Spielfeld auf dem die Spieler versuchen das Ende
 * zu erreichen
 *
 * @author rw
 */
public class DummySpielbrett implements Spielbrett {
    
    public static int FIGUREN_PRO_SPIELER = 2;
    public static final int SPIELFELDGROESSE = 20;

    private HeimBasen _basen;
    private int[] _spielfeld = new int[SPIELFELDGROESSE];

    /**
     *
     * @param spieler Eine Liste mit Spielern, die am Spiel teilnehmen. Die
     * Reihenfolge in der Liste bestimmt die Zugreihenfolge.
     * @throws IllegalArgumentException Wird geworfen falls die Anzahl der
     * Figuren die SPIELFELDGROESSE ueberschreitet.
     */
    public DummySpielbrett(int anzahlSpieler) throws IllegalArgumentException {
        if (anzahlSpieler * FIGUREN_PRO_SPIELER > SPIELFELDGROESSE + 1) {
            throw new IllegalArgumentException("Spielbrett kann nicht erstellt werden, zu viele Spieler");
        }
        _basen = new HeimBasen(anzahlSpieler, FIGUREN_PRO_SPIELER);
    }

    /**
     * Prueft fuer einen Spieler welche Zuege ihm mit einer Augenzahl offen
     * stehen
     *
     * @param s der Spieler der ziehen soll
     * @param augenzahl die gewuerfelte Augenzahl
     * @return Ein Set aller moeglichen Zuege. Ist das Set leer, sind keine
     * Zuege moeglich.
     */
    public Set<Zug> pruefe(int spieler, int augenzahl) {
        Set<Zug> zuege = new HashSet<>();
        int spielerBasis = _basen.basisBesetzung(spieler);
        if (augenzahl == 6) {
            if (spielerBasis != 0) {
                zuege.add(new Zug(-1, 0));
                return zuege;
            }
        }

        // Prueft fuer jede Figur des Spielers auf dem Brett (nicht in der Homebase), ob sie ziehen kann
        for (int probierteFiguren = 0, aktuellerIndex = 0;
                probierteFiguren < (FIGUREN_PRO_SPIELER - spielerBasis) && aktuellerIndex < SPIELFELDGROESSE;
                ++aktuellerIndex) {
            if (istSpielerFeld(spieler, aktuellerIndex) && aktuellerIndex < _spielfeld.length) {
                int zielIndex = aktuellerIndex + augenzahl;
                Zug zug = new Zug(aktuellerIndex, zielIndex);
                zuege.add(zug);
            }
        }

        return zuege;
    }

    /**
     * Zieht den angegebenen Zug mit dem Spieler der aktuell an der Reihe ist.
     *
     * @param zug Der durchzufuehrende Zug
     */
    public void setze(int spieler, Zug zug) {
        if (zug.getAusgangsPos() == -1) {
            _basen.zieheAusBasis(spieler);
        } else {
            _spielfeld[zug.getAusgangsPos()] = -1;
        }
        if (_spielfeld[zug.getZielPos()] != -1) {
            int geschlagen = _spielfeld[zug.getZielPos()];
            _basen.zieheInBasis(geschlagen);
        }
        
        _spielfeld[zug.getZielPos()] = spieler;
    }

    //setze 
    //prüfe
    //getSpielstand
    public boolean istSpielerFeld(int spieler, int feldIndex) {
        return _spielfeld[feldIndex] == spieler;
    }
    
    public int spielfeldGroesse()
    {
        return _spielfeld.length;
    }
}
