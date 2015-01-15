/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

import java.util.Observable;
import java.util.Random;
import java.util.Set;

/**
 * Diese Klasse implementiert das Spiel Mensch Aergere Dich Nicht. Das Spiel
 * kann mit einer beliebigen Mischung aus <code>KI</code> und
 * <code>Mensch</code> gespielt werden, solange die Anzahl an Spielern zwischen
 * <code>MIN_SPIELER</code> und <code>MAX_SPIELER</code> ist. Bei allen
 * KI-Spielern sorgt diese Klasse dafür, dass gezogen wird, bei menschlichen
 * Spielern werden beobachtende Objekte benachrichtigt und erhalten den Spieler
 * der ziehen soll als Argument. Um zu ziehe soll dann
 * <code>getMoeglicheZuege()</code> aufgerufen werden um die moeglichen Zuege
 * fuer den aktuellen Spieler zu erfahren. Nun muss einer ausgewaehlt werden und
 * mit einem Aufruf von <code>ziehe()</code> gezogen werden. 
 *
 * @author rw
 */
public class Spiel extends Observable {

    public static final int MAX_SPIELER = 4;
    public static final int MIN_SPIELER = 2;
    public static final int MAXIMALE_WUERFE_PRO_ZUG = 3;
    public static final int WUERFELGROESSE = 6;
    public static final String SPIELENDE = "Spielende";

    private final Spielbrett _spielbrett;
    private final Spieler[] _spieler;
    private int _anDerReihe;
    private Set<Zug> _moeglicheZuegeMensch;

    public Spiel(Spieler[] spieler, Spielbrett spielbrett) {
        _spieler = spieler;
        _spielbrett = spielbrett;
        _moeglicheZuegeMensch = null;
    }

    public void naechsterZug() {
        naechsterSpieler();
        naechsterZug(1);
    }

    private void naechsterZug(int wurf) {
        if (wurf <= MAXIMALE_WUERFE_PRO_ZUG) {

            Spieler aktuellerSpieler = _spieler[_anDerReihe];
            int augenzahl = wuerfeln();
            Set<Zug> zuege = _spielbrett.pruefe(_anDerReihe, augenzahl);
            //TODO Spieler muss ziehen
            if (zuege.isEmpty()) {
                naechsterZug(++wurf);
            } else if (zuege.size() == 1) {
                for (Zug zug : zuege) {
                    _spielbrett.setze(_anDerReihe, zug);
                }
            } else if (aktuellerSpieler instanceof KI) {
                KI ki = (KI) aktuellerSpieler;
                Zug zug = ki.entscheide(zuege);
                ziehe(zug);
            } else if (aktuellerSpieler instanceof Mensch) {
                _moeglicheZuegeMensch = zuege;
                setChanged();
                notifyObservers(aktuellerSpieler);
            }
        }
    }

    public void ziehe(Zug zug) {
        if (zugMoeglich(zug)) {
            _spielbrett.setze(_anDerReihe, zug);
            updateUI();
        }
    }

    /**
     * Gibt die moeglichen Zuege fuer den aktuellen Spieler an, falls dieser ein
     * Mensch ist. Ist der aktuelle Spieler kein Mensch, werden die moeglichen
     * Zuege fuer den letzten Mensch angezeigt der an der Reihe war.
     *
     * @return moegliche Zuege fuer aktuellen Mensch, sind alle Spieler KI,
     * null.
     */
    public Set<Zug> getMoeglicheZuege() {
        return _moeglicheZuegeMensch;
    }

    /**
     * Spieler der aktuell an der Reihe ist
     *
     * @return
     */
    public Spieler getAktuellerSpieler() {
        return _spieler[_anDerReihe];
    }

    /**
     * Index des aktuellen Spielers
     *
     * @return
     */
    public int getAktuellerSpielerIndex() {
        return _anDerReihe;
    }

    private void naechsterSpieler() {
        _anDerReihe = (_anDerReihe + 1) % _spieler.length;
    }

    private boolean zugMoeglich(Zug zug) {
        return _spielbrett.istSpielerFeld(_anDerReihe, zug.getAusgangsPos())
                && !_spielbrett.istSpielerFeld(_anDerReihe, zug.getZielPos());
    }

    private int wuerfeln() {
        // TODO Dummy
        Random wuerfel = new Random();
        int augenzahl = wuerfel.nextInt(WUERFELGROESSE) + 1;
        return augenzahl;
    }

    private void updateUI() {
        notifyObservers(this);
    }

}
