/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik;

import java.util.Set;

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
    public Zug entscheide(Set<Zug> zuege) {
        // TODO implementieren, momentan Dummy
        for (Zug zug : zuege) {
            return zug;
        }
        return null;
    }

}
