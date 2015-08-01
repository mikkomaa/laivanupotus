/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.domain;

/**
 *
 * @author Admin
 */
public class PisteidenLaskija {

    private int pisteet;

    public PisteidenLaskija() {
        this.pisteet = 0;
    }

    public int getPisteet() {
        return this.pisteet;
    }

    public void lisaaPisteet(int maara) {
        this.pisteet += maara;
    }
    
    public void nollaaPisteet() {
        this.pisteet = 0;
    }

    @Override
    public String toString() {
        return "pisteet = " + this.pisteet;
    }
}
