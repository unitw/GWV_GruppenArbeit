/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start;

//import static Start.Start.gui;
import ConsoleUI.ConsoleUI;
import java.util.Scanner;
import javax.swing.SwingUtilities;

public class StartUI {

    public static void main(String[] args) {
        
        System.out.println("Zum starten mit GUI 'y' eingeben: ");
        
        Scanner scanner =new Scanner(System.in);
        String eingabe = scanner.nextLine();
        
        if (eingabe.equals("y")) {
            SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI gui = new GUI();
              //  ConsoleUI con= new ConsoleUI();
            }
        });
        } else {
            ConsoleUI console = new ConsoleUI();
        }
        
    }

}
