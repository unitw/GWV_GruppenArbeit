/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spiellogik.KI;

import Spiellogik.Zug;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Chris
 */
public class RandomKI extends KI {

    @Override
    public Zug entscheide(List<Zug> zuege) {
        Random random = new Random();
        int zugIndex = random.nextInt(zuege.size());
        return zuege.get(zugIndex);
    }
    
}
