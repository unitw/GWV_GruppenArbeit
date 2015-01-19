/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Spiellogik.DummySpielbrett;
import Spiellogik.Mensch;
import Spiellogik.Spiel;
import Spiellogik.Spielbrett;
import Spiellogik.Spieler;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author rw
 */
public class SpielbrettUI extends JPanel implements Observer {

    int _anzahlfelder;
    int xbrett = 220;
    int ybrett = 250;
    Color bg = new Color(0xffff80);
    Color[] rgb = new Color[4];

    Spiel _spiel;
    Spielbrett _brett;
    Spieler[] _spieler;
    Homebase[] base;

    public SpielbrettUI(int anz, int sp) {
        this.setSize(1000, 600);
        this.setLayout(null);
        setBackground(bg);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        rgb[0] = new Color(0x1289f8);
        rgb[1] = new Color(0xff0000);
        rgb[2] = new Color(0xfcff00);
        rgb[3] = new Color(0x12ff00);

        _spieler = new Spieler[2];
        _spieler[0] = new Mensch();
        _spieler[1] = new Mensch();
        _brett = new DummySpielbrett(_spieler.length);
        _spiel = new Spiel(_spieler, _brett);
        _spiel.addObserver(this);
        createSpielfeld(anz, sp);

    }

    public void createSpielfeld(int anzfelder, int spieler) {
        FeldUI feldarray[] = new FeldUI[anzfelder];

        int startfelder = anzfelder / spieler;

        double Winkel = (Math.PI * 2.0) / anzfelder;
        double RadiusX = 10 * anzfelder + 61;
        double RadiusY = 10 * anzfelder + 61;
        double StartX = 450;
        double StartY = 270;
        int a = 0;
        for (int i = 0; i < anzfelder; i++) {

            double MidPosX = (Math.cos(Winkel * i) * RadiusX) + StartX;
            double MidPosY = (Math.sin(Winkel * i) * RadiusY) + StartY;

            feldarray[i] = new FeldUI((int) MidPosX, (int) MidPosY, i + 1);

            if (feldarray[i].getidx() % startfelder == 0) {

                Color[] rgb1 = new Color[4];
                rgb1[0] = new Color(0x1289f8);
                rgb1[1] = new Color(0xff0000);
                rgb1[2] = new Color(0xfcff00);
                rgb1[3] = new Color(0x12ff00);
                Color col = rgb1[a];
                feldarray[i].setBackground(col);
                feldarray[i].setOpaque(true);
                feldarray[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                a = a + 1;

            }

            this.add(feldarray[i]);

        }

        createHomerBase(spieler);

    }

    public void createHomerBase(int Anzahlbasen) {
        String[] farbennamen = new String[4];
        farbennamen[0] = "blau";
        farbennamen[1] = "rot";
        farbennamen[2] = "grün";
        farbennamen[3] = "gelb";

        for (int i = 0; i < Anzahlbasen; i++) {
            base = new Homebase[Anzahlbasen];
            base[i] = new Homebase(farbennamen[i]);
            base[i].setIndex(i);

            switch (base[i].getIndex()) {
                case 0:
                    base[i].setBounds(10, 5, base[i].getWidth(), base[i].getHeight());
                    base[i].setBackground(rgb[0]);
                    break;
                case 1:
                    base[i].setBounds(this.getWidth() - 135, 5, base[i].getWidth(), base[i].getHeight());
                    base[i].setBackground(rgb[1]);

                    break;
                case 2:
                    base[i].setBounds(10, this.getHeight() - 135, base[i].getWidth(), base[i].getHeight());
                    base[i].setBackground(rgb[2]);

                    break;
                case 3:
                    base[i].setBounds(this.getWidth() - 135, this.getHeight() - 135, base[i].getWidth(), base[i].getHeight());
                    base[i].setBackground(rgb[3]);

                    break;

            }

            this.add(base[i]);

        }

    }

    public void spielStarten() {
        FigurUI[] figuren = new FigurUI[99];
        for (int i = 0; i < base.length; i++) {
            Homebase hb = base[i];
            for (int k = 0; k < base[i].getComponentCount(); k++) {
                FeldUI fui = (FeldUI) base[i].getComponent(k);
                int x = fui.getX();
                int y = fui.getY();
                String farbe = base[i].getFarbe();
                figuren[i] = new FigurUI(farbe, x + 5, y + 5);
                //     figuren[i].setVisible(true);
                this.add(figuren[i]);

            }

        }

    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
}
