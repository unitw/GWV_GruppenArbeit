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
    public int spielfeldgroesse = 20;

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
     * Figuren die spielfeldgroesse ueberschreitet.
     */
    public ArraySpielbrett(int anzahlSpieler, SpielbrettUI spbrett) throws IllegalArgumentException {
        if (anzahlSpieler * FIGUREN_PRO_SPIELER > spielfeldgroesse + 1) {
            throw new IllegalArgumentException("Spielbrett kann nicht erstellt werden, zu viele Spieler");
        }
        _basen = new HeimBasen(anzahlSpieler, FIGUREN_PRO_SPIELER);
        _ziele = new ZielBasen(anzahlSpieler, FIGUREN_PRO_SPIELER);
        _spielfeld = new int[spielfeldgroesse];
        this.spbrett = spbrett;

// Alle Felder werden mit -1 belegt, da -1 ein leeres Feld signalisiert
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
                probierteFiguren < (FIGUREN_PRO_SPIELER - spielerBasis) && aktuellerIndex < spielfeldgroesse;
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
        if (zug.getZielPos() >= spielfeldgroesse) { // Nicht aufs Array zugreifen, falls ins Ziel ziehen
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
