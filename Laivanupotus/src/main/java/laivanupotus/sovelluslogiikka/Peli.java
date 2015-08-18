/*
 * Pakkaus sisältää pelin keskeiset sovelluslogiikkaluokat
 */
package laivanupotus.sovelluslogiikka;

import laivanupotus.domain.Ruutu;
import static laivanupotus.domain.Ruutu.*;
import static laivanupotus.domain.Vakiot.*;
import laivanupotus.tekoaly.Tekoaly;

/**
 * Luokka tarjoaa käyttöliittymän tarvitsemat palvelut pelin toteuttamiseen
 */
public class Peli {

    private Pelilauta pelaajanLauta;
    private Pelilauta tekoalynLauta;
    private Tekoaly tekoaly;
    private boolean peliKaynnissa;

    /**
     * Luokan konstruktori, joka alustaa tarvittavat muuttujat
     */
    public Peli() {
        this.pelaajanLauta = new Pelilauta();
        this.tekoalynLauta = new Pelilauta();
        this.tekoaly = new Tekoaly(this.tekoalynLauta);
        this.peliKaynnissa = false;
    }

    public void setPeliKaynnissa(boolean arvo) {
        this.peliKaynnissa = arvo;
    }

    public boolean onkoPeliKaynnissa() {
        return this.peliKaynnissa;
    }

    /**
     * Metodi alustaa pelin uutta peliä varten
     */
    public void alustaPeli() {
        this.pelaajanLauta.alustaLauta();
        this.tekoaly.alustaTekoaly();
        this.peliKaynnissa = false;
    }

    /**
     * Metodi tarkistaa, ovatko laivat oikein pelaajan laudalla
     * 
     * @return Palauttaa true, jos laivat ovat oikein, muuten false
     */
    public boolean asetaPelaajanLaivat() {
        return this.pelaajanLauta.lueLaivat();
    }

    /**
     * Metodi asettaa laivat tekoalyn laudalle. Metodi suorituksen jälkeen
     * tekoälyn laudalla on laivat oikein.
     */
    public void asetaTekoalynLaivat() {
        this.tekoaly.asetaLaivat();
    }

    /**
     * Metodi tarkistaa, ovatko pelaajan tai tekoalyn kaikki laivat uponneet
     * 
     * @return Palauttaa true, jos vähintään toisen laivat ovat uponneet, muuten false
     */
    public boolean ovatkoLaivatUponneet() {
        return this.pelaajanLauta.ovatkoLaivatUponneet()
                || this.tekoalynLauta.ovatkoLaivatUponneet();
    }

    /**
     * Metodi asettaa pelaajan pelilaudan tietyn ruudun arvon
     * 
     * @param korkeus Asetettavan ruudun y-koordinaatti
     * @param leveys Asetettavan ruudun x-koordinaatti
     * @param ruutu Arvo, joksi ruutu asetetaan
     */
    public void setPelaajanRuutu(int korkeus, int leveys, Ruutu ruutu) {
        this.pelaajanLauta.setRuutu(korkeus, leveys, ruutu);
    }

    /**
     * Metodi hakee pelaajan pelilaudan tietyn ruudun arvon
     * 
     * @param korkeus Haettavan ruudun y-koordinaatti
     * @param leveys Haettavan ruudun x-koordinaatti
     * 
     * @return Palauttaa haettavan ruudun arvon
     */
    public Ruutu getPelaajanRuutu(int korkeus, int leveys) {
        return this.pelaajanLauta.getRuutu(korkeus, leveys);
    }

    /**
     * Metodi hakee tekoalyn pelilaudan tietyn ruudun arvon
     * 
     * @param korkeus Haettavan ruudun y-koordinaatti
     * @param leveys Haettavan ruudun x-koordinaatti
     * 
     * @return Palauttaa haettavan ruudun arvon
     */
    public Ruutu getTekoalynRuutu(int korkeus, int leveys) {
        return this.tekoalynLauta.getRuutu(korkeus, leveys);
    }

    /**
     * Metodi ampuu tekoalyn pelilaudan tiettyyn ruutuun eli muuttaa ruudun
     * arvoksi AMMUTTU tai OSUMA sen mukaan, onko ruudussa laiva vai ei. Metodi
     * myös päivittää pelaajan pisteet vastaavasti.
     * 
     * @param korkeus Ammuttavan ruudun y-koordinaatti
     * @param leveys Ammuttavan ruudun x-koordinaatti
     * 
     * @return Palauttaa ammutun ruudun arvon ammunnan jälkeen
     */
    public Ruutu ammuTekoalynLautaan(int korkeus, int leveys) {
        Ruutu ruutu = this.tekoalynLauta.ammu(korkeus, leveys);
        this.lisaaPisteet(this.pelaajanLauta, ruutu);
        return ruutu;
    }

    /**
     * Metodi hakee tekoälyltä ampumakoordinaatin ja päivittää sitä vastaavan
     * pelaajan pelilaudan ruudun arvoksi AMMUTTU tai OSUMA sen mukaan, onko
     * ruudussa laiva vai ei. Metodi myös päivittää tekoälyn tilan ja pisteet.
     * 
     * @return Palauttaa tekoälyn palauttaman ampumakoordinaatin
     */
    public int ammuPelaajanLautaan() {
        int koordinaatti = this.tekoaly.ammu();
        int korkeus = koordinaatti / 10; // Muunnetaan koordinaatti pelilaudan
        int leveys = koordinaatti % 10;  // y- ja x-koordinaateiksi
        Ruutu ruutu = this.pelaajanLauta.ammu(korkeus, leveys);
        this.tekoaly.paivitaAmmuksenTulos(ruutu);
        this.lisaaPisteet(this.tekoalynLauta, ruutu);
        return koordinaatti;
    }

    /**
     * Metodi hakee tekoälyn pisteet
     * 
     * @return Palauttaa tekoälyn pisteiden määrän
     */
    public int getTekoalynPisteet() {
        return this.tekoalynLauta.getPisteet();
    }

    /**
     * Metodi hakee pelaajan pisteet
     * 
     * @return Palauttaa pelaajan pisteiden määrän
     */
    public int getPelaajanPisteet() {
        return this.pelaajanLauta.getPisteet();
    }

    /**
     * Metodi pelin ohjeen tulostamiseen
     * 
     * @return Palauttaa pelin ohjeen
     */
    public String tulostaOhje() {
        return "Aseta hiirellä omalle laudallesi 4 laivaa. Laivojen koot ovat "
                + "1x1, 2x1, 3x1 ja 4x1 ruutua. Laivat eivät saa koskea toisiaan"
                + ", eivät edes kulmittain. Aloita sen jälkeen peli painamalla "
                + "Aloita-painiketta. Pelissä ammu hiirellä tekoälyn lautaan. "
                + "Pelin voittaa se, joka upottaa ensin toisen kaikki laivat. "
                + "Voit aloittaa uuden pelin milloin tahansa Aloita-painikkeella.";
    }

    @Override
    public String toString() {
        return this.pelaajanLauta.toString() + this.tekoaly.toString() + "\n"
                + this.peliKaynnissa;
    }

    private void lisaaPisteet(Pelilauta pelilauta, Ruutu ruutu) {
        if (ruutu == OSUMA) {
            pelilauta.lisaaPisteet(OSUMAPISTEET);
        } else {
            pelilauta.lisaaPisteet(HUTIPISTEET);
        }
    }
}
