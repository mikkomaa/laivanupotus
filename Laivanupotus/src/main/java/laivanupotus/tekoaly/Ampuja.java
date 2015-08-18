package laivanupotus.tekoaly;

import java.util.Random;
import java.util.Stack;
import laivanupotus.domain.Ruutu;
import static laivanupotus.domain.Ruutu.*;
import static laivanupotus.domain.Vakiot.*;
import laivanupotus.sovelluslogiikka.Pelilauta;

/**
 * Luokka etsii tekoälylle seuraavan kohdan, johon se ampuu pelaajan lautaan
 */
public class Ampuja {

    // Ampujan sisäinen lauta, jolla se pitää kirjaa ammunnan tuloksesta
    private Pelilauta sisainenLauta;
    // Pinon avulla ampuja upottaa pelaajan laivan yhden osuman jälkeen
    private Stack<Integer> pino;
    // Koordinaatti vastaa pelilaudan ruutua 10 * korkeus + leveys,
    // eli laillinen koordinaatti on välillä [0, 99]
    private int viimeisinKoordinaatti;

    /**
     * Konstruktori, joka luo ampujan
     */
    public Ampuja() {
        this.sisainenLauta = new Pelilauta();
        this.pino = new Stack<>();
        this.viimeisinKoordinaatti = -1;
    }

    /**
     * Metodi alustaa ampujan uutta peliä varten
     */
    public void alustaAmpuja() {
        this.sisainenLauta.alustaLauta();
        this.pino.clear();
        this.viimeisinKoordinaatti = -1;
    }

    /**
     * Metodi etsii ampumakoordinaatin. Jos aiemmin on tullut osuma, ammutaan
     * osuman ympärille, kunnes pelaajan laiva on varmasti uponnut. Muulloin 
     * koordinaatti arvotaan.
     * 
     * @return Palauttaa ampumakoordinaatin
     */
    public int etsiAmpumakoordinaatti() {
        Integer koordinaatti = haeKoordinaattiPinosta();
        if (koordinaatti == null) { // edellinen laiva on varmuudella upotettu
            koordinaatti = arvoKoordinaatti();
        }
        this.viimeisinKoordinaatti = koordinaatti;
        return koordinaatti;
    }

    /**
     * Metodi päivittää ampujan tilan edellisen ammuksen tuloksen perusteella
     * 
     * @param ruutu Edellisen ammuksen tulos (OSUMA vai jokin muu)
     */
    public void paivitaSisainenLauta(Ruutu ruutu) {
        int korkeus = this.viimeisinKoordinaatti / 10;
        int leveys = this.viimeisinKoordinaatti % 10;
        this.sisainenLauta.setRuutu(korkeus, leveys, ruutu);
        if (ruutu == OSUMA) {
            asetaVinoruudutAmmutuiksi(korkeus, leveys);
            this.pino.push(this.viimeisinKoordinaatti);
        }
    }

    @Override
    public String toString() {
        return "" + sisainenLauta + "pino = " + pino
                + "\nviimeisinKoordinaatti = " + viimeisinKoordinaatti;
    }

    // Arpoo ampumakoordinaatin, jos edellisen osuman jälkihoito on päättynyt
    private int arvoKoordinaatti() {
        int korkeus, leveys;
        Ruutu ruutu;
        Random arpa = new Random();
        do {
            korkeus = arpa.nextInt(LAUDAN_KORKEUS);
            leveys = arpa.nextInt(LAUDAN_LEVEYS);
            ruutu = this.sisainenLauta.getRuutu(korkeus, leveys);
        } while (ruutu != TYHJA);
        return 10 * korkeus + leveys;
    }   

    private Integer haeKoordinaattiPinosta() {
        Integer koordinaatti = null;
        while (!this.pino.empty()) {
            koordinaatti = this.pino.peek();
            koordinaatti = haeVierestaKoordinaatti(koordinaatti);
            if (koordinaatti == null) { // ei ammuttavaa koordinaatin vieressä
                this.pino.pop();
            } else { // löytyi ammuttavaa koordinaatin vierestä
                break;
            }
        }
        return koordinaatti;
    }

    private Integer haeVierestaKoordinaatti(Integer koordinaatti) {
        int korkeus = koordinaatti / 10;
        int leveys = koordinaatti % 10;
        if (this.sisainenLauta.getRuutu(korkeus - 1, leveys) == TYHJA) {
            return 10 * (korkeus - 1) + leveys;
        } else if (this.sisainenLauta.getRuutu(korkeus, leveys + 1) == TYHJA) {
            return 10 * korkeus + (leveys + 1);
        } else if (this.sisainenLauta.getRuutu(korkeus + 1, leveys) == TYHJA) {
            return 10 * (korkeus + 1) + leveys;
        } else if (this.sisainenLauta.getRuutu(korkeus, leveys - 1) == TYHJA) {
            return 10 * korkeus + (leveys - 1);
        }
        return null;
    }

    private void asetaVinoruudutAmmutuiksi(int korkeus, int leveys) {
        this.sisainenLauta.setRuutu(korkeus - 1, leveys - 1, AMMUTTU);
        this.sisainenLauta.setRuutu(korkeus - 1, leveys + 1, AMMUTTU);
        this.sisainenLauta.setRuutu(korkeus + 1, leveys - 1, AMMUTTU);
        this.sisainenLauta.setRuutu(korkeus + 1, leveys + 1, AMMUTTU);
    }
}
