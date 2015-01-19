/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start;

import GUI.SpielbrettUI;
import GUI.WuerfelUI;
import Spiellogik.ArraySpielbrett;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author rw
 */
public class GUI extends JFrame {

    SpielbrettUI sbrett;
    JPanel spiel = new JPanel();
    JPanel setting = new JPanel();
    WuerfelUI wui;
    int feldanz;
    ArraySpielbrett spielbrett;//nachher durch einen JDialog initialisiert

    public GUI() {
        setanz();
        spielbrett = new ArraySpielbrett(feldanz);
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

        createGUI(spielbrett.spielfeldgroesse);
    }

    public void createGUI(int anz) {

        sbrett = new SpielbrettUI(anz, 4);
        sbrett.setVisible(true);
        //  sbrett.createSpielfeld(anz, 2);
        spiel.add(sbrett);
        initSettings();
        //setting.setVisible(true);
        repaint();

    }

    public void setanz() {

        JDialog dia = new JDialog();
        JTextField tx = new JTextField("");
        tx.setEditable(true);
        tx.setBounds(0, 0,100,25);
        dia.add(tx);
        JButton bu = new JButton("OK");
        
        bu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                feldanz = Integer.parseInt(tx.getText());
                dia.dispose();
            }
        });
        bu.setBounds(0, 30, 50, 25);
       dia.add(bu);
       dia.setSize(100, 1000);
       
        dia.setVisible(true);
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
