public class Kaart{
    
    private String tähis;
    private String mast;
    private int väärtus;

    public Kaart(String tähis, String mast){
        this.tähis = tähis;
        this.mast = mast;
        this.väärtus = arvutaVäärtus();
    }


    public String getMast() {
        return mast;
    }

    public String getTähis() {
        return tähis;
    }

    public int getVäärtus() {
        return väärtus;
    }

    public String toString(){
        return tähis + mast;
    }


    // Arvutab kaardi väärtuse

    public int arvutaVäärtus(){

        try{
            return Integer.parseInt(tähis);
        } catch (NumberFormatException e){
            if (tähis.equals("A")){
                return 11;
            } else {
                return 10;
            }
        }
    }
}