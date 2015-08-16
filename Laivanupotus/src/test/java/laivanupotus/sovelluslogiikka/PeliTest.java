/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.sovelluslogiikka;

import static laivanupotus.domain.Ruutu.*;
import static laivanupotus.domain.Vakiot.*;
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
public class PeliTest {

    Peli peli;
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

    String alustettuTekoaly = "Tekoälyn pelilauta:\n" + tyhjaLauta
            + "\nAmpujan sisäinen lauta ja muut tiedot:\n" + tyhjaLauta
            + "pino = []\nviimeisinKoordinaatti = -1";

    @Before
    public void setUp() {
        peli = new Peli();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void alustaPeliToimiiOikein() {
        // Muutetaan ensin pelin tilaa jotenkin
        this.peli.setPeliKaynnissa(true);
        this.peli.ammuPelaajanLautaan();
        this.peli.ammuTekoalynLautaan(0, 0);
        
        String alustettuPeli = tyhjaLauta + alustettuTekoaly + "\nfalse";
        peli.alustaPeli();
        assertEquals(alustettuPeli, peli.toString());
    }
    
    @Test
    public void onkoPeliKaynnissaToimiiOikein() {
        peli.setPeliKaynnissa(true);
        assertEquals(true, peli.onkoPeliKaynnissa());
    }

    @Test
    public void ovatkoLaivatUponneetToimiiOikein() {
        assertEquals(false, peli.ovatkoLaivatUponneet());
    }

    @Test
    public void getPelaajanRuutuToimiiOikein() {
        assertEquals(TYHJA, peli.getPelaajanRuutu(0, 0));
    }

    @Test
    public void setPelaajanRuutuToimiiOikein() {
        peli.setPelaajanRuutu(0, 1, LAIVA);
        assertEquals(LAIVA, peli.getPelaajanRuutu(0, 1));
    }

    @Test
    public void getTekoalynRuutuToimiiOikein() {
        assertEquals(TYHJA, peli.getTekoalynRuutu(5, 5));
    }

    @Test
    public void getTekoalynPisteetToimiiOikein() {
        peli.ammuPelaajanLautaan();
        assertEquals(-50, peli.getTekoalynPisteet());
    }

    @Test
    public void getPelaajanPisteetToimiiOikein() {
        peli.ammuTekoalynLautaan(3, 3);
        assertEquals(-50, peli.getPelaajanPisteet());
    }

    @Test
    public void ammuTekoalynLautaanPalauttaaRuudunOikein() {
        assertEquals(AMMUTTU, peli.ammuTekoalynLautaan(2, 1));
    }

    @Test
    public void ammuTekoalynLautaanLisaaHutipisteetOikein() {
        peli.ammuTekoalynLautaan(2, 1);
        assertEquals(-50, peli.getPelaajanPisteet());
    }

    @Test
    public void ammuPelaajanLautaanLisaaOsumapisteetOikein() {
        for (int i = 0; i < LAUDAN_KORKEUS; i++) {
            for (int j = 0; j < LAUDAN_LEVEYS; j++) {
                peli.setPelaajanRuutu(i, j, LAIVA);
            }
        }
        peli.ammuPelaajanLautaan();
        assertEquals(1000, peli.getTekoalynPisteet());
    }
    
    @Test
    public void asetaPelaajanLaivatToimiiOikein() {
        assertEquals(false, peli.asetaPelaajanLaivat());
    }

    @Test
    public void asetaTekoalynLaivatToimiiOikein() {
        peli.asetaTekoalynLaivat();
        int laivaruutuja = 0;
        for (int i = 0; i < LAUDAN_KORKEUS; i++) {
            for (int j = 0; j < LAUDAN_LEVEYS; j++) {
                if (peli.getTekoalynRuutu(i, j) == LAIVA) {
                    laivaruutuja++;
                }
            }
        }
        assertEquals(true, laivaruutuja == 10);
    }

    @Test
    public void tulostaOhjeToimiiOikein() {
        String ohje = "Aseta hiirellä omalle laudallesi 4 laivaa. Laivojen koot ovat "
                + "1x1, 2x1, 3x1 ja 4x1 ruutua. Laivat eivät saa koskea toisiaan"
                + ", eivät edes kulmittain. Aloita sen jälkeen peli painamalla "
                + "Aloita-painiketta. Pelissä ammu hiirellä tekoälyn lautaan. "
                + "Pelin voittaa se, joka upottaa ensin toisen kaikki laivat. "
                + "Voit aloittaa uuden pelin milloin tahansa Aloita-painikkeella.";
        assertEquals(ohje, peli.tulostaOhje());
    }
}
