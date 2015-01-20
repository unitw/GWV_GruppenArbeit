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

    public FeldUI feld1;
    public FeldUI feld2;
    public FeldUI feld3;
    public FeldUI feld4;
    public String farbe;

    public Homebase(String farbe) {
        this.setLayout(null);
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

    public String getFarbe() {
        return this.farbe;
    }

    public FeldUI getFields(int i) {
        FeldUI retfield = null;
        switch (i) {
            case 0:
                retfield = feld1;

            case 1:
                retfield = feld2;

            case 2:
                retfield = feld3;

            case 3:
                retfield = feld4;

        }
        return retfield;
    }

    public void setFarbe(String f) {
        this.farbe = f;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public int getIndex() {

        return index;
    }

}
