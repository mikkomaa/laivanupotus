package laivanupotus.main;

import javax.swing.SwingUtilities;
import laivanupotus.kayttoliittyma.Kayttoliittyma;
import laivanupotus.sovelluslogiikka.Peli;

/**
 * Pääohjelmaluokka pelin käynnistämiseen
 */
public class Main {

    /**
     * Metodi luo pelin ja käyttöliittymän sekä käynnistää pelin
     * 
     * @param args Komentoriviparametrit. Pelissä ei niitä ole.
     */
    public static void main(String args[]) {
        Peli peli = new Peli();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(peli);
        SwingUtilities.invokeLater(kayttoliittyma);
    }
}
