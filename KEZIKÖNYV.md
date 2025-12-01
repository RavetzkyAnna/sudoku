# Sudoku — Felhasználói kézikönyv

Ez a kézikönyv röviden ismerteti a projekt futtatását, használatát és a leggyakoribb hibák megoldását.

**Rövid ismertető**
- A projekt egy asztali Sudoku játék Java-ban, GUI felülettel. A fő indítóosztály: `hu.bme.sudoku.gui.GameWindow`.

**Követelmények**
- Java JDK 11 vagy újabb.
- Apache Maven (a projekt Maven alapú).
- macOS-en a terminál `zsh` is megfelelő.

**Telepítés és futtatás fejlesztői környezetben**
- A forrásokból építéshez a projekt gyökérmappájában futtasd:

```bash
mvn package
```

- A program közvetlen futtatása Maven-nel:

```bash
mvn -U exec:java -Dexec.mainClass="hu.bme.sudoku.gui.GameWindow"
```

- Ha elkészült a `jar`, futtathatod közvetlenül (figyelj az osztályútvonalra és external dependency-kre):

```bash
java -cp target/sudoku-1.0-SNAPSHOT.jar hu.bme.sudoku.gui.GameWindow
```


**Részletes használati útmutató (GUI — lépésről lépésre)**

1) Indítás
- A program ablakának címe: `Sudoku`. Az ablak mérete alapból 600x600 pixel, a táblázat középen jelenik meg.
- A tábla egy 9x9-es `JTable`, sorok magassága ~50 px, betűtípus `SansSerif` vastagon, 24-es méret.

2) Főbb elemek
- Menüsor: a bal felső sarokban egy `Menü` menüpont található. A menüelemek (felülről lefelé):
  - `Új játék` — új Sudoku indítása (megnyit egy nehézségválasztó párbeszédet).
  - `Mentés` — a jelenlegi tábla mentése fájlba (fájlválasztó nyílik).
  - `Betöltés` — mentett játék betöltése fájlból (fájlválasztó nyílik).
  - `Ellenőrzés` — a táblázat aktuális állapotának ellenőrzése hibák után.
  - (elválasztó) —
  - `Kilépés` — kilép az alkalmazásból.

- Játéktábla: a cellák 3x3 blokkok szerint vizuálisan elkülönítve vannak vastagabb vonalakkal.

3) Cellák és beviteli módok
- Kattints a cellára: a cella kiválasztott állapotba kerül (kék háttér jelzés).
- Szám beírása: egyszerűen gépeld be az `1`–`9` közötti számot a kiválasztott cellába. A szerkesztő csak egyjegyű 1–9 karaktereket enged be.
- Törlés: használd a `Backspace` vagy `Delete` billentyűt a cella tartalmának eltávolításához (üres cella lesz).
- Rögzített (fix) cellák: az `Új játék` által generált kezdő számok rögzítettek — ezek nem szerkeszthetők és világosszürke háttérrel jelennek meg.

4) Színek és vizuális visszajelzések
- Rögzített cella: szürke háttér (RGB ~230,230,230).
- Kiválasztott cella: kék háttér (RGB ~180,200,255).
- Ellenőrzés után:
  - Hibás mezők: piros háttér (RGB ~255,200,200).
  - Helyes, nem rögzített mezők: zöld háttér (RGB ~200,255,200).
- A 3x3-as alblokkok között vastagabb fekete vonalak láthatók; a cellák között vékonyabb határok vannak.

5) Új játék indítása
- Menüből: kattints a `Menü` → `Új játék`-ra.
- Megjelenik egy párbeszédablak, ahol választhatsz nehézséget: `Kezdő`, `Haladó`, `Profi`.
- Válassz nehézséget, majd az alkalmazás automatikusan generál egy új táblát, ahol a kezdő számok rögzítve (szürke) jelennek meg.

6) Mentés
- Menüből: `Menü` → `Mentés`.
- Megnyílik a fájlválasztó. Válaszd ki a megtartani kívánt helyet és fájlnevet, majd kattints a mentés gombra.
- Sikeres mentés esetén egy felugró ablak jelzi: `Sikeres mentés!`. Hiba esetén `Hiba történt a mentéskor.` üzenet jelenik meg.

7) Betöltés
- Menüből: `Menü` → `Betöltés`.
- Válassz egy mentett fájlt a fájlválasztóban, majd nyisd meg.
- A program visszaállítja a tábla állapotát, a rögzített cellákat, és frissíti a képernyőt. Siker esetén `Betöltés kész!` üzenet jelenik meg.

8) Ellenőrzés
- Menüből: `Menü` → `Ellenőrzés`.
- A program lefuttatja az ellenőrzőt és kiemeli a hibás mezőket pirossal. Ha nincsenek hibák és a tábla teljesen kitöltött, felugrik egy információs ablak: `Gratulálok! Kész a sudoku!`.

9) Kilépés
- Menüből: `Menü` → `Kilépés` kilépteti az alkalmazást.

10) Hibakeresés felhasználói szinten
- Ha mentésnél vagy betöltésnél hibát kapsz, ellenőrizd, hogy van-e írási/olvasási joga a kiválasztott mappához.
- Az `Ellenőrzés` használata segít megtalálni rossz számokat: piros cellákat javítsd át a helyes értékre.

**Fejlesztők: rövid belső leírás (ha érdekel)**
- A fő ablak osztálya: `hu.bme.sudoku.gui.GameWindow`.
- A cellák rendereléséért a `hu.bme.sudoku.gui.CellRenderer` felelős (színek, borderek).
- A cellaszerkesztőt a `hu.bme.sudoku.gui.SudokuCellEditor` kezeli — csak 1–9 beírása engedélyezett.

---
Készen áll ez a részletes, kattintás-alapú kézikönyv? Szeretnéd, hogy hozzáadjak képernyőképeket a menükről és a tipikus státuszokról (pl. piros/zöld/pontozott cellák), vagy elég ez a leírás? 

**Játékmenet és funkciók**
- Nehézségi beállítás: ha elérhető, válassz `Könnyű`, `Közepes`, `Nehéz` opciók közül.
- Hint / Tipp: egy cellához ad tippet (ha implementált).
- Megoldás visszajátszás: a mentett játék betöltésével folytatható.

**Mentés és betöltés**
- Használd a menüt vagy a megfelelő gombokat a mentéshez (`Save`) és betöltéshez (`Load`).
- A mentett fájl helye és formátuma a program implementációjától függ; a `src/main/java/hu/bme/sudoku/io` mappa tartalmazhatja a fájlkezelő logikát.

**Fejlesztők: tesztek, futtatás**
- Tesztek futtatása:

```bash
mvn test
```

- Források a `src/main/java/hu/bme/sudoku` alatt; tesztek a `src/test/java/hu/bme/sudoku` mappában találhatók.

**Hibakeresés és gyakori problémák**
- Maven figyelmeztés a `maven-site-plugin:3.14.0` hiányáról:
  - Ez tipikusan nem fatal error, hanem azt jelzi, hogy a Maven a lokális cache-ben korábban sikertelen letöltést talált.
  - Gyors megoldások:

```bash
# Kényszerített frissítés és futtatás
mvn -U exec:java -Dexec.mainClass="hu.bme.sudoku.gui.GameWindow"

# Vagy töröld a lokális cache hibás mappáját, majd próbáld újra
rm -rf ~/.m2/repository/org/apache/maven/plugins/maven-site-plugin/3.14.0
mvn -U exec:java -Dexec.mainClass="hu.bme.sudoku.gui.GameWindow"

# Hibakereséshez bővebb naplózás
mvn -X -U exec:java -Dexec.mainClass="hu.bme.sudoku.gui.GameWindow"
```

- Ha a GUI nem jelenik meg, ellenőrizd:
  - Fut-e a Java folyamat (pl. `ps aux | grep java`).
  - Nincsenek-e runtime kivételek a Maven naplóban (`mvn -X`).
  - macOS-en általában nincs szükség további X11 beállításra; ha távoli gépen dolgozol, ellenőrizd a grafikus kijelzőt.

**Hasznos parancsok összefoglalva**
- Build: `mvn package`
- Futtatás: `mvn -U exec:java -Dexec.mainClass="hu.bme.sudoku.gui.GameWindow"`
- Tesztek: `mvn test`

**Kapcsolat / hibajelentés**
- Ha hibát találsz, kérlek küldj egy rövid leírást, a konzol kimenetét és a lépéseket, amelyekkel reprodukálható a hiba.

---
Ez a kézikönyv kezdő lépésként szolgál. Szeretnéd, hogy bővítsem részletes képernyőképekkel, menüelemek listájával vagy a pontos fájlformátumokkal?
