package org.blackjack;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Realiseerib blackjaki mängu läbi JavaFX graafilise liidese.
 * Klass haldab menüü- ja mängustseene ning töötleb nii hiire kui ka klaviatuuriga tekitatud sündmusi
 *
 */
public class Main extends Application {
    private Laud laud = new Laud();
    private Maja maja = new Maja();
    private Mängija mängija = new Mängija();

    private Stage peamineLava;
    private Scene menüüStseen;
    private Scene mänguStseen;

    private TextArea diileriAla;
    private TextArea mängijaAla;
    private Button juurdeNupp;
    private Button jätaNupp;


    /**
     * Käivitab JavaFX rakenduse ja seadistab esialgsed stseenid.
     *
     * @param lava Peamine lava, millel stseenid kuvatakse.
     */
    @Override
    public void start(Stage lava) {
        peamineLava = lava;
        ehitaMenüüStseen();
        ehitaMänguStseen();

        peamineLava.setTitle("Blackjack");
        peamineLava.setScene(menüüStseen);
        peamineLava.show();
    }

    /**
     * Koostab ja seadistab menüüstseeni.
     */
    private void ehitaMenüüStseen() {
        // nupud menüüs
        Button mängiNupp = new Button("Mängi (1)");
        Button abiNupp = new Button("Kuidas mäng käib? (2)");
        Button eelmineNupp = new Button("Eelmise mängu tulemus (3)");
        Button lahkuNupp = new Button("Lahku (4)");

        mängiNupp.setMaxWidth(Double.MAX_VALUE);
        abiNupp.setMaxWidth(Double.MAX_VALUE);
        eelmineNupp.setMaxWidth(Double.MAX_VALUE);
        lahkuNupp.setMaxWidth(Double.MAX_VALUE);

        // nupuvajutused
        mängiNupp.setOnAction(e -> {
            peamineLava.setScene(mänguStseen);
            uusMäng();
            mänguStseen.getRoot().requestFocus();
        });
        abiNupp.setOnAction(e -> näitaAbi());
        eelmineNupp.setOnAction(e -> näitaEelmineTulemus());
        lahkuNupp.setOnAction(e -> Platform.exit());

        // paigutus
        VBox menüüKast = new VBox(10, mängiNupp, abiNupp, eelmineNupp, lahkuNupp);
        menüüKast.setPadding(new Insets(20));
        menüüKast.setAlignment(Pos.CENTER);

        //automaatne suuruse kohandamine
        BorderPane menüüRoot = new BorderPane();
        menüüRoot.setCenter(menüüKast);
        menüüKast.setMaxWidth(Double.MAX_VALUE);
        menüüRoot.widthProperty().addListener((obs, vanaVäärtus, uusVäärtus) -> {
            menüüKast.setPrefWidth(uusVäärtus.doubleValue() * 0.6);
        });

        menüüKast.setMaxHeight(Double.MAX_VALUE);
        menüüRoot.heightProperty().addListener((obs, vanaVäärtus, uusVäärtus) -> {
            menüüKast.setPrefHeight(uusVäärtus.doubleValue() * 0.6);
        });

        menüüStseen = new Scene(menüüRoot, 300, 200);

        // klaviatuuri nupuvajutused
        menüüStseen.setOnKeyPressed(e -> tryCatch(() ->{

                if (e.getCode() == KeyCode.DIGIT1) {
                    mängiNupp.fire();
                } else if (e.getCode() == KeyCode.DIGIT2) {
                    abiNupp.fire();
                } else if (e.getCode() == KeyCode.DIGIT3) {
                    eelmineNupp.fire();
                } else if (e.getCode() == KeyCode.DIGIT4) {
                    lahkuNupp.fire();
                } else {
                    throw new ValeSisendErind("Vale sisend: " + e.getText());
                }
        }));
    }

    /**
     * Koostab ja seadistab mängustseeni, kus toimub kaartide jagamine ja mängu käik.
     */
    private void ehitaMänguStseen() {
        diileriAla = new TextArea();
        mängijaAla = new TextArea();
        diileriAla.setEditable(false);
        mängijaAla.setEditable(false);


        diileriAla.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        mängijaAla.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        juurdeNupp = new Button("Juurde (1)");
        jätaNupp = new Button("Jäta (2)");
        juurdeNupp.setMaxWidth(Double.MAX_VALUE);
        jätaNupp.setMaxWidth(Double.MAX_VALUE);

        // nupuvajutused
        juurdeNupp.setOnAction(e -> võtaKaart());
        jätaNupp.setOnAction(e -> jätaKäik());



        VBox diilerBox = new VBox(new Label("Dealer:"), diileriAla);
        VBox.setVgrow(diileriAla, Priority.ALWAYS);
        diilerBox.setMaxWidth(Double.MAX_VALUE);

        VBox mängijaBox = new VBox(new Label("Sina:"), mängijaAla);
        VBox.setVgrow(mängijaAla, Priority.ALWAYS);
        mängijaBox.setMaxWidth(Double.MAX_VALUE);

        HBox kaardidBox = new HBox(10, diilerBox, mängijaBox);
        kaardidBox.setPadding(new Insets(10));

        HBox.setHgrow(diilerBox, Priority.ALWAYS);
        HBox.setHgrow(mängijaBox, Priority.ALWAYS);


        diilerBox.prefWidthProperty().bind(kaardidBox.widthProperty().multiply(0.5).subtract(5));
        mängijaBox.prefWidthProperty().bind(kaardidBox.widthProperty().multiply(0.5).subtract(5));


        HBox tegevusnupud = new HBox(10, juurdeNupp, jätaNupp);
        tegevusnupud.setPadding(new Insets(10));
        tegevusnupud.setAlignment(Pos.CENTER);


        BorderPane mänguJuht = new BorderPane();
        mänguJuht.setCenter(kaardidBox);
        mänguJuht.setBottom(tegevusnupud);

        mänguStseen = new Scene(mänguJuht, 400, 250);
        mänguStseen.setOnKeyPressed(e -> tryCatch(() -> {
            if (e.getCode() == KeyCode.DIGIT1) {
                võtaKaart();
            } else if (e.getCode() == KeyCode.DIGIT2) {
                jätaKäik();
            } else {
                throw new ValeSisendErind("Vale nupp: " + e.getText());
            }
        }));
    }

    /**
     * Näitab püütud ValeSisendErindeid kasutajale tekstina.
     * @param tegevus mida proovitakse käivitada.
     */
    private void tryCatch(Käivitaja tegevus) {
        try {
            tegevus.käivita();
        } catch (ValeSisendErind ex) {
            Alert viga = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            viga.setHeaderText("Vigane sisend");
            Button ok = (Button) viga.getDialogPane().lookupButton(ButtonType.OK);
            ok.setText("OK (Enter)");
            ok.setDefaultButton(true);
            viga.showAndWait();
        }
    }

    /**
     * Liides meetodile, mis võib visata ValeSisendErindi
     */
    @FunctionalInterface
    interface Käivitaja {
        void käivita() throws ValeSisendErind;
    }

    /**
     * Algatab uue mängu. Segab paki ja jagab algkaardid.
     */
    private void uusMäng() {
        mängija.setKäsi(new ArrayList<>());
        maja.setKäsi(new ArrayList<>());
        laud.segaKaardiPakk();
        laud.jagaKaardid(maja, mängija);
        värskendaVaateid(true);
    }

    /**
     * Uuendab diileri ja mängija tekstialasid vastavalt käe seisule.
     * @param peidaTeineDiileriKaart Kas peita teist kaarti või mitte.
     */
    private void värskendaVaateid(boolean peidaTeineDiileriKaart) {
        diileriAla.setText(
                laud.getStringKäsi(maja.getKäsi(), peidaTeineDiileriKaart)
        );
        mängijaAla.setText(
                laud.getStringKäsi(mängija.getKäsi(), false)
        );
    }

    /**
     * Käivitab mängija kaardi tõmbamise ja kontrollib tulemusi.
     * laseb tõmmata vaid kuni 21 saamiseni.
     */
    private void võtaKaart(){
        if (laud.koguVäärtus(mängija.getKäsi()) < 21) {
            mängija.lisaKaart(laud.tõmbaKaart());
            värskendaVaateid(true);
            if (laud.koguVäärtus(mängija.getKäsi()) >= 21 || laud.koguVäärtus(mängija.getKäsi()) == 21 ) {
                jätaKäik();
            }
        }
    }

    /**
     * Käivitab diileri korra ja algatab tulemuse kuvamist.
     */
    private void jätaKäik(){
        laud.dealeriKord(maja, mängija);
        värskendaVaateid(false);
        näitaTulemus();
    }

    /**
     * Kuvab mängu tulemuse ning pakub valikut uuesti mängida või menüüsse naasta.
     */
    private void näitaTulemus() {
        int diileriVäärtus = laud.koguVäärtus(maja.getKäsi());
        int mängijaVäärtus = laud.koguVäärtus(mängija.getKäsi());
        String tulemus;
        if (mängijaVäärtus > 21) {
            tulemus = "Bust! Kahjuks mängisite ennast lõhki.";
        } else if (diileriVäärtus != 21 && mängijaVäärtus == 21) {
            tulemus = "Blackjack! Palju õnne, võitsite mängu!";
        } else if (mängijaVäärtus > diileriVäärtus) {
            tulemus = "Palju õnne, võitsite mängu!";
        } else if (diileriVäärtus > 21) {
            tulemus = "Dealer bust, võitsite mängu!";
        } else if (mängijaVäärtus < diileriVäärtus) {
            tulemus = "Dealer võidab.";
        } else {
            tulemus = "Viik!";
        }

        Alert tulemusDialoog = new Alert(Alert.AlertType.INFORMATION, tulemus, ButtonType.OK);
        tulemusDialoog.setHeaderText("Mängu tulemus");
        Button ok = (Button) tulemusDialoog.getDialogPane().lookupButton(ButtonType.OK);
        ok.setText("OK (Enter)");
        ok.setDefaultButton(true);

        tulemusDialoog.showAndWait();


        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("kaardid.txt", false), StandardCharsets.UTF_8))) {
            bw.write("Dealeri kaardid:\n" + laud.getStringKäsi(maja.getKäsi(), false) + "\n");
            bw.write("Sinu kaardid:\n" + laud.getStringKäsi(mängija.getKäsi(), false));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Alert uuesti = new Alert(Alert.AlertType.CONFIRMATION,
                "Kas soovid uuesti mängida?", ButtonType.YES, ButtonType.NO);
        uuesti.setHeaderText(null);

        DialogPane pane = uuesti.getDialogPane();

        Button yesNupp = (Button) pane.lookupButton(ButtonType.YES);
        Button noNupp  = (Button) pane.lookupButton(ButtonType.NO);

        yesNupp.setText("Jah (1)");
        noNupp.setText("Ei (2)");

        pane.addEventFilter(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.DIGIT1) {
                yesNupp.fire();
                ev.consume();
            } else if (ev.getCode() == KeyCode.DIGIT2) {
                noNupp.fire();
                ev.consume();
            }
        });

        uuesti.showAndWait().ifPresent(vastus -> {
            if (vastus == ButtonType.YES) {
                uusMäng();
            } else {
                peamineLava.setScene(menüüStseen);
            }
        });
    }

    /**
     * Kuvab mängu reeglid.
     */
    private void näitaAbi() {
        String info = String.join("\n",
                "Blackjack on kaardimäng, kus on vähemalt kaks mängijat (sel juhul Sina ja Dealer).",
                "Mängu alguses mõlemad mängijad saavad kaks kaarti, kuid üks Dealeri kaartidest on mängu alguses peidetud.",
                "Igal kaardil on kindel väärtus. Näiteks iga numbri kaardi väärtuseks on selle kaardi number.",
                "Kui tegu on kaardiga millel on täht ehk siis 'J', 'Q' ja 'K' siis selle väärtuseks on 10.",
                "'A' kaardiga on erand. Selle algne väärtus on 11, kuid kui mängu käigus ületad summat 21 siis kaardi väärtus läheb 1 peale.",
                "Mängu eesmärk on saada suurem kaartide väärtus, kui Dealeril. Maksimum lubatud väärtus on 21 ehk Blackjack. Kuid, kui seda ületad siis oled automaatselt kaotanud.",
                "Samuti, kui sul on väiksem väärtus, kui Dealeril siis oled samuti kaotanud.",
                "Mängijal on mängu käigus kaks valikut: Kas ühe kaardi juurde võtta või jätta ning lasta Dealeril lõpuni mängida.",
                "Mängija saab senikaua kaarte juurde võtta, kuniks ta ei ole ületanud 21 summa.",
                "Märkus! Dealer võtab kaarte juurde senikaua, kuniks ta saab kaartide summaks kokku 17.",
                "Edu mängul!"
        );
        Alert abiDialoog = new Alert(Alert.AlertType.INFORMATION, info, ButtonType.OK);
        abiDialoog.setHeaderText("Kuidas mäng käib?");
        abiDialoog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        Button ok = (Button) abiDialoog.getDialogPane().lookupButton(ButtonType.OK);
        ok.setText("OK (Enter)");
        ok.setDefaultButton(true);
        abiDialoog.showAndWait();
    }

    /**
     * Kuvab eelmise mängu tulemuse, lugedes salvestatud faili.
     */
    private void näitaEelmineTulemus() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("kaardid.txt"), StandardCharsets.UTF_8))) {
            String rida;
            while ((rida = br.readLine()) != null) {
                sb.append(rida).append("\n");
            }
        } catch (IOException e) {
            sb.append("Hetkel eelmise mängu andmed puuduvad.");
        }
        Alert eelmineDialoog = new Alert(Alert.AlertType.INFORMATION, sb.toString(), ButtonType.OK);
        eelmineDialoog.setHeaderText("Eelmise mängu tulemus");
        eelmineDialoog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        Button ok = (Button) eelmineDialoog.getDialogPane().lookupButton(ButtonType.OK);
        ok.setText("OK (Enter)");
        ok.setDefaultButton(true);
        eelmineDialoog.showAndWait();
    }

    /**
     * Peameetod, mis käivitab JavaFX rakenduse.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
