/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.tekoaly;

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
public class LaivojenAsettajaTest {

    Pelilauta pelilauta;
    LaivojenAsettaja asettaja;
    LaivojenLukija lukija;
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
        asettaja = new LaivojenAsettaja(pelilauta);
        lukija = new LaivojenLukija();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriToimiiOikein() {
        assertEquals(tyhjaLauta, asettaja.getPelilauta().toString());
    }

    @Test
    public void laivatAsetetaanOikeinKunLautaOnTyhja() {
        asettaja.asetaLaivat();
        Pelilauta laivallinenLauta = asettaja.getPelilauta();
        assertEquals(true, lukija.lueLaivat(laivallinenLauta));
    }

    @Test
    public void laivatAsetetaanOikeinKunLautaEiOleTyhja() {
        asettaja.asetaLaivat(); // Tämän jälkeen lauta ei ole tyhjä
        asettaja.asetaLaivat(); // Asetetaan laivat uudestaan.
        Pelilauta laivallinenLauta = asettaja.getPelilauta();
        assertEquals(true, lukija.lueLaivat(laivallinenLauta));
    }

    @Test
    public void laivatAsetetaanOikeinUseitaKertoja() {
        for (int i = 0; i < 100; i++) {
            asettaja.asetaLaivat();
            Pelilauta laivallinenLauta = asettaja.getPelilauta();
            LaivojenLukija uusiLukija = new LaivojenLukija();
            assertEquals(true, uusiLukija.lueLaivat(laivallinenLauta));
        }
    }
}
