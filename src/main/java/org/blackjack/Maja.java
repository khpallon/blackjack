package org.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * org.example.Maja klass, kus tegeleme Dealeri kaartidega
 */

public class Maja {
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
     * Väljastame Dealeri kaardid ekraanile nii, et esimene kaart on nähtav ning teine peidetud
     * @param peida Boolean väärtus, millega kontrollime, kas on vaja teist kaarti peita või mitte
     */

    public void väljastaKäsi(boolean peida) {
        System.out.print("| ");

        if(peida) {
            for (int i = 0; i < 2 && i < käsi.size(); i++) {
                if (i == 1) {
                    System.out.print("XX" + " | ");
                } else {
                    System.out.print(käsi.get(i) + " | ");
                }
            }
        } else {
            for (Kaart kaart : käsi) {
                System.out.print(kaart + " | ");
            }
        }
        System.out.println();
    }


}
