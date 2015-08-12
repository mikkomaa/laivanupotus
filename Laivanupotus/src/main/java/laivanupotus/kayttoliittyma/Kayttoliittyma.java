/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Admin
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private JTextArea teksti;
    private JPanel pelaajanLauta;
    private JPanel tekoalynLauta;
    private Peli peli;
    private final int IKKUNAN_LEVEYS = 450;

    public Kayttoliittyma(Peli peli) {
        this.peli = peli;
    }

    @Override
    public void run() {
        frame = new JFrame("Laivanupotus");
        frame.setPreferredSize(new Dimension(this.IKKUNAN_LEVEYS, 750));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        this.teksti = luoTekstikentta();
        luoPelilaudat();
        this.pelaajanLauta.setPreferredSize(new Dimension(this.IKKUNAN_LEVEYS, 250));
        this.tekoalynLauta.setPreferredSize(new Dimension(this.IKKUNAN_LEVEYS, 250));
        container.add(luoNimi("Pelaaja"));
        container.add(this.pelaajanLauta);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(luoNimi("Tekoäly"));
        container.add(this.tekoalynLauta);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(luoPainikkeet());
        container.add(this.teksti);
    }

    public JFrame getFrame() {
        return frame;
    }

    private JTextArea luoTekstikentta() {
        JTextArea alue = new JTextArea(this.peli.tulostaOhje());
        alue.setEnabled(false);
        alue.setLineWrap(true);
        alue.setWrapStyleWord(true);
        return alue;
    }

    private void luoPelilaudat() {
        this.pelaajanLauta = new JPanel(new GridLayout(LAUDAN_KORKEUS, LAUDAN_LEVEYS));
        this.tekoalynLauta = new JPanel(new GridLayout(LAUDAN_KORKEUS, LAUDAN_LEVEYS));

        for (int korkeus = 0; korkeus < LAUDAN_KORKEUS; korkeus++) {
            for (int leveys = 0; leveys < LAUDAN_LEVEYS; leveys++) {
                Painike painike = new Painike(korkeus, leveys);
                painike.setBackground(Color.white);
                //painike.addActionListener(new PelaajanLaudanKuuntelija(painike, peli));
                this.pelaajanLauta.add(painike);

                painike = new Painike(korkeus, leveys);
                painike.setBackground(Color.white);
                //painike.addActionListener(new TekoalynLaudanKuuntelija(painike, teksti, peli, pelaajanLauta));
                this.tekoalynLauta.add(painike);
            }
        }
    }

    private JPanel luoNimi(String nimi) {
        JPanel paneeli = new JPanel();
        JTextField kentta = new JTextField(nimi);
        kentta.setEnabled(false);
        paneeli.add(kentta);
        return paneeli;
    }

    private JPanel luoPainikkeet() {
        JPanel painikkeet = new JPanel(new GridLayout(1, 2));
        JButton aloita = new JButton("Aloita");
//        aloita.addActionListener(new AloitaNapinKuuntelija(teksti, peli,
//                pelaajanLauta, tekoalynLauta));
        JButton ohje = new JButton("Ohje");
        ohje.addActionListener(new OhjeNapinKuuntelija(teksti, peli));
        painikkeet.add(aloita);
        painikkeet.add(ohje);
        return painikkeet;
    }
}
