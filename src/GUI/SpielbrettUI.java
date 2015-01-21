/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Spiellogik.ArraySpielbrett;
import Spiellogik.Mensch;
import Spiellogik.Spiel;
import Spiellogik.Spielbrett;
import Spiellogik.Spieler;
import Spiellogik.Zug;
import java.awt.Color;
import java.awt.Panel;
import java.util.List;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 *
 * @author rw
 */
public class SpielbrettUI extends Panel {

    int _anzahlfelder;
    int xbrett = 220;
    int ybrett = 250;
    Color bg = new Color(0xffff80);
    Color[] rgb = new Color[4];

    Spiel _spiel;
    ArraySpielbrett _brett;
    Spieler[] _spieler;
    public Homebase[] base;
    FeldUI aktspieler;
    public FeldUI[] feldarray;
    public WuerfelUI wuerf;
    ArraySpielbrett spielbrett;

    public SpielbrettUI(int anz, int sp, WuerfelUI wuerfel, FeldUI aktuellspieler) {
        this.setSize(1000, 600);
        this.setLayout(null);
        setBackground(bg);
        this.aktspieler = aktuellspieler;
        this.wuerf = wuerfel;
        rgb[0] = new Color(0x1289f8);
        rgb[1] = new Color(0xff0000);
        rgb[2] = new Color(0xfcff00);
        rgb[3] = new Color(0x12ff00);
        _brett = new ArraySpielbrett(sp);
        createSpielfeld(anz, sp);
        _spieler = new Spieler[2];
        _spieler[0] = new Mensch();
        _spieler[1] = new Mensch();
        // !!!!!!------ WICHTIG -------!!!!!!!!
        // Konstruktor Spiel(Spieler[], Spielbrett, SpielbrettUI) entfernt, 
        // das Spielfeld braucht die UI nicht zu kennen. Falls das Probleme bereitet,
        // bitte mir Bescheid sagen (Christian)
        _spiel = new Spiel(_spieler, _brett);

    }

    public void createSpielfeld(int anzfelder, int spieler) {
        feldarray = new FeldUI[anzfelder];

        int nichtstartfelder = anzfelder / spieler;

        double Winkel = (Math.PI * 2.0) / anzfelder;
        double RadiusX = 10 * anzfelder + 61;
        double RadiusY = 10 * anzfelder + 61;
        double StartX = 450;
        double StartY = 270;
        int a = 0;
        for (int i = 0; i < anzfelder; i++) {

            double MidPosX = (Math.cos(Winkel * i) * RadiusX) + StartX;
            double MidPosY = (Math.sin(Winkel * i) * RadiusY) + StartY;

            feldarray[i] = new FeldUI((int) MidPosX, (int) MidPosY, i);

            if (feldarray[i].getidx() % nichtstartfelder == 0) {

                Color[] rgb1 = new Color[4];
                rgb1[1] = new Color(0x1289f8);
                rgb1[0] = new Color(0xff0000);
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
        int[] spieler = new int[4];
        spieler[0] = 0;
        spieler[1] = 1;
        spieler[2] = 2;
        spieler[3] = 3;
        base = new Homebase[Anzahlbasen];
        for (int i = 0; i < Anzahlbasen; i++) {

            base[i] = new Homebase(spieler[i]);
            base[i].setIndex(i);

            switch (base[i].getSpieler()) {
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

        createSpielfeld(20, 2);
        FigurUI[] figuren = new FigurUI[99];

        for (Homebase hb : base) {
            for (int k = 0; k < hb.getComponentCount(); k++) {
                if (hb.getComponent(k) instanceof FeldUI) {
                    FeldUI fui = (FeldUI) hb.getComponent(k);
                    int spieler = hb.getSpieler();
                    fui.setStartFigur(true, spieler);
                }
            }
        }

    }

    public void spielFortsetzen() {
        _spiel.spielFortfahren();
        zieheMitMensch();

        int spielercnt = _spiel.getAktuellerSpielerIndex();
        this.aktspieler.setFigur(true, spielercnt);

        wuerf.setWuerfel(_spiel.getAktuelleAugenzahl());
        int basecount = _brett._basen.basisBesetzung(_spiel.getAktuellerSpielerIndex());
        if (basecount < 4) {
            for (Homebase base1 : base) {
                if(base1.getSpieler()==spielercnt)
                base1.refreshbase(spielercnt, basecount);
            }
        }
        for (int i = 0; i < _brett._spielfeld.length; i++) {
            int feldbelegung = _brett._spielfeld[i];
            feldarray[i].setFigur(true, feldbelegung);

        }
    }

    private void zieheMitMensch() {
        int spoffset = 0;
        List<Zug> zuege = _spiel.getMoeglicheZuege();
        if (_spiel.getAktuellerSpielerIndex() == 0) {
            spoffset = 0;
        }

        if (!zuege.isEmpty()) {
            if (zuege.size() == 1) {

                System.out.println("Nur ein Zug moeglich. Es wird automatisch gezogen.");
                _spiel.ziehe(zuege.get(0 + spoffset));

            } else {
                int i = 0;
                for (Zug zug : zuege) {
                    System.out.println("Zug " + i + zug.toString());
                    ++i;
                }
                Scanner scanner = new Scanner(System.in);
                System.out.print("Zum ziehen Zug-Index angeben: ");
                String zugIndexString = scanner.nextLine();

                int zugIndex = Integer.valueOf(zugIndexString + spoffset);

                _spiel.ziehe(zuege.get(zugIndex));
            }
        } else {
            System.out.println("Kein Zug moeglich.");
        }
    }

}
