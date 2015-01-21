/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start;

import GUI.FeldUI;
import GUI.SpielbrettUI;
import GUI.WuerfelUI;
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

    int xoffset = 300;
    JPanel spiel = new JPanel();
    JPanel setting = new JPanel();
    WuerfelUI wui = new WuerfelUI(150 + xoffset, 5, 1);
    FeldUI aktuellerfigur = new FeldUI(390 + xoffset, 0, 1);
    JTextField tx = new JTextField("Bemerkung:");
    int Spieleranz = 2;

    ScrollPane scroll = new ScrollPane();

    JSplitPane pane;

//ArraySpielbrett wert= new ArraySpielbrett();
    public GUI() {
        scroll.add(spiel);
        pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scroll, setting);
        pane.setDividerLocation(600);
        initGUI();
    }

    public void initGUI() {
        this.setLayout(new BorderLayout());

        spiel.setPreferredSize(new Dimension(900, 600));
        //  spiel.setMinimumSize(new Dimension(1000, 600));
        setting.setPreferredSize(new Dimension(200, 200));
        // spiel.setBackground(Color.red);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int y = (int) screen.getHeight();

        this.setSize(1000, y - 100);
        this.setVisible(true);
        setLocationRelativeTo(null);

        initSpiel(20, 2);
        initSettings();
        this.add(pane);
    }

    public void initSpiel(int anz, int spieler) {
        Color bg = new Color(0xffff80);

        sbrett = new SpielbrettUI(anz, spieler, wui, aktuellerfigur,tx);

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

        JButton butwuerfel = new JButton("Naechster Zug");

        JButton butstart = new JButton("Spiel starten");
        butstart.setBounds(5 + xoffset, 0, 120, 25);
        butstart.addActionListener((ActionEvent e) -> {
            sbrett.spielStarten();
            butwuerfel.setEnabled(true);

        });

        tx.setBounds(980 + xoffset, 0, 250, 25);
        tx.setPreferredSize(new Dimension(300, 25));
        butwuerfel.setEnabled(false);
        butwuerfel.setBounds(250 + xoffset, 0, 120, 25);
        butwuerfel.addActionListener((ActionEvent e) -> {

            sbrett.spielFortsetzen();

        });
        aktuellerfigur.setBackground(Color.white);

        setting.add(butstart);
        setting.add(butwuerfel);
        setting.add(wui);
        setting.add(aktuellerfigur);
        setting.add(tx);

        setting.setBackground(Color.white);

    }

}
