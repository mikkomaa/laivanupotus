/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.sovelluslogiikka;

import laivanupotus.domain.Ruutu;
import static laivanupotus.domain.Ruutu.*;
import static laivanupotus.domain.Vakiot.*;
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
        Ruutu ruutu = this.tekoalynLauta.ammu(korkeus, leveys);
        this.lisaaPisteet(this.pelaajanLauta, ruutu);
        return ruutu;
    }

    public int ammuPelaajanLautaan() {
        int koordinaatti = this.tekoaly.ammu();
        int korkeus = koordinaatti / 10;
        int leveys = koordinaatti % 10;
        Ruutu ruutu = this.pelaajanLauta.ammu(korkeus, leveys);
        this.tekoaly.paivitaAmmuksenTulos(ruutu);
        this.lisaaPisteet(this.tekoalynLauta, ruutu);
        return koordinaatti;
    }

    public int getTekoalynPisteet() {
        return this.tekoalynLauta.getPisteet();
    }

    public int getPelaajanPisteet() {
        return this.pelaajanLauta.getPisteet();
    }

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
