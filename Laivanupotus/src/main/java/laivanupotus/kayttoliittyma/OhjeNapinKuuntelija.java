package laivanupotus.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import laivanupotus.sovelluslogiikka.Peli;

/**
 * Luokka määrittelee Ohje-napin toiminnallisuuden
 */
public class OhjeNapinKuuntelija implements ActionListener {

    private final JTextArea teksti;
    private final Peli peli;

    /**
     * Luokan konstruktori
     * 
     * @param teksti Käyttöliittymän ohjetekstialua
     * @param peli Sovelluslogiikkapakkauksen peliolio
     */
    public OhjeNapinKuuntelija(JTextArea teksti, Peli peli) {
        this.teksti = teksti;
        this.peli = peli;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.teksti.setText(peli.tulostaOhje());
    }
}
