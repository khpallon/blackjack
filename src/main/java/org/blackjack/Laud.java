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
     * Klass, kus toimub mängu ette valmistamine ning mängu igavene loop
     * @param maja  Dealer
     * @param mängija   org.example.Mängija
     */
    
    public void mängi(Maja maja, Mängija mängija) throws IOException, ValeSisendErind {

        boolean lõpp = false;

        // Tühjendame kõik listid, mis võivad sisaldada eelmise mängu kaarte

        mängija.setKäsi(new ArrayList<>());
        maja.setKäsi(new ArrayList<>());
        this.pakk = new ArrayList<>();

        segaKaardiPakk();
        jagaKaardid(maja, mängija);


        // Mängu loop, mis töötab rekursiooni alusel

        while (!lõpp) {
            lõpp = protsess(maja, mängija, false);

        }
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
     * Informeerib mängijat kaartidest ning samuti küsib edasist tegevust
     * @param maja  Dealer
     * @param mängija   org.example.Mängija
     * @param peida Kontrollime, kas on vaja peita Dealeri teist kaarti
     * @return  Tagastame boolean väärtuse, et kas mängu lõpetada või mitte
     */

    public boolean protsess(Maja maja, Mängija mängija, boolean peida) throws ValeSisendErind {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*".repeat(30));
        System.out.println("Dealeri kaardid: ");
        maja.väljastaKäsi(!peida);

        System.out.println("\nSinu kaardid:");
        mängija.väljastaKäsi();

        // Kontrollime, kas mõlemad mängijad või üks on saanud kätte maksimum tulemuse või on seda ületanud

        if (koguVäärtus(mängija.getKäsi()) == 21 && koguVäärtus(maja.getKäsi()) == 21){
            System.out.println("*".repeat(30));
            System.out.println("Dealeri kaardid: ");
            maja.väljastaKäsi(false);
            System.out.println("\nSinu kaardid:");
            mängija.väljastaKäsi();

            System.out.println("Viik!");
            return true;
        } else if (koguVäärtus(mängija.getKäsi()) == 21){
            System.out.println("*".repeat(30));
            System.out.println("Dealeri kaardid: ");
            maja.väljastaKäsi(false);
            System.out.println("\nSinu kaardid:");
            mängija.väljastaKäsi();
            System.out.println("Blackjack! Palju õnne, võitsite mängu!");
            return true;
        } else if (koguVäärtus(mängija.getKäsi()) > 21) {
            System.out.println("\nBust! Kahjuks mängisite ennast lõhki.");
            return true;
        }

        System.out.println("\nVali tegevus:");
        System.out.println("1 - Juurde ; 2 - Jäta");
        System.out.println("*".repeat(30));
        int valik = scanner.nextInt();

        try {
            if (valik == 1){    // Lisame kaardi mängijale juurde
                mängija.lisaKaart(pakk.getFirst());
                pakk.removeFirst();
                System.out.println("\n".repeat(10));
                System.out.println("Dealer annab sulle kaardi juurde.");

            } else if (valik == 2) {    // org.example.Mängija ei võta kaardi juurde ning Dealer lõpetab oma käe ära
                System.out.println("\n".repeat(10));
                dealer(maja, mängija);
                return true;
            } else {    // Kui on vale sisend
                throw new ValeSisendErind("Vale sisend! Proovige uuesti.\n");
            }
        } catch (ValeSisendErind e) {
            System.out.println("\n".repeat(10));
            System.out.println(e.getMessage());
            protsess(maja, mängija, peida);
        }



        return false;
    }

    /**
     * Dealer automaatselt kogub kaarte kuniks jõuab väärtuseni 17 või Bustib
     * @param maja  Dealer
     * @param mängija   org.example.Mängija
     */

    public void dealer(Maja maja, Mängija mängija){
        System.out.println("*".repeat(30));
        System.out.println("Dealeri kaardid: ");
        maja.väljastaKäsi(false);

        System.out.println("\nSinu kaardid:");
        mängija.väljastaKäsi();

        int dealerVäärtus = koguVäärtus(maja.getKäsi());
        int mängijaVäärtus = koguVäärtus(mängija.getKäsi());

        // Kui dealeril kogu väärtuse summa on väiksem kui 17, siis võtab kaardi juurde

        while (dealerVäärtus < 17){
            System.out.println("*".repeat(30));
            System.out.println("Dealer võtab kaardi juurde.");
            maja.lisaKaart(pakk.get(0));
            pakk.remove(0);
            dealerVäärtus = koguVäärtus(maja.getKäsi());
            System.out.println("\nDealeri kaardid:");
            maja.väljastaKäsi(false);
            System.out.println("\nSinu kaardid:");
            mängija.väljastaKäsi();
            System.out.println("*".repeat(30));
        }


    }




    /**
     * Võtab pakist esimese kaardi ja tagastab selle.
     */
    public Kaart tõmbaKaart() {
        return pakk.remove(0);
    }

    /**
     * Diileri automaatkäik: jagab kaarte seni, kuni väärtus ≥17 või bustib.
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
