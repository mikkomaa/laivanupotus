/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.sovelluslogiikka;

import static laivanupotus.domain.Ruutu.LAIVA;
import laivanupotus.tekoaly.Ampuja;

/**
 *
 * @author Admin
 */
public class TestiMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pelilauta pelilauta = new Pelilauta();
        pelilauta.setRuutu(0, 0, LAIVA);
        pelilauta.setRuutu(0, 1, LAIVA);
        pelilauta.setRuutu(0, 2, LAIVA);
        pelilauta.setRuutu(0, 3, LAIVA);

        pelilauta.setRuutu(5, 2, LAIVA);
        pelilauta.setRuutu(6, 2, LAIVA);
        pelilauta.setRuutu(7, 2, LAIVA);

        pelilauta.setRuutu(1, 7, LAIVA);
        pelilauta.setRuutu(2, 7, LAIVA);

        pelilauta.setRuutu(8, 8, LAIVA);

        LaivojenLukija laivojenLukija = new LaivojenLukija();
        laivojenLukija.lueLaivat(pelilauta);
        //System.out.println(laivojenLukija.toString());

       // System.out.println(pelilauta.toString());
        Ampuja ampuja = new Ampuja();
        System.out.println(ampuja);
    }

}
