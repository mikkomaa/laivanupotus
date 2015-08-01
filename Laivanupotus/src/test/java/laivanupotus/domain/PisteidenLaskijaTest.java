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
public class PisteidenLaskijaTest {

    PisteidenLaskija laskija;

    @Before
    public void setUp() {
        laskija = new PisteidenLaskija();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriLuoLaskijanOikein() {
        assertEquals("pisteet = 0", laskija.toString());
    }
    
    @Test
    public void lisaaPisteetToimiiOikeinPositiivisellaLuvulla() {
        laskija.lisaaPisteet(43);
        assertEquals("pisteet = 43", laskija.toString());
        
        laskija.lisaaPisteet(5000);
        assertEquals("pisteet = 5043", laskija.toString());
    }
    
    @Test
    public void lisaaPisteetToimiiOikeinNegatiivisellaLuvulla() {
        laskija.lisaaPisteet(-1);
        assertEquals("pisteet = -1", laskija.toString());
        
        laskija.lisaaPisteet(-5000);
        assertEquals("pisteet = -5001", laskija.toString());
    }
    
    @Test
    public void lisaaPisteetToimiiOikeinNollalla() {
        laskija.lisaaPisteet(0);
        assertEquals("pisteet = 0", laskija.toString());
    }
    
    @Test
    public void getPisteetToimiiOikein() {
        assertEquals(0, laskija.getPisteet());
        
        laskija.lisaaPisteet(-2);
        assertEquals(-2, laskija.getPisteet());
    }
    
    @Test
    public void nollaaPisteetToimiiOikein() {
        laskija.lisaaPisteet(123);
        laskija.nollaaPisteet();
        assertEquals("pisteet = 0", laskija.toString());
    }
}
