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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rw
 */
public class GUI extends JFrame {

    SpielbrettUI sbrett;
    JPanel spiel = new JPanel();
    JPanel setting = new JPanel();
    WuerfelUI wui;

    public GUI(int anz) {

        this.setLayout(new BorderLayout());

        spiel.setPreferredSize(new Dimension(1000, 600));
        spiel.setMinimumSize(new Dimension(1000, 600));
        setting.setPreferredSize(new Dimension(200, 200));
        // spiel.setBackground(Color.red);
        this.add(spiel, BorderLayout.PAGE_START);
        setting.setBackground(Color.white);
        setting.setLayout(new BorderLayout());
        this.add(setting, BorderLayout.SOUTH);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int y = (int) screen.getHeight();

        this.setSize(1000, y - 100);
        this.setVisible(true);
        setLocationRelativeTo(null);
        createGUI(anz);
    }

    public void createGUI(int anz) {

        sbrett = new SpielbrettUI(anz, 2);
        sbrett.setVisible(true);
        //  sbrett.createSpielfeld(anz, 2);
        spiel.add(sbrett);
        initSettings();
        //setting.setVisible(true);
        repaint();

    }

    public void initSettings() {

        wui = new WuerfelUI(30, 50, 1);

        setting.add(wui);

        JButton butstart = new JButton("Spiel starten");
        butstart.setBounds(10, 10, 120, 25);
        setting.add(butstart);
        
        JButton butwuerfel = new JButton("wuerfeln");
        butwuerfel.setBounds(150, 10, 120, 25);
        setting.add(butwuerfel);

    }

}
