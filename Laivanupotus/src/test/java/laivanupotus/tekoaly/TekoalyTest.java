/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.tekoaly;

import static laivanupotus.domain.Ruutu.*;
import laivanupotus.sovelluslogiikka.LaivojenLukija;
import laivanupotus.sovelluslogiikka.Pelilauta;
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
public class TekoalyTest {

    Tekoaly tekoaly;

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
        tekoaly = new Tekoaly(new Pelilauta());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriAlustaaTekoalynOikein() {
        assertEquals(alustettuTekoaly, tekoaly.toString());
    }

    @Test
    public void asetaLaivatAsettaaLaivatOikein() {
        tekoaly.asetaLaivat();
        Pelilauta lauta = tekoaly.getTekoalynLauta();
        LaivojenLukija laivojenLukija = new LaivojenLukija();
        boolean laivatOikein = laivojenLukija.lueLaivat(lauta);
        assertEquals(true, laivatOikein);
    }

    @Test
    public void ammuAntaaLaudallaOlevanKoordinaatin() {
        int koordinaatti;
        boolean arvo;
        for (int i = 0; i < 50; i++) {
            koordinaatti = tekoaly.ammu();
            arvo = koordinaatti >= 0 && koordinaatti < 100;
            assertEquals(true, arvo);
        }
    }

    @Test
    public void alustaTekoalyToimiiOikein() {
        // tehdään ensin tekoalyn tilalle jotain
        int koordinaatti = tekoaly.ammu();
        tekoaly.paivitaAmmuksenTulos(AMMUTTU);
        tekoaly.getTekoalynLauta().setRuutu(0, 0, LAIVA);

        tekoaly.alustaTekoaly();
        assertEquals(alustettuTekoaly, tekoaly.toString());
    }

    @Test
    public void paivitaAmmuksenTulosPaivittaaOikein() {
        int koordinaatti = tekoaly.ammu();
        tekoaly.paivitaAmmuksenTulos(AMMUTTU);

        int korkeus = koordinaatti / 10;
        int leveys = koordinaatti % 10;
        Pelilauta vertailulauta = new Pelilauta();
        vertailulauta.setRuutu(korkeus, leveys, AMMUTTU);

        String tekoalynTila = "Tekoälyn pelilauta:\n" + tyhjaLauta
                + "\nAmpujan sisäinen lauta ja muut tiedot:\n"
                + vertailulauta.toString()
                + "pino = []\nviimeisinKoordinaatti = "
                + koordinaatti;
        
        assertEquals(tekoalynTila, tekoaly.toString());
    }
}
