/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author rw
 */
public class SpielbrettUI extends JPanel implements Observer {

    int _anzahlfelder;

    public SpielbrettUI() {
        this.setSize(1000, 1000);
        this.setLayout(null);
    }

    public void createSpielfeld(int anzfelder) {
        for (int i = 0; i < anzfelder; i++) {
            FeldUI feldarray[] = new FeldUI[anzfelder];
            feldarray[i] = new FeldUI();
            feldarray[i].set

        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
