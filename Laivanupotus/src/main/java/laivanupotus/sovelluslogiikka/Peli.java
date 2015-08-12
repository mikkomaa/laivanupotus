/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.sovelluslogiikka;

import laivanupotus.domain.Ruutu;
import laivanupotus.tekoaly.Tekoaly;

/**
 *
 * @author Admin
 */
public class Peli {

    private Pelilauta pelaajanLauta;
    private Pelilauta tekoalynLauta;
    private Tekoaly tekoaly;
    private boolean peliKaynnissa;

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

    public void alustaPeli() {
        this.pelaajanLauta.alustaLauta();
        this.tekoalynLauta.alustaLauta();
        this.tekoaly.alustaTekoaly();
        this.peliKaynnissa = false;
    }

    public boolean asetaPelaajanLaivat() {
        return this.pelaajanLauta.lueLaivat();
    }

    public void asetaTekoalynLaivat() {
        this.tekoaly.asetaLaivat();
    }

    public boolean ovatkoLaivatUponneet() {
        return this.pelaajanLauta.ovatkoLaivatUponneet()
                || this.tekoalynLauta.ovatkoLaivatUponneet();
    }

    public void setPelaajanRuutu(int korkeus, int leveys, Ruutu ruutu) {
        this.pelaajanLauta.setRuutu(korkeus, leveys, ruutu);
    }

    public Ruutu getPelaajanRuutu(int korkeus, int leveys) {
        return this.pelaajanLauta.getRuutu(korkeus, leveys);
    }

    public Ruutu getTekoalynRuutu(int korkeus, int leveys) {
        return this.tekoalynLauta.getRuutu(korkeus, leveys);
    }

    public Ruutu ammuTekoalynLautaan(int korkeus, int leveys) {
        return this.tekoalynLauta.ammu(korkeus, leveys);
    }

    public int ammuPelaajanLautaan() {
        return this.tekoaly.ammu();
    }

    public String tulostaOhje() {
        return "Aseta hiirellä omalle laudallesi 4 laivaa. Laivojen koot ovat "
                + "1x1, 2x1, 3x1 ja 4x1 ruutua. Laivat eivät saa koskea toisiaan"
                + ", eivät edes kulmittain. Aloita sen jälkeen peli painamalla "
                + "Aloita-painiketta. Pelissä ammu hiirellä tekoälyn lautaan. "
                + "Pelin voittaa se, joka upottaa ensin toisen kaikki laivat. "
                + "Voit aloittaa uuden pelin milloin tahansa Aloita-painikkeella.";
    }
}
