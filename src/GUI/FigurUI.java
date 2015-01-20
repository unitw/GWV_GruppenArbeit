/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author rw
 */
public class FigurUI extends JLabel {

    ImageIcon figurpicblau = new ImageIcon(getClass().getResource("/resources/Bilder/blaueshuetchen.png"));
    ImageIcon figurpicrot = new ImageIcon(getClass().getResource("/resources/Bilder/roteshuetchen.png"));
    int hoehe = 24;
    int breite = 50;
    int xpos;
    int ypos;
    int idx;

    public FigurUI(String s, int x, int y) {
       this.setLayout(null);
        switch(s){
       case"rot":
            this.setIcon(figurpicrot);
       break;
       case "blau":
            this.setIcon(figurpicblau);
           break;
       default:
          System.err.println("Keine figur der Farbe enthalten");
       }
       
       
        this.xpos=x;
        this.ypos=y;
        
        this.setBounds(x, y, hoehe, breite);
    }

    public int getx() {
        return xpos;
    }

    public void setx(int x) {
        this.xpos = x;
    }

    public int gety() {
        return ypos;
    }

    public void sety(int y) {
        this.ypos = y;
    }

    public void setidx(int i) {
        this.idx = i;
    }

    public int getidx() {
        return idx;
    }
}
