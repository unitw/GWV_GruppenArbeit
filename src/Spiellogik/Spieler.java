/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;


import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Chris
 */
abstract class Spieler {
    
    private Spielbrett _brett;
    private int _index;
    
    Spieler(Spielbrett brett, int index) {
        _brett = brett;
        _index = index;
    }
    
    public Set<Zug> pruefe(int augenzahl) {
       return _brett.pruefe(_index, augenzahl);
    }
}
