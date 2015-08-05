/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.tekoaly;

import java.util.Random;
import java.util.Stack;
import laivanupotus.domain.Ruutu;
import static laivanupotus.domain.Ruutu.*;
import static laivanupotus.domain.Vakiot.*;
import laivanupotus.sovelluslogiikka.Pelilauta;

/**
 *
 * @author Admin
 */
public class Ampuja {

    private Pelilauta sisainenLauta;
    private Stack<Integer> pino;
    private int viimeisinKoordinaatti;

    public Ampuja() {
        this.sisainenLauta = new Pelilauta();
        this.pino = new Stack<>();
        this.viimeisinKoordinaatti = -1;
    }

    public void alustaAmpuja() {
        this.sisainenLauta.alustaLauta();
        this.pino.clear();
        this.viimeisinKoordinaatti = -1;
    }

    public int etsiAmpumakoordinaatti() {
        Integer koordinaatti = haeKoordinaattiPinosta();
        if (koordinaatti == null) {
            koordinaatti = arvoKoordinaatti();
        }
        this.viimeisinKoordinaatti = koordinaatti;
        return koordinaatti;
    }

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
            if (koordinaatti == null) {
                this.pino.pop();
            } else {
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
