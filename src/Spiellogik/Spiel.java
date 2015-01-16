/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.SortedSet;

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
    private int _aktuelleAugenzahl;
    private int _wurfOptionen;
    private List<Zug> _moeglicheZuegeMensch;

    public Spiel(Spieler[] spieler, Spielbrett spielbrett) {
        _spieler = spieler;
        _spielbrett = spielbrett;
        _moeglicheZuegeMensch = null;
        _wurfOptionen = MAXIMALE_WUERFE_PRO_ZUG;
    }

    /**
     * Diese Methode sorgt dafür, dass das Spiel fortgesetzt wird und soll daher
     * immer aufgerufen werden nachdem ein menschlicher Spieler gezogen hat
     * (falls dies möglich ist) oder die UI aktualisiert wurde.
     */
    public void spielFortfahren() {
        naechsterZug(_wurfOptionen);
    }

    private void naechsterZug(int wurfOptionen) {
        if (wurfOptionen > 0) {

            Spieler aktuellerSpieler = _spieler[_anDerReihe];
            wuerfeln();
            List<Zug> zuege = _spielbrett.pruefe(_anDerReihe, _aktuelleAugenzahl);
            //TODO Spieler muss ziehen
            if (zuege.isEmpty()) {
                --_wurfOptionen;
                updateUI();
            } else if (zuege.size() == 1) {
                for (Zug zug : zuege) {
                    ziehe(zug);
                }
            } else if (aktuellerSpieler instanceof KI) {
                KI ki = (KI) aktuellerSpieler;
                Zug zug = ki.entscheide(zuege);
                ziehe(zug);
            } else if (aktuellerSpieler instanceof Mensch) {
                _moeglicheZuegeMensch = zuege;

                menschAmZug();
            }
        } else {
            naechsterSpieler();
        }
    }

    /**
     * Zieht mit dem aktuellen Spieler den angegebenen Zug. Diese Methode sollte
     * nur augeführt werden, wenn ein Mensch an der Reihe ist. Dies wird dadurch
     * signalisiert, dass die Beobachter dieses Spiels benachrichtigt werden und
     * als Argument der aktuelle Spieler uebergeben wird. Das Spieler-Objekt ist
     * dann auf jeden Fall vom Typ Mensch, da das Spiel dafür sorgt, dass die
     * KI-Spieler ziehen.
     *
     * @param zug Ein gültiger Zug für den aktuellen Spieler (Dies wird nicht
     * überprüft)
     */
    public void ziehe(Zug zug) {
        _spielbrett.setze(_anDerReihe, zug);
        naechsterSpieler();
    }

    /**
     * Gibt die moeglichen Zuege fuer den aktuellen Spieler an, falls dieser ein
     * Mensch ist. Ist der aktuelle Spieler kein Mensch, werden die moeglichen
     * Zuege fuer den letzten Mensch angezeigt der an der Reihe war.
     *
     * @return moegliche Zuege fuer aktuellen Mensch, sind alle Spieler KI,
     * null.
     */
    public List<Zug> getMoeglicheZuege() {
        return _moeglicheZuegeMensch;
    }

    /**
     * Spieler der aktuell an der Reihe ist. Ein Spieler ist immer so lange an
     * der Reihe bis er gezogen hat, danach ist der nächste Spieler an der
     * Reihe.
     *
     * @return der aktuelle Spieler
     */
    public Spieler getAktuellerSpieler() {
        return _spieler[_anDerReihe];
    }

    /**
     * Index des aktuellen Spielers. Ein Spieler ist immer so lange an der Reihe
     * bis er gezogen hat, danach ist der nächste Spieler an der Reihe.
     *
     * @return der Index des aktuellen Spieler
     */
    public int getAktuellerSpielerIndex() {
        return _anDerReihe;
    }

    /**
     * Der Spieler der als nächstes an der Reihe ist. Dies ist der Fall sobald
     * der aktuelle Spieler gezogen hat.
     *
     * @return naechster Spieler
     */
    public Spieler getNaechsterSpieler() {
        int naechsterIndex = getNaechsterSpielerIndex();
        return _spieler[naechsterIndex];
    }

    /**
     * Der Index des Spielers der als nächstes an der Reihe ist. Dies ist der
     * Fall sobald der aktuelle Spieler gezogen hat.
     *
     * @return Index des naechsten Spielers
     */
    public int getNaechsterSpielerIndex() {
        return (_anDerReihe + 1) % _spieler.length;
    }

    /**
     * Gibt die aktuelle Augenzahl des Spielwuerfels zurueck.
     *
     * @return
     */
    public int getAktuelleAugenzahl() {
        return _aktuelleAugenzahl;
    }

    private void naechsterSpieler() {
        _anDerReihe = getNaechsterSpielerIndex();
        _wurfOptionen = MAXIMALE_WUERFE_PRO_ZUG;
        updateUI();
    }

    private boolean zugMoeglich(Zug zug) {
        return _spielbrett.istSpielerFeld(_anDerReihe, zug.getAusgangsPos())
                && !_spielbrett.istSpielerFeld(_anDerReihe, zug.getZielPos());
    }

    private void wuerfeln() {
        Random wuerfel = new Random();
        int augenzahl = wuerfel.nextInt(WUERFELGROESSE) + 1;
        _aktuelleAugenzahl = augenzahl;
    }

    private void menschAmZug() {
        setChanged();
        notifyObservers(getAktuellerSpieler());
    }

    private void updateUI() {
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        String ausgabe = "\n" + _spielbrett.toString();
        ausgabe += "\nAn der Reihe: " + getAktuellerSpielerIndex() + "Augenzahl " +  getAktuelleAugenzahl();
        return ausgabe;
    }
}
