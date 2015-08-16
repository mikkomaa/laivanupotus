package laivanupotus.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka määrittelee pelilaudalla olevan laivan, ja LaivojenLukija käyttää
 * luokkaa apuna lukiessaan pelilaudan laivat
 */
public class Laiva {

    private List<Integer> sijainti;

    /**
     * Luokan konstruktori
     */
    public Laiva() {
        this.sijainti = new ArrayList<>();
    }

    /**
     * Metodi lisää laivaan osan eli pelilaudan tietyn ruudun
     * 
     * @param korkeus Laivan osan y-koordinaatti pelilaudalla
     * @param leveys Laivan osan x-koordinaatti pelilaudalla
     */
    public void lisaaRuutu(int korkeus, int leveys) {
        this.sijainti.add(10 * korkeus + leveys);
    }

    /**
     * Metodi tarkistaa, onko tietyssä pelilaudan ruudussa laiva
     * 
     * @param korkeus Tarkistettavan ruudun y-koordinaatti pelilaudalla
     * @param leveys Tarkistettavan ruudun x-koordinaatti pelilaudalla
     * 
     * @return Palauttaa true, jos ruudussa on laiva, muuten false
     */
    public boolean onkoLaivaRuudussa(int korkeus, int leveys) {
        int tarkistettava = 10 * korkeus + leveys;
        for (Integer ruutu : this.sijainti) {
            if (ruutu == tarkistettava) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi kertoo laivan koon ruutuina
     * 
     * @return Palauttaa laivan koon ruutuina
     */
    public int getKoko() {
        return this.sijainti.size();
    }

    @Override
    public String toString() {
        return "Laivan sijainti on " + this.sijainti;
    }
}
