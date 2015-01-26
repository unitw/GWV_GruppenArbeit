/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

import Spiellogik.KI.KI;
import GUI.SpielbrettUI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

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
    private List<Integer> _rangfolge;
    private int _anDerReihe;
    private int _aktuelleAugenzahl;
    private int _wurfOptionen;
    private boolean _spielZuEnde;
    private List<Zug> _moeglicheZuege;

    /**
     * Erstellt ein neues Spiel mit den übergebenen Spielern auf dem übergebenen
     * Spielbrett.
     *
     * @param spieler
     * @param spielbrett
     */
    public Spiel(Spieler[] spieler, Spielbrett spielbrett) {
        _spieler = spieler;
        _spielbrett = spielbrett;
        _moeglicheZuege = new LinkedList<Zug>();
        _wurfOptionen = MAXIMALE_WUERFE_PRO_ZUG;
        _spielZuEnde = false;
        _rangfolge = new ArrayList<>();
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
        fuelleRangFolge();
        if (!spielZuEnde()) {
            if (wurfOptionen > 0) {
                wuerfeln();
                _moeglicheZuege = _spielbrett.pruefe(_anDerReihe, _aktuelleAugenzahl);
                // TODO In Spielbrett auslagern
                // TODO Aus 0 dynamische Startposition machen
                if (_spielbrett.istSpielerFeld(_anDerReihe, 0) && _moeglicheZuege.size() > 1) {
                    for (int i = 0; i < _moeglicheZuege.size(); ++i) {
                        if (_moeglicheZuege.get(i).getAusgangsPos() != 0) {
                            _moeglicheZuege.remove(i);
                            --i;
                        }
                    }
                }

                //TODO Spieler muss ziehen
                if (_moeglicheZuege.isEmpty()) {
                    --_wurfOptionen;
                } else if (_aktuelleAugenzahl == WUERFELGROESSE) {
                    _wurfOptionen = 1;
                } else {
                    _wurfOptionen = 0;
                }
                updateUIMitSpieler();

            } else {
                naechsterSpieler();
            }
        } else {
            _spielZuEnde = true;
        }
    }

    private void fuelleRangFolge() {
        
    }
    
    public boolean spielZuEnde() {
        int volleBasen = 0;
        for (int i = 0; i < _spieler.length; ++i) {
            if (_spielbrett.alleImZiel(i)) {
                ++volleBasen;
            }
        }
        return volleBasen == _spieler.length - 1;
    }

    /**
     * Zieht mit dem aktuellen Spieler den angegebenen Zug. Diese Methode sollte
     * nur augeführt werden, wenn ein Mensch an der Reihe ist. Dies wird dadurch
     * signalisiert, dass die Beobachter dieses Spiels benachrichtigt werden und
     * als Argument der aktuelle Spieler uebergeben wird. 
     *
     * @param zug Ein gültiger Zug für den aktuellen Spieler (Dies wird nicht
     * überprüft)
     * @throws IllegalStateException falls der aktuelle Spieler kein Mensch ist.
     */
    public void ziehe(Zug zug) throws IllegalStateException {
        if (getAktuellerSpieler() instanceof Mensch) {
            _spielbrett.setze(_anDerReihe, zug);

            if (_aktuelleAugenzahl != WUERFELGROESSE) {
                naechsterSpieler();
            } else {
                updateUI();
            }
        } else {
            //   throw new IllegalStateException("Momentan ist eine KI an der Reihe, zieheKI() muss aufgerufen werden");
        }
    }

    /**
     * Zieht mit dem aktuellen Spieler den nächsten Zug, falls dieser eine KI ist.
     * Der Entscheidungsprozess wird an die jeweilige KI weitergegeben und der Zug gezogen.
     * @throws IllegalStateException falls der aktuelle Spieler keine KI ist
     */
    public void zieheKI() throws IllegalStateException {
        if (getAktuellerSpieler() instanceof KI) {
            KI ki = (KI) getAktuellerSpieler();
            if (!_moeglicheZuege.isEmpty()) {
                if (_moeglicheZuege.size() == 1) {
                    Zug zug = _moeglicheZuege.get(0);
                    _spielbrett.setze(_anDerReihe, zug);
                } else {
                    Zug zug = ki.entscheide(_moeglicheZuege);
                    _spielbrett.setze(_anDerReihe, zug);
                }
                if (_aktuelleAugenzahl != WUERFELGROESSE) {
                    naechsterSpieler();
                } else {
                    updateUI();
                }
            }
        } else {
            //   throw new IllegalStateException("Momentan ist ein Mensch an der Reihe, ziehe(Zug) muss aufgerufen werden");
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
    public List<Zug> getMoeglicheZuege() {
        return _moeglicheZuege;
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

    /**
     * Gibt an, ob das Feld ein Zielfeld fuer den aktuellen Spieler ist.
     *
     * @param feldIndex Index eines Feldes im Spiel
     * @return true, wenn es sich um ein Zielfeld fuer den aktuellen Spieler
     * handelt.
     */
    private boolean istZielFeld(int feldIndex) {
        return istZielFeld(_anDerReihe, feldIndex);
    }

    private boolean istZielFeld(int spielerIndex, int feldIndex) {
        return feldIndex == _spielbrett.spielfeldGroesse() - 1;
    }

    private void naechsterSpieler() {
        // TODO Diese Kodierung klar machen. Zwischenschritt, keiner ist dran.
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

    private void updateUIMitSpieler() {
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
        ausgabe += "\nAn der Reihe: " + getAktuellerSpielerIndex() + "Augenzahl " + getAktuelleAugenzahl();
        return ausgabe;
    }

    public String toStringReinesBrett() {
        return _spielbrett.toString();
    }
}
