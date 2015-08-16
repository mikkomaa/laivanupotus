package laivanupotus.sovelluslogiikka;

import laivanupotus.domain.PisteidenLaskija;
import laivanupotus.domain.Ruutu;
import static laivanupotus.domain.Ruutu.*;
import static laivanupotus.domain.Vakiot.*;

/**
 * Luokka määrittelee pelilaudan
 */
public class Pelilauta {
    
    private Ruutu[][] lauta;
    private PisteidenLaskija pisteidenLaskija;

    /**
     * Luokan konstruktori, joka luo pelilaudan ja alustaa sen peliä varten
     */
    public Pelilauta() {
        this.lauta = new Ruutu[LAUDAN_KORKEUS][LAUDAN_LEVEYS];
        this.pisteidenLaskija = new PisteidenLaskija();
        this.alustaLauta();
    }

    /**
     * Metodi alustaa pelilaudan uutta peliä varten
     */
    public void alustaLauta() {
        for (int korkeus = 0; korkeus < LAUDAN_KORKEUS; korkeus++) {
            for (int leveys = 0; leveys < LAUDAN_LEVEYS; leveys++) {
                this.lauta[korkeus][leveys] = TYHJA;
            }
        }
        this.pisteidenLaskija.nollaaPisteet();
    }

    /**
     * Metodi asettaa pelilaudan tietyn ruudun arvon
     * 
     * @param korkeus Asetettavan ruudun y-koordinaatti
     * @param leveys Asetettavan ruudun x-koordinaatti
     * @param ruutu Ruudulle asetettava arvo
     */
    public void setRuutu(int korkeus, int leveys, Ruutu ruutu) {
        if (onkoRuutuLaudalla(korkeus, leveys)) {
            this.lauta[korkeus][leveys] = ruutu;
        }
    }

    /**
     * Metodi palauttaa pelilaudan tietyn ruudun arvon
     * 
     * @param korkeus Palautettavan ruudun y-koordinaatti
     * @param leveys Palautettavan ruudun x-koordinaatti
     * 
     * @return Palauttaa ruudun arvon
     */
    public Ruutu getRuutu(int korkeus, int leveys) {
        if (onkoRuutuLaudalla(korkeus, leveys)) {
            return this.lauta[korkeus][leveys];
        }
        return null;
    }
    
    /**
     * Metodi tarkistaa, ovatko laivat oikein pelilaudalla
     * 
     * @return Palauttaa true, jos laivat ovat oikein, muuten false
     */
    public boolean lueLaivat() {
        LaivojenLukija lukija = new LaivojenLukija();
        return lukija.lueLaivat(this);
    }

    /**
     * Metodi muuttaa pelilaudan ruudun arvoksi AMMUTTU, jos ruudussa ei ole
     * laivaa, ja arvoksi OSUMA, jos ruudussa on laiva
     * 
     * @param korkeus Muutettavan ruudun y-koordinaatti
     * @param leveys Muutettavan ruudun x-koordinaatti
     * 
     * @return Palauttaa muutetun ruudun arvon muutoksen jälkeen
     */
    public Ruutu ammu(int korkeus, int leveys) {
        Ruutu ruutu = getRuutu(korkeus, leveys);
        if (ruutu == TYHJA) {
            setRuutu(korkeus, leveys, AMMUTTU);
        } else if (ruutu == LAIVA) {
            this.setRuutu(korkeus, leveys, OSUMA);
        }
        return getRuutu(korkeus, leveys);
    }

    /**
     * Metodi tarkistaa, ovatko pelilaudan laivat uponneet eli jokainen
     * laivaruutu saanut osuman
     * 
     * @return Palauttaa true, jos laivat ovat uponneet, muuten false
     */
    public boolean ovatkoLaivatUponneet() {
        int osumia = 0;
        for (int korkeus = 0; korkeus < LAUDAN_KORKEUS; korkeus++) {
            for (int leveys = 0; leveys < LAUDAN_LEVEYS; leveys++) {
                if (this.lauta[korkeus][leveys] == OSUMA) {
                    osumia++;
                }
            }
        }
        return osumia == LAIVARUUTUJA;
    }
    
    /**
     * Metodi lisää pelilaudan pisteitä
     * 
     * @param maara Lisättävä pistemäärä, joka voi olla myös negatiivinen
     */
    public void lisaaPisteet(int maara) {
        this.pisteidenLaskija.lisaaPisteet(maara);
    }
    
    public int getPisteet() {
        return this.pisteidenLaskija.getPisteet();
    }
    
    /**
     * Metodi nollaa pelilaudan pisteet
     */
    public void nollaaPisteet() {
        this.pisteidenLaskija.nollaaPisteet();
    }
    
    @Override
    public String toString() {
        String mjono = "";
        for (int korkeus = 0; korkeus < LAUDAN_KORKEUS; korkeus++) {
            for (int leveys = 0; leveys < LAUDAN_LEVEYS; leveys++) {
                mjono += this.lauta[korkeus][leveys] + "\t";
            }
            mjono += "\n";
        }
        return mjono;
    }
    
    private boolean onkoRuutuLaudalla(int korkeus, int leveys) {
        return korkeus >= 0 && korkeus < LAUDAN_KORKEUS && leveys >= 0 && leveys < LAUDAN_LEVEYS;
    }
}
