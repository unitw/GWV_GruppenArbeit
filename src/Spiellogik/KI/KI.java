/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik.KI;

import Spiellogik.Spielbrett;
import Spiellogik.Spieler;
import Spiellogik.Zug;
import java.util.List;

/**
 * Die Klasse KI beschreibt eine generische künstliche Intelligenz für das Spiel
 * Mensch Ärgere Dich Nicht. Wird die Operation entscheide aufgerufen so kann
 * eine KI mittels in Subklassen definierter Vorgehensweise einen Zug auswählen.
 *
 * @author Chris
 */
public abstract class KI implements Spieler {

    protected Spielbrett _brett;
    protected int _index;

    /**
     * Beim Aufruf dieses Konstruktors ist zu beachten, dass der erzeugten
     * Instanz ein Spielbrett und ein Spieler-Index zugewiesen werden muss,
     * bevor sie spielen kann. Dies geschieht mittels Aufrufen von
     * <code>setzeSpielbrett(Spielbrett)</code> und
     * <code>setzeSpielerIndex(int)</code>.
     */
    KI() {
    }

    /**
     * Erzeugt eine neue KI, die sofort bereit ist, auf dem uebergebenen
     * Spielbrett zu spielen
     *
     * @param brett Das Brett auf dem gespielt werden soll
     * @param index Der Index der KI auf dem Spielbrett brett
     */
    KI(Spielbrett brett, int index) {
        _brett = brett;
        _index = index;
    }

    /**
     * Dies setzt das Spielbrett fest auf dem die KI spielt
     * @param brett
     */
    public void setzeSpielbrett(Spielbrett brett) {
        _brett = brett;
    }

    /**
     * Diese Operation teilt der KI mit welcher Spieler sie auf dem Spielbrett ist.
     * Dies ist wichtig für die Entscheidungsfindung der KI. 
     * @param index
     */
    public void setzeSpielerIndex(int index) {
        _index = index;
    }

    /**
     * Nimmt eine Menge von Zuegen und entscheidet, welcher Zug gespielt werden
     * soll. 
     *
     * @param zuege die zur Auswahl stehenden Zuege
     * @return der Zug, der gezogen werden soll
     */
    abstract public Zug entscheide(List<Zug> zuege);
}
