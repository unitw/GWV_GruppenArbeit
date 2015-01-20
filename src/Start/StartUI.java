/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start;

//import static Start.Start.gui;
import Spiellogik.ArraySpielbrett;
import javax.swing.SwingUtilities;

public class StartUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                GUI gui = new GUI();
            }
        });

    }

}
