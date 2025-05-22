package org.blackjack;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Laua klass, kus tegeleme mängu üldloogikaga
 */

public class Laud {

    private String[] tähised = {"A", "J", "Q", "K", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private String[] mastid = {"♠", "♥", "♣", "♦"};
    private List<Kaart> pakk;   // List, mis sisaldab 52 mängukaarti

    // konstruktor, et initsialiseerida paki list ühekordselt
    public Laud() {
        this.pakk = new ArrayList<>();
    }








    /**
     * Lisab pakk muutujale 52 kaarti ning segab need ära
     */

    public void segaKaardiPakk(){

        pakk.clear();

        for (String s : tähised) {
            for (String string : mastid) {
                pakk.add(new Kaart(s, string));
            }
        }

        Collections.shuffle(pakk);
    }

    /**
     * Lisame Dealerile ja Mängijale 2 kaarti ning eemaldame need pakkist
     * @param maja  Dealer
     * @param mängija   org.example.Mängija
     */

    public void jagaKaardid(Maja maja, Mängija mängija){
        for (int i = 0; i < 4; i++) {
            Kaart kaart = pakk.remove(0);

            // Aususe mõttes, jagame kaardid ükshaaval

            if (i % 2 == 0) {
                maja.lisaKaart(kaart);

            } else {
                mängija.lisaKaart(kaart);
            }

        }
    }




    /**
     * Arvutab org.example.Mängija või Dealeri kogu käe väärtuse
     * @param kaardid   Kaardid, mille väärtust arvutada
     * @return  Tagastame kaardi väärtuste summa
     */

    public int koguVäärtus(List<Kaart> kaardid){
        int summa = 0;
        int ässad = 0;

        for (Kaart kaart : kaardid) {
            summa += kaart.getVäärtus();
            if (kaart.getTähis().equals("A")) {
                ässad++;
            }
        }

        // Kontrollime, kui kellegil on äss ning on lubatud väärtuse ületanud siis alandame selle väärtust 10 võrra

        while (summa > 21 && ässad > 0) {
            summa -=10;
            ässad--;
        }

        return summa;
    }









    /**
     * Võtab pakist esimese kaardi ja tagastab selle.
     */
    public Kaart tõmbaKaart() {
        return pakk.remove(0);
    }

    /**
     * Dealer automaatselt kogub kaarte kuniks jõuab väärtuseni 17 või Bustib
     * @param maja  Dealer
     * @param mängija   org.example.Mängija
     */
    public void dealeriKord(Maja maja, Mängija mängija) {
        int dealerVäärtus = koguVäärtus(maja.getKäsi());
        while (dealerVäärtus < 17) {
            maja.lisaKaart(tõmbaKaart());
            dealerVäärtus = koguVäärtus(maja.getKäsi());
        }
    }

    /**
     * Tagastab käe tekstina, peites soovi korral diileri teise kaardi.
     */
    public String getStringKäsi(List<Kaart> käsi, boolean peidaTeine) {
        StringBuilder sb = new StringBuilder("| ");
        for (int i = 0; i < käsi.size(); i++) {
            if (i == 1 && peidaTeine) {
                sb.append("XX");
            } else {
                sb.append(käsi.get(i));
            }
            sb.append(" | ");
        }
        return sb.toString();
    }
}
