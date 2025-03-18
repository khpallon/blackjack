public class Laud {

    private String[] tähised = {"A", "J", "Q", "K", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private String[] mastid = {"♠", "♥", "♣", "♦"};
    
    public void alusta(Maja maja, Mängija mängija){

            Kaart kaart = new Kaart(tähised[(int)(Math.random()*13)], mastid[(int)(Math.random()*4)]);

        System.out.println(kaart);
    }
}
