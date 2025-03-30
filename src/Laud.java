import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Laud {

    private String[] tähised = {"A", "J", "Q", "K", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private String[] mastid = {"♠", "♥", "♣", "♦"};
    private List<Kaart> pakk;
    
    public void alusta(Maja maja, Mängija mängija){


        // Tühjendame kõik listid, mis sisaldavad kaarte

        mängija.setKäsi(new ArrayList<>());
        maja.setKäsi(new ArrayList<>());
        this.pakk = new ArrayList<>();

        boolean lõpp = false;

        segaKaardiPakk();
        jagaKaardid(maja, mängija);


        // Mängu loop, mis töötab rekursiooni alusel

        while (!lõpp){
            lõpp = protsess(maja,mängija, false);


            // Kui mäng sai läbi siis valik, kas uuesti teha või lahkuda

            if (lõpp){
                Scanner scanner = new Scanner(System.in);
                System.out.println("\nValige edasine tegevus:");
                System.out.println("1 - Mängi uuesti ; 2 - Lahku");
                int valik = scanner.nextInt();
                switch (valik){
                    case 1:
                        System.out.println("\n".repeat(10));
                        alusta(maja, mängija);
                    case 2: break;
                }
            }
        }

    }

    // Lisab pakk muutujale 52 kaarti ning segab kõik ära

    public void segaKaardiPakk(){

        for (String s : tähised) {
            for (String string : mastid) {
                pakk.add(new Kaart(s, string));
            }
        }

        Collections.shuffle(pakk);
    }

    // Jagame algsed kaardid Dealeri ja Mängija vahel ära

    public void jagaKaardid(Maja maja, Mängija mängija){
        for (int i = 0; i < 4; i++) {

            if (i % 2 == 0) {
                maja.lisaKaart(pakk.getFirst());

            } else {
                mängija.lisaKaart(pakk.getFirst());
            }

            pakk.removeFirst();
        }
    }


    // Arvutab Mängija või Dealeri kogu käe väärtuse

    public int koguVäärtus(List<Kaart> kaardid){
        int summa = 0;
        for (Kaart kaart : kaardid) {
            summa += kaart.getVäärtus();
        }
        return summa;
    }

    // (WIP) Informeerib mängijat kaartidest ning samuti küsib edasist tegevust

    public boolean protsess(Maja maja, Mängija mängija, boolean peida){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nDealeri kaardid: ");
        maja.peidetudKäsi(!peida);

        System.out.println("\nSinu kaardid:");
        mängija.väljastaKäsi();

        if (koguVäärtus(mängija.getKäsi()) == 21){
            System.out.println("Blackjack! Palju õnne, võitsite mängu!");
            return true;
        } else if (koguVäärtus(mängija.getKäsi()) > 21) {
            System.out.println("\nBust! Kahjuks mängisite ennast lõhki.");
            return true;
        }

        System.out.println("\nVali tegevus:");
        System.out.println("1 - Juurde ; 2 - Jäta");

        int valik = scanner.nextInt();

        if (valik == 1){
            mängija.lisaKaart(pakk.getFirst());
            pakk.removeFirst();
            System.out.println("\n".repeat(10));
            System.out.println("Dealer annab sulle kaardi juurde.");


        } else if (valik == 2) {
            System.out.println("\n".repeat(10));
            if (dealer(maja, mängija)){
                System.out.println("\n".repeat(10));
                System.out.println("Dealer võidab!");
                return true;
            }
        } else {
            System.out.println("\n".repeat(10));
            System.out.println("Arusaamatu tegevus! Proovi uuesti!");
            protsess(maja, mängija, peida);
        }

        return false;
    }


    // (WIP) Dealer automaatselt kogub kaarte kuniks jõuab väärtuseni 17 või Bustib

    public boolean dealer(Maja maja, Mängija mängija){

        System.out.println("\nDealeri kaardid: ");
        maja.peidetudKäsi(false);

        System.out.println("\nSinu kaardid:");
        mängija.väljastaKäsi();

        System.out.println(koguVäärtus(maja.getKäsi()));

        while (koguVäärtus(maja.getKäsi()) <= 17){
            System.out.println("Dealer võtab kaardi juurde.");
            maja.lisaKaart(pakk.getFirst());
            pakk.removeFirst();
            System.out.println("\n".repeat(10));

        }

        return true;
    }


}
