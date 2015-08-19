package laivanupotus.kayttoliittyma;

import javax.swing.JButton;

/**
 * Luokka määrittelee painikkeen, joka vastaa käyttöliittymän graafisen
 * pelilaudan yhtä ruutua
 */
public class Painike extends JButton {

    private int korkeus;
    private int leveys;

    /**
     * Luokan konstruktori
     * 
     * @param korkeus Painikkeen y-koordinaatti graafisella pelilaudalla
     * @param leveys Painikkeen x-koordinaatti graafisella pelilaudalla
     */
    public Painike(int korkeus, int leveys) {
        this.korkeus = korkeus;
        this.leveys = leveys;
    }

    public int getKorkeus() {
        return this.korkeus;
    }

    public int getLeveys() {
        return this.leveys;
    }
}
