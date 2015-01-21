/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author rw
 */
public class FeldUI extends JLabel implements Setzen {

    ImageIcon feldpic = new ImageIcon(getClass().getResource("/resources/Bilder/feldbr.png"));
    ImageIcon figurpicblausetstart = new ImageIcon(getClass().getResource("/resources/Bilder/blaueshuetchenset.png"));
    ImageIcon figurpicrotsetstart = new ImageIcon(getClass().getResource("/resources/Bilder/roteshuetchenset.png"));
    ImageIcon figurpicblauset = new ImageIcon(getClass().getResource("/resources/Bilder/blaueshuetchenfeld.png"));
    ImageIcon figurpicrotset = new ImageIcon(getClass().getResource("/resources/Bilder/roteshuetchenfeld.png"));

    int xpos;
    int ypos;
    int breite = 60;
    int hoehe = 60;
    int idx;
    int gesetzt = -1;
    Color bg = new Color(0xffff80);

    public FeldUI(int x, int y, int index) {
        this.setLayout(null);
        // this.add(new JLabel(feldpic));
        this.xpos = x;
        this.ypos = y;
        this.idx = index;
        this.setIcon(feldpic);
        this.setBounds(x, y, breite, hoehe);
        this.setBackground(bg);

//        this.addMouseListener(new MouseListener() {
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
////                Toolkit toolkit = Toolkit.getDefaultToolkit();
////                Image image = toolkit.getImage(getClass().getResource("/resources/Bilder/blaueshütchen.png"));
////                Cursor c = toolkit.createCustomCursor(image, new Point(getParent().getX(),
////                        getParent().getY()), "img");
////                setCursor(c);
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                setCursor(Cursor.getDefaultCursor());
//            }
//        });
//       
    }

    @Override
    public void setStartFigur(Boolean b, int Spieler) {

        if (b) {
            switch (Spieler) {
                case -1:
                    this.setIcon(feldpic);
                    gesetzt = -1;

                    break;
                case 0:
                    this.setIcon(figurpicblausetstart);
                    gesetzt = 0;

                    break;
                case 1:
                    this.setIcon(figurpicrotsetstart);
                    gesetzt = 1;
                    break;
            }

        }
    }

    @Override
    public void setFigur(Boolean b, int Spieler) {
        if (b) {
            switch (Spieler) {
                case -1:
                    this.setIcon(feldpic);
                    this.setOpaque(true);
                    //this.setBackground(bg);
                    gesetzt = -1;
                    break;
                case 0:
                    this.setIcon(figurpicblauset);
                    gesetzt = 0;
                   // this.setBackground(bg);

                    this.setOpaque(true);

                    break;
                case 1:
                    this.setIcon(figurpicrotset);
                    gesetzt = 1;
                    //this.setBackground(bg);

                    this.setOpaque(true);

                    break;
            }

        }
    }

    public void clearFeld() {

        this.setIcon(feldpic);
    }

    public int getx() {
        return xpos;
    }

    public void setx(int x) {
        this.xpos = x;
    }

    public int gety() {
        return ypos;
    }

    public void sety(int y) {
        this.ypos = y;
    }

    public void setidx(int i) {
        this.idx = i;
    }

    public int getidx() {
        return idx;
    }

}
