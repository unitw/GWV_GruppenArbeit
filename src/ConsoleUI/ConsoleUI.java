/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleUI;

import Spiellogik.KI.RandomKI;
import Spiellogik.KI.KI;
import Spiellogik.*;
import Spiellogik.KI.DecisionNetworkKI;
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
<<<<<<< HEAD
        setup2KI();
     //  setup2KI();
        
        
=======

        setup1Mensch1KI();
>>>>>>> FETCH_HEAD
        _spiel.addObserver(this);
        spielStarten();
    }

    private void setup2Mensch() {
        _spieler = new Spieler[2];
        _spieler[0] = new Mensch();
        _spieler[1] = new Mensch();
        _brett = new ArraySpielbrett(_spieler.length);
        _spiel = new Spiel(_spieler, _brett);
    }

    private void setup1Mensch1KI() {
        _spieler = new Spieler[2];
        _spieler[0] = new Mensch();
        KI ki = new DecisionNetworkKI();
<<<<<<< HEAD
=======

>>>>>>> FETCH_HEAD
        _spieler[1] = ki;
        ki.setzeSpielerIndex(1);

        _brett = new ArraySpielbrett(_spieler.length);
        _spiel = new Spiel(_spieler, _brett);
        ki.setzeSpielbrett(_brett);

    }

    private void setup2KI() {
        _spieler = new Spieler[2];
        KI ki0 = new DecisionNetworkKI();
        _spieler[0] = ki0;
        ki0.setzeSpielerIndex(0);
        KI ki1 = new DecisionNetworkKI();
        _spieler[1] = ki1;
        ki1.setzeSpielerIndex(1);

        _brett = new ArraySpielbrett(_spieler.length);
        _spiel = new Spiel(_spieler, _brett);
        ki0.setzeSpielbrett(_brett);
        ki1.setzeSpielbrett(_brett);
    }

    private void spielStarten() {
        System.out.println("Spiel wird gestartet... \n");
        String ausgabe = "Spieler: ";
        for (int index = 0; index < _spieler.length; ++index) {
            ausgabe += index + " (" + _spieler[index].getClass().toString() + ")";
        }
        System.out.println(ausgabe);
        spielFortsetzen();
        System.out.println("Spiel zu Ende!");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == _spiel) {
            if (arg instanceof Mensch) {
                zieheMitMensch();
            } else if (arg instanceof KI) {
                zieheMitKI();
            } else {
                zeigeZwischenstand();
                warteAufBestaetigung();
            }
            if (!_spiel.spielZuEnde()) {
                spielFortsetzen();
            }
        }
    }

    private void spielFortsetzen() {

        _spiel.spielFortfahren();
    }

    private void zieheMitKI() {
        spielstandAnzeigen();
        List<Zug> zuege = _spiel.getMoeglicheZuege();
        System.out.println(zuege.toString());
        _spiel.zieheKI();
    }

    private void zieheMitMensch() {
        spielstandAnzeigen();
        List<Zug> zuege = _spiel.getMoeglicheZuege();

        if (!zuege.isEmpty()) {
            if (zuege.size() == 1) {
                System.out.println("Nur ein Zug moeglich. Es wird automatisch gezogen.");
                _spiel.ziehe(zuege.get(0));
            } else {
                int i = 0;
                for (Zug zug : zuege) {
                    System.out.println("Zug " + i + zug.toString());
                    ++i;
                }
                Scanner scanner = new Scanner(System.in);
                int zugIndex = -1;
                do {
                    System.out.print("Zum ziehen Zug-Index angeben: ");
                    String zugIndexString = scanner.nextLine();
                    zugIndex = Integer.valueOf(zugIndexString);
                } while (zugIndex >= zuege.size() || zugIndex < 0);
                _spiel.ziehe(zuege.get(zugIndex));
            }
        } else {
            System.out.println("Kein Zug moeglich.");
        }
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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exc) {

        }
//        warteAufBestaetigung();
    }

    private void warteAufBestaetigung() {
//        Scanner scanner = new Scanner(System.in);
//        scanner.nextLine();
    }

    private void zeigeZwischenstand() {
        System.out.println("\n\n" + _spiel.toStringReinesBrett() + "\n");
    }
}
