/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik.KI;

import Spiellogik.Spielbrett;
import Spiellogik.Zug;
import java.util.List;

/**
 *
 * @author Chris
 */
public class DecisionNetworkKI extends KI {

    public DecisionNetworkKI(Spielbrett brett, int index) {
        super(brett, index);
    }

    public DecisionNetworkKI() {
        super();
    }

    @Override
    public Zug entscheide(List<Zug> zuege) {
        // TODO implementieren, momentan Dummy
        for (Zug zug : zuege) {
            return zug;
        }
        return null;
    }

}
