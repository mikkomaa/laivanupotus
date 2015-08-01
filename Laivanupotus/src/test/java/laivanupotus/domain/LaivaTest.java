/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.domain;

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
public class LaivaTest {
    
    Laiva laiva;

    @Before
    public void setUp() {
        laiva = new Laiva();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriLuoLaivanOikein() {
        assertEquals("Laivan sijainti on []", laiva.toString());
    }

    @Test
    public void lisaaRuutuLisaaSijainninOikein() {
        laiva.lisaaRuutu(0, 0);
        assertEquals("Laivan sijainti on [0]", laiva.toString());

        laiva.lisaaRuutu(3, 5);
        assertEquals("Laivan sijainti on [0, 35]", laiva.toString());

        laiva.lisaaRuutu(9, 9);
        assertEquals("Laivan sijainti on [0, 35, 99]", laiva.toString());

        laiva.lisaaRuutu(-1, 0);
        assertEquals("Laivan sijainti on [0, 35, 99, -10]", laiva.toString());
        
        laiva.lisaaRuutu(0, 0);
        assertEquals("Laivan sijainti on [0, 35, 99, -10, 0]", laiva.toString());
    }

    @Test
    public void kokoOnOikein() {
        assertEquals(0, laiva.getKoko());

        laiva.lisaaRuutu(0, 0);
        assertEquals(1, laiva.getKoko());

        laiva.lisaaRuutu(7, 7);
        assertEquals(2, laiva.getKoko());
        
        laiva.lisaaRuutu(7, 7);
        assertEquals(3, laiva.getKoko());
    }

    @Test
    public void onkoLaivaRuudussaToimiiOikein() {
        assertEquals(false, laiva.onkoLaivaRuudussa(0, 0));

        laiva.lisaaRuutu(0, 0);
        assertEquals(true, laiva.onkoLaivaRuudussa(0, 0));
        assertEquals(false, laiva.onkoLaivaRuudussa(0, 1));
        assertEquals(false, laiva.onkoLaivaRuudussa(1, 0));

        laiva.lisaaRuutu(0, 1);
        assertEquals(true, laiva.onkoLaivaRuudussa(0, 0));
        assertEquals(true, laiva.onkoLaivaRuudussa(0, 1));
        assertEquals(false, laiva.onkoLaivaRuudussa(10, 1));
    }
}
