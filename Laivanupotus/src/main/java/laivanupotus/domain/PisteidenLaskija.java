package laivanupotus.domain;

/**
 * Luokka pitää kirjaa pelilautaan liittyvistä pisteistä
 */
public class PisteidenLaskija {

    private int pisteet;

    /**
     * Luokan konstruktori, joka asettaa pisteet nollaksi
     */
    public PisteidenLaskija() {
        this.pisteet = 0;
    }

    public int getPisteet() {
        return this.pisteet;
    }

    /**
     * Metodi lisää pisteitä
     * 
     * @param maara Lisättävien pisteiden määrä, joka voi olla myös negatiivinen
     */
    public void lisaaPisteet(int maara) {
        this.pisteet += maara;
    }

    /**
     * Metodi asettaa pisteet nollaksi
     */
    public void nollaaPisteet() {
        this.pisteet = 0;
    }

    @Override
    public String toString() {
        return "pisteet = " + this.pisteet;
    }
}
