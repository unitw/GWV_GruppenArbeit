/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik.KI;

import Spiellogik.Spiel;
import Spiellogik.Spielbrett;
import Spiellogik.Zug;
import java.util.List;

/**
 * Diese Klasse beschreibt einen Algorithmus, der auf einem gegebenen Spielbrett
 * für das Spiel Mensch Ärgere Dich Nicht für einen Spieler den erwarteten
 * Nutzen eines bestimmten Zuges berechnen kann. Somit können verschiedene
 * mögliche Züge verglichen werden und der bestmögliche ausgewählt werden.
 *
 * @author Chris
 */
public class ExpectedUtility {

    private static final int anzahlSimulationsRunden = 1;

    private Spielbrett original;
    private int spielerIdx;
    private int spielerAnzahl;

    /**
     * Erstellt ein neues ExpectedUtility Objekt, das auf dem übergebenen
     * Spielbrett arbeitet. Die expectded Utility wird jeweils für den
     * übergebenen Spieler-Index berechnet.
     *
     * @param spbrett Das Brett auf dem simuliert wird
     * @param spielerIdx Der Index des Spielers für den simuliert wird
     */
    public ExpectedUtility(Spielbrett spbrett, int spielerIdx) {

        this.original = spbrett;
        this.spielerIdx = spielerIdx;
        this.spielerAnzahl = original.getAnzSpieler();
    }

    /**
     * Berechnet den Erwartungwert für den übergebenen Zug. Der Erwartungswert
     * beschreibt die Aussicht des gegebenen Zuges den Spieler dem Sieg näher zu
     * bringen. Zur Berechnung werden Züge simuliert. Am Ende dieser
     * Simulationen, werden die Spielausgänge anhand von verschiedenen Kriterien
     * bewertet und miteinander verrechnet. Die Kriterien sind: Die Position des
     * Spielers und der Gegner, ob man Gegner schlägt oder geschlagen wird und
     * die Position der im übergebenen Zug gezogenen Figur.
     *
     * @param zug Der Zug für den die Expected Utility berechnet werden soll
     * @return Der Erwartungswert (Expected Utility) für den übergebenen Zug
     */
    public double berechneExpectedUtility(Zug zug) {
        Spielbrett simulation = original.clone();
        simulation.setze(spielerIdx, zug); // TODO Überprüfen, ob hier gezogen werden soll.
        int spielerPos = zug.getZielPos(); // Position lediglich bezogen auf den Zug
        int naechsterSpieler = (spielerIdx + 1) % simulation.getAnzSpieler();
        int weitereSpieler = anzahlSimulationsRunden * simulation.getAnzSpieler() - 1;
        return spielerPos + berechneUtility(simulation, weitereSpieler, naechsterSpieler);
    }

    private double berechneUtility(Spielbrett brett, int weitereSpieler, int aktuellerSpieler) {

        if (spielerAnzahl > 2) {
            System.out.println("");
        }

        double utility = 0;
        if (weitereSpieler == 0) {
            int gegnerPos = 0;
            int spielerPosGesamt = 0; // Die Indices aller Figuren der KI aufaddiert.
            int[] position = brett.getSpielerPositionen();
            for (int i = 0; i < spielerAnzahl; ++i) {
                if (i != spielerIdx) {
                    gegnerPos += position[i]; // TODO Parameter Tweaking
                } else {
                    spielerPosGesamt = position[i];
                }
            }
            utility -= gegnerPos;
            utility += spielerPosGesamt;
        } else {

            for (int augenzahl = 1; augenzahl <= Spiel.WUERFELGROESSE; ++augenzahl) {
                // TODO Auf den Sonderfall Augenzahl = 6 reagieren. Wird momentan nicht beachtet.
                List<Zug> zuege = brett.pruefe(aktuellerSpieler, augenzahl);
                for (Zug zug : zuege) {
                    if (brett.schlaegt(zug)) {
                        if (aktuellerSpieler == spielerIdx) {
                            utility += 1.0 / Spiel.WUERFELGROESSE * 1.0 / zuege.size() * (zug.getZielPos()); // TDOD Parameter Tweaking
                        } else if (brett.istSpielerFeld(spielerIdx, zug.getZielPos())) {
                            utility -= 1.0 / Spiel.WUERFELGROESSE * 1.0 / zuege.size() * (zug.getZielPos());
                        }
                    }

                    Spielbrett neuesBrett = brett.clone();
                    neuesBrett.setze(aktuellerSpieler, zug);
                    int naechsterSpieler = (aktuellerSpieler + 1) % spielerAnzahl;

                    utility += 1.0 / Spiel.WUERFELGROESSE * 1.0 / zuege.size() * (berechneUtility(neuesBrett, weitereSpieler - 1, naechsterSpieler));
                    // TODO Berechnung überprüfen (1/Wuerfelgroesse * 1/zuege.size()???)
                }
            }
        }
        return utility;
    }
}
