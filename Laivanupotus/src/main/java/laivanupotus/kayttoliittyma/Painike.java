/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.kayttoliittyma;

import javax.swing.JButton;

/**
 *
 * @author Admin
 */
public class Painike extends JButton {

    private int korkeus;
    private int leveys;

    public Painike(int korkeus, int leveys) {
        this.korkeus = korkeus;
        this.leveys = leveys;
    }

    public int getKorkeus() {
        return this.korkeus;
    }

    public int getLeveys() {
        return this.leveys;
    }
}
