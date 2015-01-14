/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

import java.util.Observable;

/**
 *
 * @author rw
 */
public class Spiel extends Observable {
    
    public static final int DEFAULT_ANZAHL_SPIELER = 2;
    
    private final Spielbrett _spielbrett;
    private final Spieler[] _spieler;
    private int _anDerReihe;
    
    public Spiel(Spieler[] spieler, Spielbrett spielbrett) {
        _spieler = spieler;
        _spielbrett = spielbrett;
    }
    
    public void spieleMitAktuellemSpieler()
    {
        Spieler aktuellerSpieler = _spieler[_anDerReihe];
        
        //TODO Spieler muss ziehen
        
        naechsterSpieler();
    }
    
    private void naechsterSpieler() {
       _anDerReihe = (_anDerReihe + 1) % _spieler.length;
    }
}
