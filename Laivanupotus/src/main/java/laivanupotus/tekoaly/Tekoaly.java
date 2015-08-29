/*
 * Pakkaus sisältää tekoalyn ja sen toimintaan liittyvät luokat
 */
package laivanupotus.tekoaly;

import laivanupotus.domain.Ruutu;
import laivanupotus.sovelluslogiikka.Pelilauta;

/**
 * Luokka määrittelee pelaajaa vastaan pelaavan tekoalyn toiminnallisuuden ja
 * palvelut, joiden avulla peli voi käyttää tekoälyä
 */
public class Tekoaly {

    private Pelilauta tekoalynLauta;
    private Ampuja ampuja;

    /**
     * Konstruktori, joka luo tekoalyn
     * 
     * @param tekoalynLauta tekoalyn pelilauta, jonka se saa peliltä
     */
    public Tekoaly(Pelilauta tekoalynLauta) {
        this.tekoalynLauta = tekoalynLauta;
        this.ampuja = new Ampuja();
    }

    /**
     * Metodi alustaa tekoalyn uutta peliä varten
     */
    public void alustaTekoaly() {
        this.tekoalynLauta.alustaLauta();
        this.ampuja.alustaAmpuja();
    }

    /**
     * Metodi asettaa tekoalyn laivat sen pelilaudalle. Metodin suorituksen
     * jälkeen laivat ovat laudalla oikein ja lauta on muuten tyhjä.
     */
    public void asetaLaivat() {
        LaivojenAsettaja asettaja = new LaivojenAsettaja(this.tekoalynLauta);
        asettaja.asetaLaivat();
    }

    /**
     * Metodi etsii ruudun, johon tekoaly haluaa seuraavaksi ampua pelaajan lautaan
     * 
     * @return Palauttaa ruudun koordinaatin laudalla (10 * korkeus + leveys)
     */
    public int ammu() {
        return this.ampuja.etsiAmpumakoordinaatti();
    }

    /**
     * Metodi päivittää tekoälyn sisäisen tilan sen perusteella, osuiko
     * edellinen ammus pelaajan laivaan vai ei.
     * 
     * @param ruutu Edellisen ammuksen tulos (OSUMA vai jokin muu)
     */
    public void paivitaAmmuksenTulos(Ruutu ruutu) {
        this.ampuja.paivitaSisainenLauta(ruutu);
    }

    @Override
    public String toString() {
        return "Tekoälyn pelilauta:\n" + this.tekoalynLauta.toString()
                + "\nAmpujan sisäinen lauta ja muut tiedot:\n"
                + this.ampuja.toString();
    }

    /**
     * Apumetodi helpottamaan testausta
     * 
     * @return Palauttaa tekoälyn pelilaudan
     */
    public Pelilauta getTekoalynLauta() {
        return this.tekoalynLauta;
    }
}
