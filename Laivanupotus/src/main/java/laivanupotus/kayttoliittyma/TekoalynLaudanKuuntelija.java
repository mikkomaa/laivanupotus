/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.kayttoliittyma;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import laivanupotus.domain.Ruutu;
import static laivanupotus.domain.Ruutu.*;
import laivanupotus.sovelluslogiikka.Peli;

/**
 *
 * @author Admin
 */
public class TekoalynLaudanKuuntelija implements ActionListener {

    private final Peli peli;
    private final JPanel pelaajanLauta;
    private final JTextArea teksti;
    private JTextArea pelaajanPisteet;
    private JTextArea tekoalynPisteet;
    private final Painike painike;
    private final int korkeus;
    private final int leveys;

    public TekoalynLaudanKuuntelija(Painike painike, JTextArea teksti,
            JTextArea pelaajanPisteet, JTextArea tekoalynPisteet,
            Peli peli, JPanel pelaajanLauta) {
        this.peli = peli;
        this.pelaajanLauta = pelaajanLauta;
        this.teksti = teksti;
        this.pelaajanPisteet = pelaajanPisteet;
        this.tekoalynPisteet = tekoalynPisteet;
        this.painike = painike;
        this.korkeus = painike.getKorkeus();
        this.leveys = painike.getLeveys();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!onkoTehtavaa()) {
            return;
        }

        hoidaPelaajanVuoro();
        if (this.peli.ovatkoLaivatUponneet()) { // Pelaaja voitti.
            this.teksti.setText("Voitit!\n\nVoit aloittaa uuden pelin Aloita-napista.");
            this.peli.setPeliKaynnissa(false);
            return;
        }

        hoidaTekoalynVuoro();
        if (this.peli.ovatkoLaivatUponneet()) { // Tekoäly voitti.
            this.teksti.setText("Hävisit.\n\nVoit aloittaa uuden pelin Aloita-napista.");
            this.peli.setPeliKaynnissa(false);
        }
    }

    private boolean onkoTehtavaa() {
        Ruutu ruutu = this.peli.getTekoalynRuutu(this.korkeus, this.leveys);
        return this.peli.onkoPeliKaynnissa() && ruutu != AMMUTTU && ruutu != OSUMA;
    }

    private void hoidaPelaajanVuoro() {
        this.peli.ammuTekoalynLautaan(this.korkeus, this.leveys);
        Ruutu ruutu = this.peli.getTekoalynRuutu(this.korkeus, this.leveys);
        muutaRuudunUlkonako(ruutu, this.painike);
        paivitaPisteet();
    }

    private void hoidaTekoalynVuoro() {
        int koordinaatti = peli.ammuPelaajanLautaan();
        Painike nappi = (Painike) this.pelaajanLauta.getComponent(koordinaatti);
        Ruutu ruutu = this.peli.getPelaajanRuutu(nappi.getKorkeus(), nappi.getLeveys());
        muutaRuudunUlkonako(ruutu, nappi);
        paivitaPisteet();
    }

    private void muutaRuudunUlkonako(Ruutu ruutu, Painike painike) {
        if (ruutu == AMMUTTU) {
            painike.setText("x");
        } else { // ruutu == OSUMA
            painike.setText("x");
            painike.setBackground(Color.lightGray);
        }
    }
    
    private void paivitaPisteet() {
        int pisteet = this.peli.getPelaajanPisteet();
        this.pelaajanPisteet.setText("Pisteet: " + pisteet);
        pisteet = this.peli.getTekoalynPisteet();
        this.tekoalynPisteet.setText("Pisteet: " + pisteet);
    }
}
