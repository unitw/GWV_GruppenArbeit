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
public class DummySpielbrett extends Observable implements Spielbrett {

    public static final int DEFAULT_ANZAHL_SPIELER = 2;
    public static final int FIGUREN_PRO_SPIELER = 2;
    public static final int SPIELFELDGROESSE = 20;

    private List<Spieler> _spieler;
    private Map<Spieler, Integer> _homeBases;
    private Spieler[] _spielfeld = new Spieler[SPIELFELDGROESSE];

    private Spieler _anDerReihe;

    /**
     * Erstellt ein DummySpielbrett mit einer Spieleranzahl von
     * DEFAULT_ANZAHL_SPIELER. Diese sind alle KIs.
     */
    public DummySpielbrett() {
        //TODO _anDerReihe initialisieren

        for (int i = 1; i < DEFAULT_ANZAHL_SPIELER; ++i) {
            // TODO Spieler initialisieren und Figuren hinzufügen
        }
    }

    /**
     *
     * @param spieler Eine Liste mit Spielern, die am Spiel teilnehmen. Die
     * Reihenfolge in der Liste bestimmt die Zugreihenfolge.
     * @throws IllegalArgumentException Wird geworfen falls die Anzahl der
     * Figuren die SPIELFELDGROESSE ueberschreitet.
     */
    public DummySpielbrett(List<Spieler> spieler) throws IllegalArgumentException {
        if (spieler.size() * FIGUREN_PRO_SPIELER > SPIELFELDGROESSE + 1) {
            throw new IllegalArgumentException("Spielbrett kann nicht erstellt werden, zu viele Spieler");
        }
        int aktuellePos = 0;
        for (Spieler s : spieler) {
            _homeBases.put(s, FIGUREN_PRO_SPIELER);
        }

        _anDerReihe = spieler.get(0);
        _spieler = spieler;
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
    public Set<Zug> pruefe(Spieler s, int augenzahl) {
        Set<Zug> zuege = new HashSet<>();
        int homebase = _homeBases.get(s);
        if (augenzahl == 6) {
            if (homebase != 0) {
                zuege.add(new Zug(-1, 0));
                return zuege;
            }
        }

        // Prueft fuer jede Figur des Spielers auf dem Brett (nicht in der Homebase), ob sie ziehen kann
        for (int probierteFiguren = 0, aktuellerIndex = 0;
                probierteFiguren < (FIGUREN_PRO_SPIELER - homebase) && aktuellerIndex < SPIELFELDGROESSE;
                ++aktuellerIndex) {
            if (istSpielerFeld(s, aktuellerIndex) && aktuellerIndex < _spielfeld.length) {
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
    private void setze(Zug zug) {
        if (zug.getAusgangsPos() == -1) {
            int newHomeBase = _homeBases.get(_anDerReihe) - 1;
            _homeBases.put(_anDerReihe, newHomeBase);
        } else {
            _spielfeld[zug.getAusgangsPos()] = null;
        }
        if (_spielfeld[zug.getZielPos()] != null) {
            Spieler gegner = _spielfeld[zug.getZielPos()];
            int newGegnerHomeBase = _homeBases.get(gegner) - 1;
            _homeBases.put(gegner, newGegnerHomeBase);
        }

        _spielfeld[zug.getZielPos()] = _anDerReihe;
        naechsterSpieler();
    }

    //setze 
    //prüfe
    //getSpielstand
    private boolean istSpielerFeld(Spieler s, int feldIndex) {
        return _spielfeld[feldIndex].equals(s);
    }
    
    private void naechsterSpieler()
    {
       int anDerReiheIndex = (_spieler.lastIndexOf(_anDerReihe) + 1) % _spieler.size();
       _anDerReihe = _spieler.get(anDerReiheIndex);
    }
}
