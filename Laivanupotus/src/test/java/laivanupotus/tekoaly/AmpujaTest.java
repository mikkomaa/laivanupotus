/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.tekoaly;

import static laivanupotus.domain.Ruutu.*;
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
public class AmpujaTest {

    Ampuja ampuja;
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
        ampuja = new Ampuja();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriLuoAmpujanOikein() {
        String jono = tyhjaLauta + "pino = []" + "\nviimeisinKoordinaatti = -1";
        assertEquals(jono, ampuja.toString());
    }

    @Test
    public void ampujaAntaaLaudallaOlevanKoordinaatin() {
        int koordinaatti;
        boolean arvo;
        for (int i = 0; i < 50; i++) {
            koordinaatti = ampuja.etsiAmpumakoordinaatti();
            arvo = koordinaatti >= 0 && koordinaatti < 100;
            assertEquals(true, arvo);
        }
    }
    @Test
    public void alustaAmpujaAlustaaAmpujanOikein() {
        ampuja.etsiAmpumakoordinaatti();
        ampuja.paivitaSisainenLauta(OSUMA);
        ampuja.alustaAmpuja();
        String jono = tyhjaLauta + "pino = []" + "\nviimeisinKoordinaatti = -1";
        assertEquals(jono, ampuja.toString());
    }
    
    @Test
    public void koordinaattiaHaetaanPinostaJosPinoEiOleTyhja() {
        int ekaKoordinaatti = annaKoordinaattiMuttaEiReunalta(ampuja);
        ampuja.paivitaSisainenLauta(OSUMA);
        int tokaKoordinaatti = ampuja.etsiAmpumakoordinaatti();
        boolean arvo = (ekaKoordinaatti - 10) == tokaKoordinaatti;
        assertEquals(true, arvo);
    }
    
    @Test
    public void vinoruudutAsetetaanAmmutuiksiOsumanJalkeen() {
        int koordinaatti = annaKoordinaattiMuttaEiReunalta(ampuja);
        ampuja.paivitaSisainenLauta(OSUMA);
        
        int korkeus = koordinaatti / 10;
        int leveys = koordinaatti % 10;
        Pelilauta apulauta = new Pelilauta();
        apulauta.setRuutu(korkeus, leveys, OSUMA);
        apulauta.setRuutu(korkeus - 1, leveys - 1, AMMUTTU);
        apulauta.setRuutu(korkeus - 1, leveys + 1, AMMUTTU);
        apulauta.setRuutu(korkeus + 1, leveys - 1, AMMUTTU);
        apulauta.setRuutu(korkeus + 1, leveys + 1, AMMUTTU);
        
        String jono = apulauta.toString() + "pino = [" + koordinaatti
                + "]\nviimeisinKoordinaatti = " + koordinaatti;
        assertEquals(jono, ampuja.toString());
    }
    
    @Test
    public void koordinaatitHaetaanOsumanJalkeenOsumanYmparilta() {
        int ekaKoordinaatti = annaKoordinaattiMuttaEiReunalta(ampuja);
        ampuja.paivitaSisainenLauta(OSUMA);
        
        int tokaKoordinaatti = ampuja.etsiAmpumakoordinaatti();
        assertEquals(ekaKoordinaatti - 10, tokaKoordinaatti);
        ampuja.paivitaSisainenLauta(AMMUTTU);
        
        tokaKoordinaatti = ampuja.etsiAmpumakoordinaatti();
        assertEquals(ekaKoordinaatti + 1, tokaKoordinaatti);
        ampuja.paivitaSisainenLauta(AMMUTTU);
        
        tokaKoordinaatti = ampuja.etsiAmpumakoordinaatti();
        assertEquals(ekaKoordinaatti + 10, tokaKoordinaatti);
        ampuja.paivitaSisainenLauta(AMMUTTU);
        
        tokaKoordinaatti = ampuja.etsiAmpumakoordinaatti();
        assertEquals(ekaKoordinaatti - 1, tokaKoordinaatti);
        ampuja.paivitaSisainenLauta(AMMUTTU);
    }
    
    private int annaKoordinaattiMuttaEiReunalta(Ampuja ampuja) {
        int koordinaatti;
        do {
            koordinaatti = ampuja.etsiAmpumakoordinaatti();
        } while (koordinaatti < 10 || koordinaatti > 89 ||
                koordinaatti % 10 == 0 || koordinaatti % 10 == 9);
        return koordinaatti;
    }
}
