/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start;

import GUI.SpielbrettUI;
import GUI.WuerfelUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rw
 */
public class GUI extends JFrame {

    SpielbrettUI sbrett;

    public GUI() {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int y = (int) screen.getHeight();

        this.setSize(1000, y - 100);
        this.setVisible(true);
        setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

    }

    public void createGUI(int anz) {
        sbrett = new SpielbrettUI();
        sbrett.createSpielfeld(anz, 4);
        JPanel pan= sbrett;
        pan.setBounds(0, 0, sbrett.getWidth(), sbrett.getHeight());
       this.add(pan);
      JPanel pan1= createWürfelPanel();
      pan1.setBounds(0, 600, pan1.getWidth(), pan1.getHeight());
       this.add(pan1);

      //  this.revalidate();
    repaint();
    }

    public JPanel createWürfelPanel() {
        JPanel pan = new JPanel();
        pan.setBorder(BorderFactory.createTitledBorder("Würfel"));
        WuerfelUI wui = new WuerfelUI(0,0,1);
        pan.add(wui);
        return pan;
    }

}
