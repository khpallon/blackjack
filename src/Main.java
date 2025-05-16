import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


/**
 * Main class, kus loome algsed klassid ning alustame mängu loopiga
 */

public class Main {
    public static void main(String[] args) throws IOException {
        Laud laud = new Laud();
        Maja maja = new Maja();
        Mängija mängija = new Mängija();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("*".repeat(30));
            System.out.println("\nTere tulemast Blackjack lauda! Mis on teie soovitud tegevus?");
            System.out.println("1 - Mängi ; 2 - Kuidas mäng käib? ; 3 - Lahku ; 4 - Eelmise mängu tulemus");
            System.out.println("*".repeat(30));
            int valik = scanner.nextInt();

            try {
                switch (valik){
                    case 1: laud.mängi(maja, mängija); break;  // Alustab mänguga
                    case 2: {   // Annab infot mängu kohta
                        System.out.println("*".repeat(30));
                        System.out.println("Blackjack on kaardimäng, kus on vähemalt kaks mängijat (sel juhul Sina ja Dealer).");
                        System.out.println("Mängu alguses mõlemad mängijad saavad kaks kaarti, kuid üks Dealeri kaartidest on mängu alguses peidetud.");
                        System.out.println("Igal kaardil on kindel väärtus. Näiteks iga numbri kaardi väärtuseks on selle kaardi number.");
                        System.out.println("Kui tegu on kaardiga millel on täht ehk siis 'J', 'Q' ja 'K' siis selle väärtuseks on 10.");
                        System.out.println("'A' kaardiga on erand. Selle algne väärtus on 11, kuid kui mängu käigus ületad summat 21 siis kaardi väärtus läheb 1 peale.");
                        System.out.println("Mängu eesmärk on saada suurem kaartide väärtus, kui Dealeril. Maksimum lubatud väärtus on 21 ehk Blackjack. Kuid, kui seda ületad siis oled automaatselt kaotanud.");
                        System.out.println("Samuti, kui sul on väiksem väärtus, kui Dealeril siis oled samuti kaotanud.");
                        System.out.println("Mängijal on mängu käigus kaks valikut: Kas ühe kaardi juurde võtta või jätta ning lasta Dealeril lõpuni mängida.");
                        System.out.println("Mängija saab senikaua kaarte juurde võtta, kuniks ta ei ole ületanud 21 summa.");
                        System.out.println("Märkus! Dealer võtab kaarte juurde senikaua, kuniks ta saab kaartide summaks kokku 17.");
                        System.out.println("Edu mängul!");
                        break;
                    }
                    case 3: {   // Lõpetab programmi tegevuse
                        System.exit(0);
                        break;
                    }
                    case 4: { // Eelmise mängu tulemus
                        try (BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream("kaardid.txt"), StandardCharsets.UTF_8))) {
                            System.out.println("*".repeat(30));
                            System.out.println("Eelmise mängu tulemus:");
                            System.out.println();
                            while (true){
                                String rida = d.readLine();
                                if (rida == null) {
                                    break;
                                } else {
                                    System.out.println(rida);
                                }
                            }
                        } catch (IOException e){
                            System.out.println("Hetkel eelmise mängu andmed puuduvad.");
                        }
                        break;
                    }
                    default: {
                        throw new ValeSisendErind("Vale sisend! Proovige uuesti.\n");
                    }
                }
            } catch (ValeSisendErind e) {
                System.out.println(e.getMessage());
            }
        }

    }
}