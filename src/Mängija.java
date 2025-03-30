import java.util.ArrayList;
import java.util.List;

public class Mängija {
    private List<Kaart> käsi = new ArrayList<>();


    public void setKäsi(List<Kaart> käsi) {
        this.käsi = käsi;
    }

    // Väljastab kaardid

    public void väljastaKäsi() {
        System.out.print("| ");
        for (int i = 0; i < käsi.size(); i++) {
                System.out.print(käsi.get(i) + " | ");
        }
        System.out.println();
    }

    public List<Kaart> getKäsi() {
        return käsi;
    }

    public void lisaKaart(Kaart kaart){
        käsi.add(kaart);
    }
}
