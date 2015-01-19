/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author rw
 */
public class Homebase extends JPanel {

    int index;

    private FeldUI feld1;
    private FeldUI feld2;
    private FeldUI feld3;
    private FeldUI feld4;
    private String farbe;

    public Homebase(String farbe) {
        feld1 = new FeldUI(0, 0, 1);
        feld2 = new FeldUI(61, 0, 2);
        feld3 = new FeldUI(0, 61, 3);
        feld4 = new FeldUI(61, 61, 4);
        this.farbe = farbe;
        this.add(feld1);
        this.add(feld2);
        this.add(feld3);
        this.add(feld4);
        this.setSize(122, 122);

        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }
public String getFarbe(){
    return this.farbe;
}
public void setFarbe(String f){
    this.farbe=f;
}
    public void setIndex(int i) {
        this.index = i;
    }

    public int getIndex() {

        return index;
    }

}
