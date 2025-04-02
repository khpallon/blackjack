import java.util.ArrayList;
import java.util.List;

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

    // Dealeri algne käsi, kus teine kaart on mängija eest peidetud

    public void peidetudKäsi(boolean algus) {
        System.out.print("| ");
        if(algus) {

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
