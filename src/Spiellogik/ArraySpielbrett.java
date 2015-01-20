/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

import GUI.FeldUI;
import GUI.Homebase;
import GUI.SpielbrettUI;
import java.util.ArrayList;
import java.util.List;

/**
 * Ein Spielbrett Objekt repraesentiert ein Spielbrett, das aus den Home Bases
 * der Spieler besteht und dem Spielfeld auf dem die Spieler versuchen das Ende
 * zu erreichen
 *
 * @author rw
 */
public class ArraySpielbrett implements Spielbrett {

    public static int FIGUREN_PRO_SPIELER = 4;
    public int _spielfeldGroesse;

    private HeimBasen _basen;
    private ZielBasen _ziele;
    private int[] _spielfeld;
    public SpielbrettUI spbrett;

    /**
     *
     * @param anzahlSpieler
     * @param spbrett
     * @param spieler Eine Liste mit Spielern, die am Spiel teilnehmen. Die
     * Reihenfolge in der Liste bestimmt die Zugreihenfolge.
     * @throws IllegalArgumentException Wird geworfen falls die Anzahl der
<<<<<<< HEAD
     * Figuren die spielfeldgroesse ueberschreitet.
     */
    public ArraySpielbrett(int anzahlSpieler, SpielbrettUI spbrett) throws IllegalArgumentException {
        if (anzahlSpieler * FIGUREN_PRO_SPIELER > spielfeldgroesse + 1) {
=======
 Figuren die _spielfeldGroesse ueberschreitet.
     */
    public ArraySpielbrett(int anzahlSpieler) throws IllegalArgumentException {
        this(anzahlSpieler, 20);
    }
    
    public ArraySpielbrett(int anzahlSpieler, int spielfeldGroesse) throws IllegalArgumentException {
        _spielfeldGroesse = spielfeldGroesse;
        if (anzahlSpieler * FIGUREN_PRO_SPIELER > _spielfeldGroesse + 1) {
>>>>>>> FETCH_HEAD
            throw new IllegalArgumentException("Spielbrett kann nicht erstellt werden, zu viele Spieler");
        }
        _basen = new HeimBasen(anzahlSpieler, FIGUREN_PRO_SPIELER);
        _ziele = new ZielBasen(anzahlSpieler, FIGUREN_PRO_SPIELER);
<<<<<<< HEAD
        _spielfeld = new int[spielfeldgroesse];
        this.spbrett = spbrett;

// Alle Felder werden mit -1 belegt, da -1 ein leeres Feld signalisiert
=======
        _spielfeld = new int[_spielfeldGroesse];
        // Alle Felder werden mit -1 belegt, da -1 ein leeres Feld signalisiert
>>>>>>> FETCH_HEAD
        // 0 ist die Kodierung des 1. Spielers.
        java.util.Arrays.fill(_spielfeld, -1);
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
    @Override
    public List<Zug> pruefe(int spieler, int augenzahl) {
        List<Zug> zuege = new ArrayList<>();
        int spielerBasis = _basen.basisBesetzung(spieler);
        if (augenzahl == Spiel.WUERFELGROESSE && spielerBasis != 0 && !istSpielerFeld(spieler, 0)) {
            zuege.add(new Zug(-1, 0));
            return zuege;
        }

        // Prueft fuer jede Figur des Spielers auf dem Brett (nicht in der Homebase), ob sie ziehen kann
        for (int probierteFiguren = 0, aktuellerIndex = 0;
                probierteFiguren < (FIGUREN_PRO_SPIELER - spielerBasis) && aktuellerIndex < _spielfeldGroesse;
                ++aktuellerIndex) {
            int zielIndex = aktuellerIndex + augenzahl;
            //TODO Temporärer Fix um nicht aus Array rauszulaufen
            if (zielIndex > _spielfeld.length) {
                ++probierteFiguren;
//                if (zielIndex == _spielfeld.length) {
                Zug zug = new Zug(aktuellerIndex, zielIndex);
                zuege.add(zug);
//                }
            }
            if (istSpielerFeld(spieler, aktuellerIndex) && zielIndex < _spielfeld.length
                    && !istSpielerFeld(spieler, aktuellerIndex + augenzahl)) {
                Zug zug = new Zug(aktuellerIndex, zielIndex);
                zuege.add(zug);
                ++probierteFiguren;
            }
        }

        return zuege;
    }

    /**
     * Zieht den angegebenen Zug mit dem Spieler der aktuell an der Reihe ist.
     *
     * @param zug Der durchzufuehrende Zug
     */
    @Override
    public void setze(int spieler, Zug zug) {
        if (zug.getAusgangsPos() == -1) {
            _basen.zieheAusBasis(spieler);

            for (Homebase base : spbrett.base) {
                if (base.getSpieler() == spieler) {
                    base.clearplace();
                }
            }

        } else {
            _spielfeld[zug.getAusgangsPos()] = -1;
        }
        if (zug.getZielPos() >= _spielfeldGroesse) { // Nicht aufs Array zugreifen, falls ins Ziel ziehen
            _ziele.zieheInsZiel(spieler);

        } // TODO Fehler mit 0 und -1 (Spieler 1 (Index 0) steht am Anfang überall
        else if (_spielfeld[zug.getZielPos()] != -1) {
            int geschlagen = _spielfeld[zug.getZielPos()];
            _basen.zieheInBasis(geschlagen);
            _spielfeld[zug.getZielPos()] = spieler;

            for (Homebase base : spbrett.base) {

                base.eineFigurinbasis(spieler);
            }
        } else {
            _spielfeld[zug.getZielPos()] = spieler;
            for (FeldUI feldarray : spbrett.feldarray) {
                if (zug.getZielPos() == feldarray.getidx()) {

                    feldarray.setFigur(true, spieler);
                }
            }

        }

    }
    //setze 
    //prüfe
    //getSpielstand

    public boolean istSpielerFeld(int spieler, int feldIndex) {
        return _spielfeld[feldIndex] == spieler;
    }

    public int spielfeldGroesse() {
        return _spielfeld.length;
    }

    public int[] getSpielfeld() {
        return _spielfeld.clone();
    }

    public HeimBasen getHeimBasen() {
        return _basen.clone();
    }

    // TODO Anpassen an variable Startpositionen
    public int getStartPos(int spieler) {
        
        return 0;
    }
    
    public int getZielPos(int spieler) {
        return 0;
    }
    
    public int getZielEingang(int spieler) {
        int zielPos = getZielPos(spieler);
        if (zielPos - 1 >= 0) {
            return zielPos - 1;
        } else {
            return _spielfeldGroesse;
        }
    }
    
    public int getEntfernungZuZiel(int feld) {
        int spieler = _spielfeld[feld];
        int zielPos = getZielEingang(spieler);
        if (zielPos < feld) {
            return _spielfeldGroesse - feld + 1;
        } else {
            return zielPos - feld;
        }
    }
    

    public ArraySpielbrett clone() {
        ArraySpielbrett clone = new ArraySpielbrett(_basen.getAnzahlSpieler(), this.spbrett);
        clone._basen = getHeimBasen();
        clone._spielfeld = getSpielfeld();
        return clone;
    }

    public String toString() {
        return _basen.toString() + "\n" + spielfeldToString();
    }

    private String spielfeldToString() {
        String ausgabe = "";
        for (int i = 0; i < _spielfeld.length; ++i) {
            ausgabe += "| ";
        }
        ausgabe += "|\n";
        for (int spieler : _spielfeld) {
            if (spieler == -1) {
                ausgabe += "|_";
            } else {
                ausgabe += "|" + spieler;
            }
        }
        ausgabe += "|";
        return ausgabe;
    }
}
