/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleUI;

import Spiellogik.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 *
 * @author Chris
 */
public class ConsoleUI implements Observer {

    Spiel _spiel;
    Spielbrett _brett;
    Spieler[] _spieler;

    public ConsoleUI() {
        _spieler = new Spieler[2];
        _spieler[0] = new Mensch();
        _spieler[1] = new Mensch();
        _brett = new DummySpielbrett(_spieler.length);
        _spiel = new Spiel(_spieler, _brett);
        _spiel.addObserver(this);
        spielStarten();
    }

    private void spielStarten() {
        System.out.println("Spiel wird gestartet... \n");
        String ausgabe = "Spieler: ";
        for (int index = 0; index < _spieler.length; ++index) {
            ausgabe += index + " (" + _spieler[index].getClass().toString() + ")";
        }
        System.out.println(ausgabe);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exc) {

        }
        spielFortsetzen();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == _spiel) {
            if (arg instanceof Mensch) {
                spielen();
            } else {
                spielstandAnzeigen();
                warte();
            }
            spielFortsetzen();
        }
    }

    private void spielFortsetzen() {

        _spiel.spielFortfahren();
    }

    private void spielen() {
        spielstandAnzeigen();
        List<Zug> zuege = _spiel.getMoeglicheZuege();

        int i = 0;
        for (Zug zug : zuege) {
            System.out.println("Zug " + i + zug.toString());
            ++i;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Zum ziehen Zug-Index angeben: ");
        String zugIndexString = scanner.nextLine();
        int zugIndex = Integer.valueOf(zugIndexString);

        _spiel.ziehe(zuege.get(zugIndex));
    }

    private void spielstandAnzeigen() {
        System.out.println(_spiel.toString());
    }
    
    private void warte() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
