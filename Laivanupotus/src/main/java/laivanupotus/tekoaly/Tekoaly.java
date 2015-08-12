/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.tekoaly;

import laivanupotus.domain.Ruutu;
import laivanupotus.sovelluslogiikka.Pelilauta;

/**
 *
 * @author Admin
 */
public class Tekoaly {

    private Pelilauta tekoalynLauta;
    private Ampuja ampuja;

    public Tekoaly(Pelilauta tekoalynLauta) {
        this.tekoalynLauta = tekoalynLauta;
        this.ampuja = new Ampuja();
    }

    public void alustaTekoaly() {
        this.tekoalynLauta.alustaLauta();
        this.ampuja.alustaAmpuja();
    }

    public void asetaLaivat() {
        LaivojenAsettaja asettaja = new LaivojenAsettaja(this.tekoalynLauta);
        asettaja.asetaLaivat();
    }

    public int ammu() {
        return this.ampuja.etsiAmpumakoordinaatti();
    }

    public void paivitaAmmuksenTulos(Ruutu ruutu) {
        this.ampuja.paivitaSisainenLauta(ruutu);
    }

    @Override
    public String toString() {
        return "Tekoälyn pelilauta:\n" + this.tekoalynLauta.toString()
                + "\nAmpujan sisäinen lauta ja muut tiedot:\n"
                + this.ampuja.toString();
    }
    
    // testausta helpottamaan
    public Pelilauta getTekoalynLauta() {
        return this.tekoalynLauta;
    }
}
