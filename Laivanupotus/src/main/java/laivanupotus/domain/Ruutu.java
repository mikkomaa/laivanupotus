package laivanupotus.domain;

/**
 * Luokka määrittelee pelilaudan ruutujen mahdolliset arvot
 */
public enum Ruutu {

    /**
     * Ruudussa ei ole laivaa, eikä ruutuun ole ammuttu
     */
    TYHJA,

    /**
     * Ruudussa on laiva, eikä ruutuun ole ammuttu
     */
    LAIVA,

    /**
     * Ruutu oli ensin tyhjä, ja ruutuun on ammuttu
     */
    AMMUTTU,

    /**
     * Ruudussa oli ensin laiva, ja ruutuun on ammuttu
     */
    OSUMA,

    /**
     * Apuarvo, jota LaivojenLukija käyttää toiminnassaan
     */
    LUETTU
}
