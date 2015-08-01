/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.sovelluslogiikka;

import laivanupotus.domain.PisteidenLaskija;
import laivanupotus.domain.Ruutu;
import static laivanupotus.domain.Ruutu.*;
import static laivanupotus.domain.Vakiot.*;

/**
 *
 * @author Admin
 */
public class Pelilauta {
    
    private Ruutu[][] lauta;
    private PisteidenLaskija pisteidenLaskija;

    public Pelilauta() {
        this.lauta = new Ruutu[KORKEUS][LEVEYS];
        this.alustaLauta();
        this.pisteidenLaskija = new PisteidenLaskija();
    }

    public void alustaLauta() {
        for (int korkeus = 0; korkeus < KORKEUS; korkeus++) {
            for (int leveys = 0; leveys < LEVEYS; leveys++) {
                this.lauta[korkeus][leveys] = TYHJA;
            }
        }
    }

    public void setRuutu(int korkeus, int leveys, Ruutu ruutu) {
        if (onkoRuutuLaudalla(korkeus, leveys)) {
            this.lauta[korkeus][leveys] = ruutu;
        }
    }

    public Ruutu getRuutu(int korkeus, int leveys) {
        if (onkoRuutuLaudalla(korkeus, leveys)) {
            return this.lauta[korkeus][leveys];
        }
        return null;
    }
    
    public boolean lueLaivat() {
        LaivojenLukija lukija = new LaivojenLukija();
        return lukija.lueLaivat(this);
    }

    public Ruutu ammu(int korkeus, int leveys) {
        Ruutu ruutu = getRuutu(korkeus, leveys);
        if (ruutu == TYHJA) {
            setRuutu(korkeus, leveys, AMMUTTU);
        } else if (ruutu == LAIVA) {
            this.setRuutu(korkeus, leveys, OSUMA);
        }
        return getRuutu(korkeus, leveys);
    }

    public boolean ovatkoLaivatUponneet() {
        int osumia = 0;
        for (int korkeus = 0; korkeus < KORKEUS; korkeus++) {
            for (int leveys = 0; leveys < LEVEYS; leveys++) {
                if (this.lauta[korkeus][leveys] == OSUMA) {
                    osumia++;
                }
            }
        }
        return osumia == LAIVARUUTUJA;
    }
    
    public void lisaaPisteet(int maara) {
        this.pisteidenLaskija.lisaaPisteet(maara);
    }
    
    public void nollaaPisteet() {
        this.pisteidenLaskija.nollaaPisteet();
    }
    
    public int getPisteet() {
        return this.pisteidenLaskija.getPisteet();
    }
    
    @Override
    public String toString() {
        String mjono = "";
        for (int korkeus = 0; korkeus < KORKEUS; korkeus++) {
            for (int leveys = 0; leveys < LEVEYS; leveys++) {
                mjono += this.lauta[korkeus][leveys] + "\t";
            }
            mjono += "\n";
        }
        return mjono;
    }
    
    private boolean onkoRuutuLaudalla(int korkeus, int leveys) {
        return korkeus >= 0 && korkeus < KORKEUS && leveys >= 0 && leveys < LEVEYS;
    }
}
