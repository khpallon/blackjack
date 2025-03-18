public class Kaart{
    
    private String tähis;
    private String mast;

    public Kaart(String tähis, String mast){
        this.tähis = tähis;
        this.mast = mast;
    }

    public String toString(){
        return tähis + mast;
    }

    public int arvutaVäärtus(){
        return -1;
    }
}