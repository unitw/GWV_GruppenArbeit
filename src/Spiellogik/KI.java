/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

import java.util.Set;

/**
 *
 * @author Chris
 */
public abstract class KI extends Spieler {

    public KI(Spielbrett brett, int index) {
        super(brett, index);
    }

    /**
     * Der Aufruf der Methode startet den Entscheidungsprozess der KI.
     * Es wird ein Zug ausgewaehlt und diese Entscheidung zurueckgegeben.
     * @param augenzahl die gewuerfelte Augenzahl
     * @return der Zug der gezogen werden soll
     */
    public Zug spiele(int augenzahl) {
        Set<Zug> zuege = pruefe(augenzahl);

        //TODO Dummy
        return new Zug(-10, -10);
    }

    /**
     * Nimmt eine Menge von Zuegen und entscheidet, welcher Zug gespielt werden
     * soll.
     *
     * @param zuege die zur Auswahl stehenden Zuege
     * @return der Zug, der gezogen werden soll
     */
    abstract protected Zug entscheide(Set<Zug> zuege);
}
