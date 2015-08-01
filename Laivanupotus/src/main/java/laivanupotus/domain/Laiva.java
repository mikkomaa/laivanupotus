/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Laiva {

    private List<Integer> sijainti;

    public Laiva() {
        this.sijainti = new ArrayList<>();
    }

    public void lisaaRuutu(int korkeus, int leveys) {
        this.sijainti.add(10 * korkeus + leveys);
    }

    public boolean onkoLaivaRuudussa(int korkeus, int leveys) {
        int tarkistettava = 10 * korkeus + leveys;
        for (Integer ruutu : this.sijainti) {
            if (ruutu == tarkistettava) {
                return true;
            }
        }
        return false;
    }

    public int getKoko() {
        return this.sijainti.size();
    }

    @Override
    public String toString() {
        return "Laivan sijainti on " + this.sijainti;
    }
}
