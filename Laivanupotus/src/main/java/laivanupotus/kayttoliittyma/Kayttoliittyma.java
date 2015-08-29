/*
 * Pakkaus sisältää graafisen käyttöliittymän luokat
 */
package laivanupotus.kayttoliittyma;

import laivanupotus.sovelluslogiikka.*;
import static laivanupotus.domain.Vakiot.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * Luokka luo käyttöliittymän graafiset komponentit
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private JTextArea teksti;
    private JTextArea pelaajanPisteet;
    private JTextArea tekoalynPisteet;
    private JPanel pelaajanLauta;
    private JPanel tekoalynLauta;
    private Peli peli;
    private final int ikkunanLeveys = 450;

    /**
     * Luokan konstruktori
     * 
     * @param peli Sovelluslogiikkapakkauksen peliolio
     */
    public Kayttoliittyma(Peli peli) {
        this.peli = peli;
    }

    @Override
    public void run() {
        frame = new JFrame("Laivanupotus");
        frame.setPreferredSize(new Dimension(this.ikkunanLeveys, 750));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        this.teksti = luoTekstikentta();
        luoPistekentat();
        luoPelilaudat();
        this.pelaajanLauta.setPreferredSize(new Dimension(this.ikkunanLeveys, 250));
        this.tekoalynLauta.setPreferredSize(new Dimension(this.ikkunanLeveys, 250));
        
        container.add(this.pelaajanPisteet);
        container.add(this.tekoalynPisteet);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(luoLaudanNimi("Pelaajan lauta"));
        container.add(this.pelaajanLauta);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(luoLaudanNimi("Tekoälyn lauta"));
        container.add(this.tekoalynLauta);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(luoPainikkeet());
        container.add(this.teksti);
    }

    private JTextArea luoTekstikentta() {
        JTextArea alue = new JTextArea(this.peli.tulostaOhje());
        alue.setEnabled(false);
        alue.setLineWrap(true);
        alue.setWrapStyleWord(true);
        alue.setDisabledTextColor(Color.black);
        return alue;
    }
    
    private void luoPistekentat() {
        this.pelaajanPisteet = new JTextArea("Pelaajan pisteet: 0");
        this.pelaajanPisteet.setEnabled(false);
        this.pelaajanPisteet.setDisabledTextColor(Color.black);
        this.tekoalynPisteet = new JTextArea("Tekoälyn pisteet: 0");
        this.tekoalynPisteet.setEnabled(false);
        this.tekoalynPisteet.setDisabledTextColor(Color.black);
    }

    private void luoPelilaudat() {
        this.pelaajanLauta = new JPanel(new GridLayout(LAUDAN_KORKEUS, LAUDAN_LEVEYS));
        this.tekoalynLauta = new JPanel(new GridLayout(LAUDAN_KORKEUS, LAUDAN_LEVEYS));

        for (int korkeus = 0; korkeus < LAUDAN_KORKEUS; korkeus++) {
            for (int leveys = 0; leveys < LAUDAN_LEVEYS; leveys++) {
                Painike painike = new Painike(korkeus, leveys);
                painike.setBackground(Color.white);
                painike.addActionListener(new PelaajanLaudanKuuntelija(painike, peli));
                this.pelaajanLauta.add(painike);

                painike = new Painike(korkeus, leveys);
                painike.setBackground(Color.white);
                painike.addActionListener(new TekoalynLaudanKuuntelija(painike,
                        teksti, pelaajanPisteet, tekoalynPisteet, peli, pelaajanLauta));
                this.tekoalynLauta.add(painike);
            }
        }
    }

    private JTextArea luoLaudanNimi(String nimi) {
        JTextArea nimikentta = new JTextArea(nimi);
        nimikentta.setEnabled(false);
        nimikentta.setDisabledTextColor(Color.black);
        return nimikentta;
    }

    private JPanel luoPainikkeet() {
        JPanel painikkeet = new JPanel(new GridLayout(1, 2));
        JButton aloita = new JButton("Aloita");
        aloita.addActionListener(new AloitaNapinKuuntelija(teksti,
                pelaajanPisteet, tekoalynPisteet, peli, pelaajanLauta, tekoalynLauta));
        JButton ohje = new JButton("Ohje");
        ohje.addActionListener(new OhjeNapinKuuntelija(teksti, peli));
        painikkeet.add(aloita);
        painikkeet.add(ohje);
        return painikkeet;
    }
}
