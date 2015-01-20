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
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

/**
 *
 * @author rw
 */
public class GUI extends JFrame {

    SpielbrettUI sbrett;
    JPanel spiel = new JPanel();
    JPanel setting = new JPanel();
    WuerfelUI  wui = new WuerfelUI(7, 26, 1);
    int Spieleranz = 2;
    JSplitPane pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, spiel, setting);
//ArraySpielbrett wert= new ArraySpielbrett();
    public GUI() {
        // setanz();
        initGUI();
    }

    public void initGUI() {
         this.setLayout(new BorderLayout());

        spiel.setPreferredSize(new Dimension(1000, 600));
        //  spiel.setMinimumSize(new Dimension(1000, 600));
        setting.setPreferredSize(new Dimension(200, 200));
        // spiel.setBackground(Color.red);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int y = (int) screen.getHeight();

        this.setSize(1000, y - 100);
        this.setVisible(true);
        setLocationRelativeTo(null);

<<<<<<< HEAD
        initSpiel(20, 2);
=======
        initSpiel(spielbrett._spielfeldGroesse, Spieleranz, spielbrett);
>>>>>>> FETCH_HEAD
        initSettings();
        this.add(pane);
    }

    public void initSpiel(int anz, int spieler) {
        Color bg = new Color(0xffff80);

        sbrett = new SpielbrettUI(anz, spieler,wui);

        spiel.add(sbrett);
        spiel.setBackground(bg);
       // this.add(spiel, BorderLayout.NORTH);

    }

    public void setanz() {

        JDialog dia = new JDialog();
        dia.setTitle("SpielerAnzahl");
        JTextField tx = new JTextField("");
        tx.setEditable(true);
        tx.setBounds(0, 0, 100, 25);
        dia.add(tx);
        JButton bu = new JButton("OK");

        bu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Spieleranz = Integer.parseInt(tx.getText());
                dia.dispose();
                initGUI();

            }
        });
        bu.setBounds(0, 30, 50, 25);
        dia.add(bu);
        dia.setSize(100, 100);

        dia.setVisible(true);
    }

    public void initSettings() {

       

        setting.add(wui);

        JButton butstart = new JButton("Spiel starten");
        butstart.setBounds(5, 0, 120, 25);
        butstart.addActionListener((ActionEvent e) -> {
            sbrett.spielStarten();

        });
        setting.add(butstart);

        JButton butwuerfel = new JButton("wuerfeln");
        butwuerfel.setBounds(130, 0, 120, 25);
        setting.add(butwuerfel);
        setting.setBackground(Color.white);
        setting.setLayout(new BorderLayout());
        //this.add(setting, BorderLayout.SOUTH);
    }

}
