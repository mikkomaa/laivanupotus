/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
import laivanupotus.domain.Laiva;
import laivanupotus.domain.Ruutu;
import static laivanupotus.domain.Ruutu.*;
import static laivanupotus.domain.Vakiot.*;

/**
 *
 * @author Admin
 */
public class LaivojenLukija {

    private Pelilauta lauta;
    private List<Laiva> laivat;

    public LaivojenLukija() {
        this.lauta = new Pelilauta();
        this.laivat = new ArrayList<>();
    }

    public boolean lueLaivat(Pelilauta pelilauta) {
        kopioiPelilauta(pelilauta);
        if (onkoLaivojaKulmittain()) {
            return false;
        }
        etsiLaivatLaudalta();
        if (!onkoLaivojaOikeaMaara() || !onkoLaivojenKootOikein()) {
            this.laivat.clear();
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
        for (int korkeus = 0; korkeus < KORKEUS; korkeus++) {
            for (int leveys = 0; leveys < LEVEYS; leveys++) {
                ruutu = pelilauta.getRuutu(korkeus, leveys);
                this.lauta.setRuutu(korkeus, leveys, ruutu);
            }
        }
    }

    private void etsiLaivatLaudalta() {
        Ruutu ruutu;
        for (int korkeus = 0; korkeus < KORKEUS; korkeus++) {
            for (int leveys = 0; leveys < LEVEYS; leveys++) {
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
        maaritaLaivanSijainti(korkeus - 1, leveys, laiva);
        maaritaLaivanSijainti(korkeus + 1, leveys, laiva);
        maaritaLaivanSijainti(korkeus, leveys - 1, laiva);
        maaritaLaivanSijainti(korkeus, leveys + 1, laiva);
    }

    private boolean onkoLaivojaKulmittain() {
        Ruutu ruutu;
        for (int korkeus = 0; korkeus < KORKEUS; korkeus++) {
            for (int leveys = 0; leveys < LEVEYS; leveys++) {
                ruutu = this.lauta.getRuutu(korkeus, leveys);
                if (ruutu == LAIVA && onkoRuudunKulmassaLaiva(korkeus, leveys)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean onkoRuudunKulmassaLaiva(int korkeus, int leveys) {
        return this.lauta.getRuutu(korkeus - 1, leveys - 1) == LAIVA
                || this.lauta.getRuutu(korkeus - 1, leveys + 1) == LAIVA
                || this.lauta.getRuutu(korkeus + 1, leveys - 1) == LAIVA
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
