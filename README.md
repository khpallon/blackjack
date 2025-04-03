**OOP Esimene rühmatöö - Blackjack kaardimäng**

***AUTORID***

`Karl Hannes Pallon`\
`Mihkel Kotta`

***KIRJELDUS***

Meie valisime enda projektiks luua lihtsa versiooni Blackjack kaardimängust. 
Hetkel mäng töötab ilma rahalise panuseta ning mängus on mängijal ainult võimalik, kas kaardi juurde võtta või jätta.
Samuti alguses on võimalik saada infot, et kuidas mäng käib. 

Programm töötab enamasti while loopis ning põhineb kastuaja sisendile. Igas olukorras on võimalik kasutajal sisendi käigus mõjutada edasist tegevust.
Iga sisendi käigus väljastab programm ekraanile, võimalikud sisendid, millega on võimalik mängu jätkata või lõpetada.

***KLASSID JA MEETODID***

Programmil on 5 klassi: `Mängija`, `Dealer`, `Kaart`, `Laud` ja `Main`.

`Mängija` klassis hoiustame Mängija ehk Sinu kaarte ning samuti väljastame need ekraanile.

`Dealer` klassis hoiustame Dealeri kaarte ning samuti väljastame need ekraanile.

`Kaart` klassis määrame kaardi tähise, masti ning samuti väärtuse.

`Laud` klassis toimub kogu mängu põhitöö. Seal programm tegeleb kaardi segamisega, kaartide juurde andmisega, Mängija sisendiga ning Dealeriga.

Siin mõned olulisemad meetodid sellest klassist:

`protsess(Maja, Mängija, boolean)` -> Siin kontrollime mängija sisendit ning tegutseme vastavalt\
`dealer(Maja, Mängija)` -> Dealer võtab seni kaua kaarte, kuniks võidab või kaotab mängu. Samuti on ka viik võiamlus.\
`koguVäärtus(List<Kaart>)` -> Arvutab kaartide kogusumma ning tagastab selle
`mängi(Maja, Mängija)` -> Teeb mängule ettevalmistusi ning alustab mängu loopiga.

`Main` klassis loome kõik klassid, käivitame mängu ning samuti anname mängijale võimaluse saada infot, et kuidas mäng käib.


***TEGEMISE PROTSESS***

Töö ja suhtlus toimus meil kõik veebi vahendusel. Ajurünnakud tehes otsustasime, et teeksime lihtsa versiooni Blackjack kaardimängust. 
Avastasime, et tööjaotust oli raske teha, kuna projekt iseenesest tundus väga lühike ja lihtne. Seega otsustasime, et Karl alustab ning Mihkel lõpetab töö.
Kõik rühmaliikmed osalesid töö tegemises ning probleeme meil ei tekkinud. Orienteeruvalt läks meil umbes 15h, et töö valmis saada. Kasutasime GitHub'i, et koodi jagada ning uuendada.


***PANUS***

Kuna tööd oli suhteliselt raske jaotada siis otsustasime, et Karl alustab kirjutamisega ning Mihkel lõpetab. Seega Karl jõudis ise teha kõik klassid ning neid arendada ning Mihkel lõpetas ja ka parandas neid.

Karl - `Kaart`, `Maja`, `Mängija`, `Laud`, `Main`
Mihkel - `Laud`, `Maja`

Karl tegeles enamasti taaskasutatavate meetotidega ning Mängija väljundiga .\
Mihkel tegeles Dealeri väljundiga ja arvutustega.

***HINNANG***

Meie arvates tuli programm väga hästi välja. Mängu on võimalik lõpmatuseni mängida ning mingeid olulisemaid vigu pole me avastanud.
Ainuke asi, mida oleks saanud paremini teha, oleks olnud teksti väljund, kuid kuna hetkel ei soovitud graafilist väljundit siis otsustasime sellega edasi minna.
5 palli süsteemis paneksime meie 4.9/5

***TESTIMINE***

Hetkel oleme programmi testinud kasutades `System.out.println` käske, et veenduda programm väljastab õigeid väljundeid ning jõuab sinna kuhu oleks vaja jõuda.
Omakorda oleme ka mängu mitu korda läbi mänginud, et olla kindlad iga juhsuliku kaardi kombinatsiooni võimalustega ning vastavalt nende väljunditega.