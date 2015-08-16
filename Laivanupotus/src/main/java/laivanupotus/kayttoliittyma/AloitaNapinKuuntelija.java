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
import laivanupotus.sovelluslogiikka.Peli;

/**
 *
 * @author Admin
 */
public class AloitaNapinKuuntelija implements ActionListener {

    private JTextArea teksti;
    private JTextArea pelaajanPisteet;
    private JTextArea tekoalynPisteet;
    private Peli peli;
    private JPanel pelaajanLauta;
    private JPanel tekoalynLauta;

    public AloitaNapinKuuntelija(JTextArea teksti, JTextArea pelaajanPisteet,
            JTextArea tekoalynPisteet, Peli peli, JPanel pelaajanLauta, JPanel tekoalynLauta) {
        this.teksti = teksti;
        this.pelaajanPisteet = pelaajanPisteet;
        this.tekoalynPisteet = tekoalynPisteet;
        this.peli = peli;
        this.pelaajanLauta = pelaajanLauta;
        this.tekoalynLauta = tekoalynLauta;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.peli.onkoPeliKaynnissa()) {
            alustaUusiPeli();
            return;
        }

        boolean onkoLaivatOk = this.peli.asetaPelaajanLaivat();
        if (onkoLaivatOk) {
            this.peli.asetaTekoalynLaivat();
            this.peli.setPeliKaynnissa(true);
            this.teksti.setText("Aloita peli ampumalla Tekoälyn lautaan.");
        } else {
            alustaUusiPeli();
            this.teksti.setText("Aseta laivat laudallesi oikein ja aloita peli Aloita-painikkeella.");
        }
    }

    private void alustaUusiPeli() {
        tyhjennaPelilaudat();
        this.teksti.setText(this.peli.tulostaOhje());
        this.pelaajanPisteet.setText("Pisteet: 0");
        this.tekoalynPisteet.setText("Pisteet: 0");
        this.peli.setPeliKaynnissa(false);
    }

    private void tyhjennaPelilaudat() {
        this.peli.alustaPeli();

        int ruutuja = this.pelaajanLauta.getComponentCount();
        for (int i = 0; i < ruutuja; i++) {
            Painike nappi = (Painike) this.pelaajanLauta.getComponent(i);
            nappi.setBackground(Color.white);
            nappi.setText("");
            nappi = (Painike) this.tekoalynLauta.getComponent(i);
            nappi.setBackground(Color.white);
            nappi.setText("");
        }
    }
}
