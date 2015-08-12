/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import laivanupotus.sovelluslogiikka.Peli;

/**
 *
 * @author Admin
 */
public class OhjeNapinKuuntelija implements ActionListener {

    private final JTextArea teksti;
    private final Peli peli;

    public OhjeNapinKuuntelija(JTextArea teksti, Peli peli) {
        this.teksti = teksti;
        this.peli = peli;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.teksti.setText(peli.tulostaOhje());
    }
}
