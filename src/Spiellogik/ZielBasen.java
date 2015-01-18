/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

/**
 *
 * @author Chris
 */
public class ZielBasen {
    private int[] _zielBasen;
    private int _basisGroesse;
    
    public ZielBasen(int anzahlSpieler, int basisGroesse) {
        _basisGroesse = basisGroesse;
        _zielBasen = new int[anzahlSpieler];
    }
    
    public void zieheInsZiel(int spieler) {
        ++_zielBasen[spieler];
    }
    
    public boolean basisVoll(int spieler) {
        return _zielBasen[spieler] == _basisGroesse;
    }
    
    public int basisBesetzung(int spieler) {
        return _zielBasen[spieler];
    }
    
    @Override
    public String toString() {
        String ausgabe = "Ziel Basen: \n";
        for (int spielerIndex = 0; spielerIndex < _zielBasen.length; ++spielerIndex) {
            ausgabe += "Spieler " + spielerIndex + ": " + _zielBasen[spielerIndex] + " ";
        }
        return ausgabe;
    }
}
