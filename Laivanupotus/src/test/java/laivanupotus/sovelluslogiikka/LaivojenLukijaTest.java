/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.sovelluslogiikka;

import laivanupotus.domain.Ruutu;
import static laivanupotus.domain.Ruutu.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class LaivojenLukijaTest {

    LaivojenLukija laivojenLukija;
    Pelilauta pelilauta;

    @Before
    public void setUp() {
        laivojenLukija = new LaivojenLukija();

        // Luodaan lauta, jossa laivat ovat oikein.
        pelilauta = new Pelilauta();

        pelilauta.setRuutu(0, 0, LAIVA);
        pelilauta.setRuutu(0, 1, LAIVA);
        pelilauta.setRuutu(0, 2, LAIVA);
        pelilauta.setRuutu(0, 3, LAIVA);

        pelilauta.setRuutu(5, 2, LAIVA);
        pelilauta.setRuutu(6, 2, LAIVA);
        pelilauta.setRuutu(7, 2, LAIVA);

        pelilauta.setRuutu(1, 7, LAIVA);
        pelilauta.setRuutu(1, 8, LAIVA);

        pelilauta.setRuutu(8, 8, LAIVA);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void lueLaivatToimiiOikeinKunLaivatOvatOikein() {
        assertEquals(true, laivojenLukija.lueLaivat(pelilauta));
        
        laivojenLukija = new LaivojenLukija();
        pelilauta.setRuutu(1, 8, TYHJA);
        pelilauta.setRuutu(2, 7, LAIVA);
        assertEquals(true, laivojenLukija.lueLaivat(pelilauta));
    }

    @Test
    public void onkoLaivojaKulmittainToimiiOikeinKunLaivojaOnKulmittain() {
        pelilauta.setRuutu(8, 8, TYHJA);
        pelilauta.setRuutu(2, 9, LAIVA);
        assertEquals(false, laivojenLukija.lueLaivat(pelilauta));
    }

    @Test
    public void etsiLaivatLaudaltaToimiiOikein() {
        laivojenLukija.lueLaivat(pelilauta);
        String laivat = "Laivan sijainti on [0, 1, 2, 3]\n"
                + "Laivan sijainti on [17, 18]\n"
                + "Laivan sijainti on [52, 62, 72]\n"
                + "Laivan sijainti on [88]\n";
        assertEquals(laivat, laivojenLukija.toString());
    }

    @Test
    public void onkoLaivojaOikeaMaaraToimiiOikeinKunMaaraOnVaarin() {
        pelilauta.setRuutu(8, 8, TYHJA);
        assertEquals(false, laivojenLukija.lueLaivat(pelilauta));
    }

    @Test
    public void onkoLaivojenKootOikeinToimiiOikeinKunKootOvatVaarin() {
        pelilauta.setRuutu(0, 4, LAIVA);
        assertEquals(false, laivojenLukija.lueLaivat(pelilauta));
    }
}
