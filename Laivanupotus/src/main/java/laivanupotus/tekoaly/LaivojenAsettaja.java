/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.tekoaly;

import java.util.Random;
import static laivanupotus.domain.Ruutu.LAIVA;
import laivanupotus.domain.Suunta;
import static laivanupotus.domain.Suunta.*;
import static laivanupotus.domain.Vakiot.*;
import laivanupotus.sovelluslogiikka.Pelilauta;

/**
 *
 * @author Admin
 */
public class LaivojenAsettaja {

    private Pelilauta pelilauta;

    public LaivojenAsettaja(Pelilauta pelilauta) {
        this.pelilauta = pelilauta;
    }

    public void asetaLaivat() {
        do {
            this.pelilauta.alustaLauta();
            for (int koko = 1; koko <= LAIVOJA; koko++) {
                asetaLaiva(koko);
            }
        } while (!this.pelilauta.lueLaivat());
    }
    
    // testausta helpottamaan
    public Pelilauta getPelilauta() {
        return this.pelilauta;
    }

    private void asetaLaiva(int laivanKoko) {
        Random arpa = new Random();
        int alkupisteenKorkeus = arpa.nextInt(LAUDAN_KORKEUS);
        int alkupisteenLeveys = arpa.nextInt(LAUDAN_LEVEYS);
        Suunta suunta = arvoSuunta(arpa);
        asetaLaivanRuudut(alkupisteenKorkeus, alkupisteenLeveys, laivanKoko, suunta);
    }

    private void asetaLaivanRuudut(int alkupisteenKorkeus, int alkupisteenLeveys,
            int laivanKoko, Suunta suunta) {
        for (int i = 0; i < laivanKoko; i++) {
            this.pelilauta.setRuutu(alkupisteenKorkeus, alkupisteenLeveys, LAIVA);
            if (suunta == YLOS) {
                alkupisteenKorkeus--;
            } else if (suunta == OIKEA) {
                alkupisteenLeveys++;
            } else if (suunta == ALAS) {
                alkupisteenKorkeus++;
            } else { // suunta == VASEN
                alkupisteenLeveys--;
            }
        }
    }

    private Suunta arvoSuunta(Random arpa) {
        Suunta[] suunnat = Suunta.values();
        int monesko = arpa.nextInt(suunnat.length);
        return suunnat[monesko];
    }
}
