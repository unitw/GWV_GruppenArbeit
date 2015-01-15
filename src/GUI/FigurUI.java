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

   ImageIcon figurpicrot = new ImageIcon("resources/Bilder/blaueshütchen.png");
 ImageIcon figurpicblau = new ImageIcon("resources/Bilder/roteshütchen.png");
 
    public FigurUI(String s) {
        if (s.equals("ROT")) {
            this.setIcon(figurpicrot);
        }
        else{
           this.setIcon(figurpicblau);
         
        }
    }

}
