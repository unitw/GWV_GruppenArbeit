/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

/**
 * Verwaltet die verschiedenen Heim Basen fuer ein Spielbrett. Es wird nicht nur
 * eine Heimbasis gespeichert, sondern alle fuer das Spiel noetien Basen koennen
 * gespeichert werden.
 *
 * @author Chris
 */
public class HeimBasen {

    private int[] _homeBases;
    private int _basisGroesse;

    /**
     * Erstellt neue Heimbasen für alle Spieler in der Liste. Jeder Spieler
     * bekommt zu Anfang eine volle Heimbasis, d.h. es sind alle Figuren in der
     * Basis
     *
     * @param anzahlSpieler die Anzahl von Spielern für die eine Basis
     * gespeichert werden soll
     * @param basisGroesse die Groesse der Basen in dem jeweiligen Spiel
     */
    public HeimBasen(int anzahlSpieler, int basisGroesse) {
        _homeBases = new int[anzahlSpieler];
        for (int i = 0; i < anzahlSpieler; ++i) {
            _homeBases[i] = basisGroesse;
        }
        _basisGroesse = basisGroesse;
    }

    /**
     * Zieht eine Figur des Spielers aus der Basis heraus
     *
     * @param spieler der Index des Spielers
     */
    public void zieheAusBasis(int spieler) {
        _homeBases[spieler] = basisBesetzung(spieler) - 1;
    }

    /**
     * Zieht die Figur eines Spielers in seine Basis zurueck.
     *
     * @param spieler der Index des Spielers
     */
    public void zieheInBasis(int spieler) {
        _homeBases[spieler] = basisBesetzung(spieler) + 1;
    }

    /**
     * Gibt an wieviele Figuren derzeit in der Basis des Spielers sind.
     *
     * @param spieler der Index des Spielers
     * @return die Anzahl der Figuren in der Basis
     */
    public int basisBesetzung(int spieler) {
        return _homeBases[spieler];
    }
    
    public HeimBasen clone()
    {
        HeimBasen clone = new HeimBasen(_homeBases.length, _basisGroesse);
        clone._homeBases = this._homeBases;
        return clone;
    }
}
