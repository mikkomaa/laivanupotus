/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.kayttoliittyma;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import laivanupotus.domain.Ruutu;
import static laivanupotus.domain.Ruutu.*;
import laivanupotus.sovelluslogiikka.Peli;

/**
 *
 * @author Admin
 */
public class PelaajanLaudanKuuntelija implements ActionListener {

    private final Peli peli;
    private final Painike painike;
    private final int korkeus;
    private final int leveys;

    public PelaajanLaudanKuuntelija(Painike painike, Peli peli) {
        this.peli = peli;
        this.painike = painike;
        this.korkeus = painike.getKorkeus();
        this.leveys = painike.getLeveys();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.peli.onkoPeliKaynnissa()) {
            return;
        }

        Ruutu ruutu = this.peli.getPelaajanRuutu(korkeus, leveys);
        if (ruutu == TYHJA) {
            this.peli.setPelaajanRuutu(korkeus, leveys, LAIVA);
            this.painike.setBackground(Color.lightGray);
        } else { // ruutu == LAIVA
            this.peli.setPelaajanRuutu(korkeus, leveys, TYHJA);
            this.painike.setBackground(Color.white);
        }
    }
}
