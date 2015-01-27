/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Spiellogik.ArraySpielbrett;
import Spiellogik.KI.DecisionNetworkKI;
import Spiellogik.KI.KI;
import Spiellogik.Mensch;
import Spiellogik.Spiel;
import Spiellogik.Spieler;
import Spiellogik.Zug;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
    public Homebase[] homebase;
    public Zielbase[] zielbase;

    ArrayList startFelder = new ArrayList();
    FeldUI aktspieler;
    public FeldUI[] feldarray;
    public WuerfelUI wuerf;
    ArraySpielbrett spielbrett;
    JTextField tx;
    ImageIcon feldpic = new ImageIcon(getClass().getResource("/resources/Bilder/feldbr.png"));
    ImageIcon figurpicblausetstart = new ImageIcon(getClass().getResource("/resources/Bilder/blaueshuetchenset.png"));
    ImageIcon figurpicrotsetstart = new ImageIcon(getClass().getResource("/resources/Bilder/roteshuetchenset.png"));
    ImageIcon figurpicblauset = new ImageIcon(getClass().getResource("/resources/Bilder/blaueshuetchenfeld.png"));
    ImageIcon figurpicrotset = new ImageIcon(getClass().getResource("/resources/Bilder/roteshuetchenfeld.png"));
    int basecountanf;
    int zielbasecntanf;

    public SpielbrettUI(int anz, int sp, WuerfelUI wuerfel, FeldUI aktuellspieler, JTextField tx) {
        this.setSize(1100, 600);
        this.setLayout(null);
        setBackground(bg);

        this.tx = tx;
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
        //_spieler[1] = new Mensch();

        KI ki = new DecisionNetworkKI();
        _spieler[1] = ki;
        ki.setzeSpielerIndex(1);

        // !!!!!!------ WICHTIG -------!!!!!!!!
        // Konstruktor Spiel(Spieler[], Spielbrett, SpielbrettUI) entfernt, 
        // das Spielfeld braucht die UI nicht zu kennen. Falls das Probleme bereitet,
        // bitte mir Bescheid sagen (Christian)
        _spiel = new Spiel(_spieler, _brett);
        ki.setzeSpielbrett(_brett);

    }

    public void createSpielfeld(int anzfelder, int spieler) {
        feldarray = new FeldUI[anzfelder];

        int nichtstartfelder = anzfelder / spieler;

        double Winkel = (Math.PI * 2.0) / anzfelder;
        double RadiusX = 10 * anzfelder + 61;
        double RadiusY = 10 * anzfelder + 61;
        double StartX = 520;
        double StartY = 260;
        int a = 0;
        for (int i = 0; i < anzfelder; i++) {

            double MidPosX = (Math.cos(Winkel * i) * RadiusX) + StartX;
            double MidPosY = (Math.sin(Winkel * i) * RadiusY) + StartY;

            feldarray[i] = new FeldUI((int) MidPosX, (int) MidPosY, i);

            if (feldarray[i].getidx() % nichtstartfelder == 0) {

                startFelder.add(feldarray[i]);

                Color[] rgb1 = new Color[4];
                rgb1[1] = new Color(0x1289f8);
                rgb1[0] = new Color(0xff0000);
                rgb1[2] = new Color(0xfcff00);
                rgb1[3] = new Color(0x12ff00);
                Color col = rgb1[a];
                feldarray[i].setBackground(col);
                feldarray[i].setStartFeld(col);

                feldarray[i].setOpaque(true);
                feldarray[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                a = a + 1;

            }

            this.add(feldarray[i]);

        }

        createHomerBase(spieler);
        createZielbase(spieler);

    }

    public void createZielbase(int Anzahl) {
        int[] spieler = new int[4];
        spieler[0] = 0;
        spieler[1] = 1;
        spieler[2] = 2;
        spieler[3] = 3;

        zielbase = new Zielbase[Anzahl];
        for (int i = Anzahl - 1; i >= 0; i--) {

            FeldUI fstartui = (FeldUI) startFelder.get(i);

            zielbase[i] = new Zielbase(spieler[i]);
            zielbase[i].setIndex(i);
            int spielera = zielbase[i].getSpieler();

            if (spielera == 0) {
                spielera = 1;
            } else {
                spielera = 0;
            }
            switch (spielera) {
                case 0:
                    zielbase[i].setBounds(fstartui.xpos + 583, fstartui.ypos, zielbase[i].getWidth(), zielbase[i].getHeight());
                    zielbase[i].setBackground(rgb[1]);
                    break;
                case 1:
                    zielbase[i].setBounds(fstartui.xpos - 768, fstartui.ypos, zielbase[i].getWidth(), zielbase[i].getHeight());
                    zielbase[i].setBackground(rgb[0]);
                    break;

            }
            this.add(zielbase[i]);
        }

    }

    public void createHomerBase(int Anzahlbasen) {
        int[] spieler = new int[4];
        spieler[0] = 0;
        spieler[1] = 1;
        spieler[2] = 2;
        spieler[3] = 3;
        homebase = new Homebase[Anzahlbasen];
        for (int i = 0; i < Anzahlbasen; i++) {

            homebase[i] = new Homebase(spieler[i]);
            homebase[i].setIndex(i);

            switch (homebase[i].getSpieler()) {
                case 0:
                    homebase[i].setBounds(10, 5, homebase[i].getWidth(), homebase[i].getHeight());
                    homebase[i].setBackground(rgb[0]);
                    break;
                case 1:
                    homebase[i].setBounds(this.getWidth() - 135, 5, homebase[i].getWidth(), homebase[i].getHeight());
                    homebase[i].setBackground(rgb[1]);

                    break;
                case 2:
                    homebase[i].setBounds(10, this.getHeight() - 135, homebase[i].getWidth(), homebase[i].getHeight());
                    homebase[i].setBackground(rgb[2]);

                    break;
                case 3:
                    homebase[i].setBounds(this.getWidth() - 135, this.getHeight() - 135, homebase[i].getWidth(), homebase[i].getHeight());
                    homebase[i].setBackground(rgb[3]);

                    break;

            }

            this.add(homebase[i]);

        }

    }

    public void spielStarten() {

        createSpielfeld(20, 2);
        FigurUI[] figuren = new FigurUI[99];

        for (Homebase hb : homebase) {
            for (int k = 0; k < hb.getComponentCount(); k++) {
                if (hb.getComponent(k) instanceof FeldUI) {
                    FeldUI fui = (FeldUI) hb.getComponent(k);
                    int spieler = hb.getSpieler();
                    fui.setStartFigur(true, spieler);
                }
            }
        }
        basecountanf = _brett._basen.basisBesetzung(_spiel.getAktuellerSpielerIndex());
        zielbasecntanf = _brett.getZielBasen().basisBesetzung(_spiel.getAktuellerSpielerIndex());

    }

    public void spielFortsetzen() {
        if (!_spiel.spielZuEnde()) {
            int spielercnt = _spiel.getAktuellerSpielerIndex();

            _spiel.spielFortfahren();
            if (_spiel.getAktuelleAugenzahl() == 6) {
                System.out.print("");

            }

            if (_spiel.getAktuellerSpielerIndex() == 0 && _spiel.getAktuellerSpieler() instanceof Mensch) {
                zieheMitMensch();
            }
            if (_spiel.getAktuellerSpielerIndex() == 1 && _spiel.getAktuellerSpieler() instanceof KI) {
                _spiel.zieheKI();
            }

            this.aktspieler.setFigur(true, spielercnt);
            wuerf.setWuerfel(_spiel.getAktuelleAugenzahl());

            int basecount = _brett._basen.basisBesetzung(_spiel.getAktuellerSpielerIndex());

            if (basecount < basecountanf) {
                if (basecount < 4) {
                    for (Homebase base1 : homebase) {
                        if (base1.getSpieler() == spielercnt) {
                            base1.refreshbase(spielercnt, basecount);
                        }
                    }
                }
                basecountanf = basecount;
            }

            for (int i = 0; i < _brett._spielfeld.length; i++) {
                int feldbelegung = _brett._spielfeld[i];
                feldarray[i].setFigur(true, feldbelegung);

            }

            int zielbasecnt = _brett.getZielBasen().basisBesetzung(_spiel.getAktuellerSpielerIndex());

            if (zielbasecnt < zielbasecntanf) {
                if (zielbasecnt < 4) {
                    for (Zielbase zielbase1 : zielbase) {
                        if (zielbase1.getSpieler() == spielercnt) {
                            zielbase1.refreshbase(spielercnt, zielbasecnt - 1);
                        }
                    }
                }
                zielbasecntanf = zielbasecnt;
            }
        } else {

            int Gewinner = -1;
            for (int i = 0; i < _spieler.length; ++i) {
                if (_brett.alleImZiel(i)) {
                    Gewinner = i;
                }
            }

            JOptionPane.showMessageDialog(new JFrame(),
                    "Spiel zu Ende:" + "Spieler" + " " + Gewinner + " " + "hat gewonnen");

        }

    }

    private void zieheMitMensch() {

        List<Zug> zuege = _spiel.getMoeglicheZuege();

        if (!zuege.isEmpty()) {
            if (zuege.size() == 1) {

                tx.setText("Nur ein Zug moeglich. Es wird automatisch gezogen.");
                _spiel.ziehe(zuege.get(0));

            } else {
                int i = 0;
                for (Zug zug : zuege) {
                    tx.setText("Zug " + i + zug.toString());
                    ++i;
                }

                tx.setText("Mehere Zuege moeglich! Figur waehlen ");

                int zugIndex = zug(_spiel.getMoeglicheZuege());

                _spiel.ziehe(zuege.get(zugIndex));
            }
        } else {
            tx.setText("Kein Zug moeglich.");
        }
    }

    public int zug(List<Zug> zuge) {

        zuge.stream().forEach((zug) -> {
            zug.getAusgangsPos();
        });

        tx.setText("Bitte Figur Auswaehlen");
        Handler();
        return 1;
    }

    public int Handler() {
        int zugindx = -1;
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() instanceof FeldUI) {
                    FeldUI fuim = (FeldUI) e.getSource();
                    if (fuim.getIcon() == figurpicblauset || fuim.getIcon() == figurpicrotset) {
                        tx.setText("0,1");
                    }

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        return zugindx;
    }

}
