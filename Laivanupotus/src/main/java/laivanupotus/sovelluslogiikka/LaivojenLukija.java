package laivanupotus.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
import laivanupotus.domain.Laiva;
import laivanupotus.domain.Ruutu;
import static laivanupotus.domain.Ruutu.*;
import static laivanupotus.domain.Vakiot.*;

/**
 * Luokka tarkistaa, ovatko pelilaudalle sijoitetut laivat oikein
 */
public class LaivojenLukija {

    private Pelilauta lauta;
    private List<Laiva> laivat;

    /**
     * Luokan konstruktori
     */
    public LaivojenLukija() {
        this.lauta = new Pelilauta();
        this.laivat = new ArrayList<>();
    }

    /**
     * Metodi tarkistaa, ovatko laivat oikein pelilaudalla. Metodi ei muuta
     * tarkistettavaa pelilautaa mitenkään.
     * 
     * @param pelilauta Pelilauta, jonka laivat tarkistetaan
     * 
     * @return Palauttaa true, jos laivat ovat oikein, muuten false
     */
    public boolean lueLaivat(Pelilauta pelilauta) {
        this.laivat.clear();
        kopioiPelilauta(pelilauta);
        if (onkoLaivojaKulmittain()) {
            return false;
        }
        etsiLaivatLaudalta();
        if (!onkoLaivojaOikeaMaara() || !onkoLaivojenKootOikein()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String mjono = "";
        for (Laiva laiva : this.laivat) {
            mjono += laiva.toString() + "\n";
        }
        return mjono;
    }

    private void kopioiPelilauta(Pelilauta pelilauta) {
        Ruutu ruutu;
        for (int korkeus = 0; korkeus < LAUDAN_KORKEUS; korkeus++) {
            for (int leveys = 0; leveys < LAUDAN_LEVEYS; leveys++) {
                ruutu = pelilauta.getRuutu(korkeus, leveys);
                this.lauta.setRuutu(korkeus, leveys, ruutu);
            }
        }
    }

    private void etsiLaivatLaudalta() {
        Ruutu ruutu;
        for (int korkeus = 0; korkeus < LAUDAN_KORKEUS; korkeus++) {
            for (int leveys = 0; leveys < LAUDAN_LEVEYS; leveys++) {
                ruutu = this.lauta.getRuutu(korkeus, leveys);
                if (ruutu == LAIVA) {
                    luoLaiva(korkeus, leveys);
                }
            }
        }
    }

    private void luoLaiva(int korkeus, int leveys) {
        Laiva laiva = new Laiva();
        maaritaLaivanSijainti(korkeus, leveys, laiva);
        this.laivat.add(laiva);
    }

    // Jos parametrina annetussa kohdassa on laivan osa, luetaan koko laivan
    // sijainti parametrina annettuun laivaan. Oletetaan, että parametrin
    // laivalla ei ole sijaintia ennestään.
    private void maaritaLaivanSijainti(int korkeus, int leveys, Laiva laiva) {
        Ruutu ruutu = this.lauta.getRuutu(korkeus, leveys);
        if (ruutu != LAIVA) {
            return;
        }

        laiva.lisaaRuutu(korkeus, leveys);
        this.lauta.setRuutu(korkeus, leveys, LUETTU); // Jotta ei lueta samaa ruutua uudestaan.
        maaritaLaivanSijainti(korkeus + 1, leveys, laiva);
        maaritaLaivanSijainti(korkeus, leveys + 1, laiva);
    }

    private boolean onkoLaivojaKulmittain() {
        Ruutu ruutu;
        for (int korkeus = 0; korkeus < LAUDAN_KORKEUS; korkeus++) {
            for (int leveys = 0; leveys < LAUDAN_LEVEYS; leveys++) {
                ruutu = this.lauta.getRuutu(korkeus, leveys);
                if (ruutu == LAIVA && onkoRuudunAlakulmassaLaiva(korkeus, leveys)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean onkoRuudunAlakulmassaLaiva(int korkeus, int leveys) {
        return this.lauta.getRuutu(korkeus + 1, leveys - 1) == LAIVA
                || this.lauta.getRuutu(korkeus + 1, leveys + 1) == LAIVA;
    }

    private boolean onkoLaivojaOikeaMaara() {
        return this.laivat.size() == LAIVOJA;
    }

    private boolean onkoLaivojenKootOikein() {
        for (int koko = 1; koko <= 4; koko++) {
            boolean loytyi = false;
            for (Laiva laiva : this.laivat) {
                if (laiva.getKoko() == koko) {
                    loytyi = true;
                }
            }
            if (!loytyi) {
                return false;
            }
        }
        return true;
    }
}
