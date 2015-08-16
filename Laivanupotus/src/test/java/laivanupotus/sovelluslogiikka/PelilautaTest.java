/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.sovelluslogiikka;

import laivanupotus.domain.Ruutu;
import static laivanupotus.domain.Ruutu.*;
import static laivanupotus.domain.Vakiot.LAIVARUUTUJA;
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
public class PelilautaTest {

    Pelilauta pelilauta;
    Pelilauta laivatOikeinLauta;
    String tyhjaLauta
            = "TYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\t\n"
            + "TYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\t\n"
            + "TYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\t\n"
            + "TYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\t\n"
            + "TYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\t\n"
            + "TYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\t\n"
            + "TYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\t\n"
            + "TYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\t\n"
            + "TYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\t\n"
            + "TYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\tTYHJA\t\n";

    @Before
    public void setUp() {
        pelilauta = new Pelilauta();
        
        // Luodaan lauta, jossa laivat ovat oikein.
        laivatOikeinLauta = new Pelilauta();

        laivatOikeinLauta.setRuutu(0, 0, LAIVA);
        laivatOikeinLauta.setRuutu(0, 1, LAIVA);
        laivatOikeinLauta.setRuutu(0, 2, LAIVA);
        laivatOikeinLauta.setRuutu(0, 3, LAIVA);

        laivatOikeinLauta.setRuutu(5, 2, LAIVA);
        laivatOikeinLauta.setRuutu(6, 2, LAIVA);
        laivatOikeinLauta.setRuutu(7, 2, LAIVA);

        laivatOikeinLauta.setRuutu(1, 7, LAIVA);
        laivatOikeinLauta.setRuutu(1, 8, LAIVA);

        laivatOikeinLauta.setRuutu(8, 8, LAIVA);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriLuoLaudanOikein() {
        assertEquals(tyhjaLauta, pelilauta.toString());
    }
    
    @Test
    public void konstruktoriAlustaaPisteetOikein() {
        assertEquals(0, pelilauta.getPisteet());
    }

    @Test
    public void getRuutuToimiiOikeinKunRuutuOnLaudalla() {
        assertEquals(TYHJA, pelilauta.getRuutu(0, 0));
        assertEquals(TYHJA, pelilauta.getRuutu(0, 1));
        assertEquals(TYHJA, pelilauta.getRuutu(1, 0));
        assertEquals(TYHJA, pelilauta.getRuutu(1, 1));
        assertEquals(TYHJA, pelilauta.getRuutu(3, 7));
        assertEquals(TYHJA, pelilauta.getRuutu(9, 9));
        assertEquals(TYHJA, pelilauta.getRuutu(8, 9));
        assertEquals(TYHJA, pelilauta.getRuutu(9, 8));
        assertEquals(TYHJA, pelilauta.getRuutu(8, 8));
    }
    
    @Test
    public void getRuutuToimiiOikeinKunRuutuEiOleLaudalla() {
        assertEquals(null, pelilauta.getRuutu(-1, 0));
        assertEquals(null, pelilauta.getRuutu(0, -1));
        assertEquals(null, pelilauta.getRuutu(-1, -1));
        assertEquals(null, pelilauta.getRuutu(-1, 10));
        assertEquals(null, pelilauta.getRuutu(10, 10));
        assertEquals(null, pelilauta.getRuutu(10, 9));
        assertEquals(null, pelilauta.getRuutu(9, 10));
        assertEquals(null, pelilauta.getRuutu(5, 333));
    }
    
    @Test
    public void setRuutuToimiiOikein() {
        pelilauta.setRuutu(0, 0, LAIVA);
        assertEquals(LAIVA, pelilauta.getRuutu(0, 0));
        
        pelilauta.setRuutu(0, 0, OSUMA);
        assertEquals(OSUMA, pelilauta.getRuutu(0, 0));
        
        pelilauta.setRuutu(2, 3, TYHJA);
        assertEquals(TYHJA, pelilauta.getRuutu(2, 3));
        
        pelilauta.setRuutu(8, 1, LUETTU);
        assertEquals(LUETTU, pelilauta.getRuutu(8, 1));
        
        pelilauta.setRuutu(6, 6, null);
        assertEquals(null, pelilauta.getRuutu(6, 6));
    }
    
    @Test
    public void lueLaivatToimiiOikeinKunLaivatOvatOikein() {
        assertEquals(true, laivatOikeinLauta.lueLaivat());
    }
    
    @Test
    public void lueLaivatToimiiOikeinKunLaivatOvatVaarin() {
        laivatOikeinLauta.setRuutu(9, 9, LAIVA);
        assertEquals(false, laivatOikeinLauta.lueLaivat());
    }
    
    @Test
    public void ammuToimiiOikeinKunKoordinaattiOnLaudalla() {
        assertEquals(AMMUTTU, pelilauta.ammu(3, 5));
        
        pelilauta.setRuutu(2, 2, LAIVA);
        assertEquals(OSUMA, pelilauta.ammu(2, 2));
        
        pelilauta.setRuutu(5, 4, OSUMA);
        assertEquals(OSUMA, pelilauta.ammu(5, 4));
    }
    
    @Test
    public void ammuToimiiOikeinKunKoordinaattiEiOleLaudalla() {
        assertEquals(null, pelilauta.ammu(-1, 0));
        assertEquals(null, pelilauta.ammu(2, 20));
    }
    
    @Test
    public void ovatkoLaivatUponneetToimiiOikein() {
        assertEquals(false, pelilauta.ovatkoLaivatUponneet());
        
        pelilauta.setRuutu(0, 0, OSUMA);
        assertEquals(false, pelilauta.ovatkoLaivatUponneet());
        
        for (int i = 0; i < LAIVARUUTUJA; i++) {
            pelilauta.setRuutu(0, i, OSUMA);
        }
        assertEquals(true, pelilauta.ovatkoLaivatUponneet());
        
        pelilauta.setRuutu(5, 0, OSUMA);
        assertEquals(false, pelilauta.ovatkoLaivatUponneet());
    }
    
    @Test
    public void lisaaPisteetToimiiOikein() {
        pelilauta.lisaaPisteet(50);
        assertEquals(50, pelilauta.getPisteet());
        
        pelilauta.lisaaPisteet(-61);
        assertEquals(-11, pelilauta.getPisteet());
    }
    
    @Test
    public void nollaaPisteetToimiiOikein() {
        pelilauta.lisaaPisteet(50);
        pelilauta.nollaaPisteet();
        assertEquals(0, pelilauta.getPisteet());
    }
    
    @Test
    public void alustaLautaNollaaPisteetOikein() {
        pelilauta.lisaaPisteet(50);
        pelilauta.alustaLauta();
        assertEquals(0, pelilauta.getPisteet());
    }
}
