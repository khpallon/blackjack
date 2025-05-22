package org.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * org.example.Mängija klass, kus hoiame ning väljastame mängija kaardid ehk "käsi"
 */

public class Mängija {
    private List<Kaart> käsi = new ArrayList<>();

    public void setKäsi(List<Kaart> käsi) {
        this.käsi = käsi;
    }

    public List<Kaart> getKäsi() {
        return käsi;
    }

    public void lisaKaart(Kaart kaart){
        käsi.add(kaart);
    }

    /**
     * Väljastab ekraanile mängija kaardid
     */

    public void väljastaKäsi() {
        System.out.print("| ");
        for (int i = 0; i < käsi.size(); i++) {
                System.out.print(käsi.get(i) + " | ");
        }
        System.out.println();
    }
}
