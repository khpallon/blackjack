**OOP Teine rühmatöö - Blackjack kaardimäng**

***AUTORID***

`Karl Hannes Pallon`\
`Mihkel Kotta`

***KIRJELDUS***

Programmil on 6 klassi: `Mängija`, `Dealer`, `Kaart`, `Laud`, `Main` ja `ValeSisendErind`.

`Mängija` klassis hoiustame Mängija ehk Sinu kaarte.

`Dealer` klassis hoiustame Dealeri kaarte.

`Kaart` klassis määrame kaardi tähise, masti ning samuti väärtuse.

`Laud` Seal programm tegeleb kaardi segamisega, kaartide juurde andmisega, ning Dealeriga.

Siin mõned olulisemad meetodid sellest klassist:

`koguVäärtus(List<Kaart> kaardid)` -> Arvutab kaartide kogusumma ning tagastab selle.
`dealeriKord(Maja maja, Mängija mängija)` -> Dealer automaatselt kogub kaarte kuniks jõuab väärtuseni 17 või Bustib
`getStringKäsi(List<Kaart> käsi, boolean peidaTeine)` -> Tagastab käe tekstina, peites soovi korral diileri teise kaardi.

`Main` klassis loome klassid, käivitame mängu, tegeleb mängu protsessiga ning samuti anname mängijale võimaluse saada infot, et kuidas mäng käib ja laseb vaadata eelmise mängu tulemust.

Siin mõned olulisemad meetodid sellest klassist:

`ehitaMenüüStseen()` -> Koostab mängu menüü kuvapildi kasutades JavaFXi\
`ehitaMänguStseen()`-> Koostab mängu kuvapildi kasutades JavaFXi\
`uusMäng()` -> Algatab uue mängu. Segab paki ja jagab algkaardid.\
`võtaKaart()` -> Käivitab mängija kaardi tõmbamise ja kontrollib tulemusi.\
`jätaKäik()`-> Käivitab diileri korra ja algatab tulemuse kuvamist.\
`näitaTulemus()` -> Kuvab mängu tulemuse ning pakub valikut uuesti mängida või menüüsse naasta.

***TEGEMISE PROTSESS***

Töö ja suhtlus toimus meil kõik veebi vahendusel. Otsustasime, et arendame edasi oma esimest projekti.
Seekord oli tööjaotust palju kergem teha kui eelmine kord. Otsustasime, et Karl tegeleb faili lugemise ning erinditega ning Mihkel teeb mängu ümber JavaFXis.
Kõik rühmaliikmed osalesid töö tegemises ning probleeme meil ei tekkinud. Orienteeruvalt läks meil umbes 20h, et töö valmis saada. Kasutasime GitHub'i, et koodi jagada ning uuendada.

***PANUS***

Kuna tegime esimeseks projektiks blackjacki algsema versiooni, siis ei olnud vaja muuta klasse Kaart.java, Maja.java, Mängija.java
Karl tegeles erindite ning failist lugemisega
Mihkel tegi mängu JavaFX graafilise kasutajaliidese abil ümber ning parandas mida oli vaja.

Karl - `Laud`, `Main`, `ValeSisendErind`
Mihkel - `Laud`, `Main`

***HINNANG***

Meie arvates tuli programm väga hästi välja. Ühtkit suurt viga ei ole me leidnud. 
Ainukeseks arenduskohaks on see, et programmi graafilise väljundi välimust oleks saanud veidi ilusamaks teha kuid oleme enda praeguse tulemusega ka väga rahul.
5 palli süsteemis paneksime meie 4.9/5

***TESTIMINE***

Mängu testisime peamiselt seda mängides.
Oleme mängu mitu korda läbi mänginud, et olla kindlad iga juhsuliku kaardi kombinatsiooni võimalustega ning vastavalt nende väljunditega.
Lasime ka mängu mängida mõnel sõbral, kes aitas probleemikohte tuvastada.
