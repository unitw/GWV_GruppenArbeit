/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

/**
 *Objekte dieser Klasse repraesentieren einen Zug. 
 * Es sind jeweils der Ausgangs- und der Zielpunkt spezifiziert.
 * -1 steht fuer die Homebase.
 * @author Chris
 */
public class Zug {
    
    private final int _ausgangsPos;
    private final int _zielPos;
    
    public Zug(int ausgangsIndex, int zielIndex)
    {
        _ausgangsPos = ausgangsIndex;
        _zielPos = zielIndex;
    }
    
    public int getAusgangsPos() {
        return _ausgangsPos;
    }

    public int getZielPos() {
        return _zielPos;
    }
}
