/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

import java.util.List;



/**
 *
 * @author Chris
 */
public interface Spielbrett {
    
    /**
     * Zieht den angegebenen Zug mit dem Spieler der aktuell an der Reihe ist.
     *
     * @param zug Der durchzufuehrende Zug
     */
    public void setze(int spieler, Zug zug);
    
    public boolean istSpielerFeld(int spieler, int feldIndex);
    
    public int spielfeldGroesse();
    
    public List<Zug> pruefe(int spieler, int augenzahl);
    
    public int[] getSpielfeld();
    
    public boolean alleImZiel(int spieler);

    public HeimBasen getHeimBasen();
}
