/**
 * Mängu kaardi klass, kus väärtustame ja tähistame kaardid
 */

public class Kaart{
    
    private String tähis;   // "K", "A", "Q", 10, 9, jne
    private String mast;    // "♠", "♥", "♣", "♦"
    private int väärtus;    // Kaardi numbriline väärtus

    public Kaart(String tähis, String mast){
        this.tähis = tähis;
        this.mast = mast;
        this.väärtus = arvutaVäärtus();
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


    /**
     * Arvutame kaardi numbrilise väärtuse
     * @return Tagastame saadud numbri
     */

    public int arvutaVäärtus(){

        // try ja catchiga kontrollime, kas tegu on tähe või numbriga

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