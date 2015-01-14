/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author rw
 */
public class FeldUI extends JLabel {
    ImageIcon feldpic = new ImageIcon("resources/Bilder/feldbr.png");
   
    int xpos;
    int ypos;
    int breite=70;
    int hoehe=70;
    
    public FeldUI(int x,int y){
      this.xpos=x;
      this.ypos=y;
        
      this.setBounds(x, y, breite, hoehe);
       this.setIcon(feldpic);
   }

}
