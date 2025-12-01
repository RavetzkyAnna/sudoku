# Sudoku Játék - Felhasználói Kézikönyv

## Telepítés

### Rendszerkövetelmények
- **Java 21** vagy újabb verzió
- **Maven 3.9.11** vagy újabb verzió
- Legalább 100 MB szabad hely
- Bármilyen operációs rendszer (Windows, macOS, Linux)

### Telepítési lépések

1. **Java és Maven telepítése** (ha még nincs meg):
   ```bash
   # macOS (Homebrew segítségével)
   brew install openjdk@21 maven
   
   # Linux (Ubuntu/Debian)
   sudo apt-get install openjdk-21-jdk maven
   ```

2. **A projekt klónozása**:
   ```bash
   git clone https://github.com/RavetzkyAnna/sudoku.git
   cd sudoku
   ```

3. **Projekt lefordítása**:
   ```bash
   mvn clean compile
   ```

4. **JAR fájl generálása**:
   ```bash
   mvn package
   ```

---

## A játék indítása

### Parancssorból

```bash
java -jar target/sudoku-1.0-SNAPSHOT.jar
```

### Közvetlenül Maven-ből

```bash
mvn exec:java -Dexec.mainClass="hu.bme.sudoku.gui.GameWindow"
```

Ezután megjelenik a játék ablaka egy üres 9×9-es Sudoku táblával.

## Alapvető szabályok

A Sudoku egy logikai rejtvény, ahol a célja:

1. **Kitölteni az összes üres cellát** számokkal 1-től 9-ig
2. **Szabályok**:
   - Minden **sorban** az 1-9 számok csak egyszer szerepelhetnek
   - Minden **oszlopban** az 1-9 számok csak egyszer szerepelhetnek
   - Minden **3×3-as blokkban** az 1-9 számok csak egyszer szerepelhetnek

## Játékmenet

### 1. Új játék indítása

1. Kattints a **Menü** → **Új játék** opcióra
2. Válassz nehézségi szintet:
   - **Kezdő**: Könnyebb rejtvény (50 szám adott)
   - **Haladó**: Közepes nehézségű (35 szám adott)
   - **Profi**: Nagyon nehéz (25 szám adott)
3. A tábla betöltődik az adott nehézség szerint

### 2. Szám beírása

1. **Kattints egy üres cellára** (fehér háttér)
2. **Írj be egy számot 1-9 között** vagy töröld az aktuális értéket
3. Az adott cella frissül az új értékkel
4. **Rögzített cellákba (szürke háttér) nem tudsz írni** - ezek a játék kezdeti elemei

---

## Menü funkciók

### Menü → Új játék
- Új játék indítása
- Korábbi játék eldobása
- Választhatsz nehézségi szintet

### Menü → Mentés
- Az aktuális játék állapotának mentése fájlba
- Fájlválasztó ablak megjelenik
- Mentés után az "OK" gomb kattintást követően a játék folytatható

### Menü → Betöltés
- Korábban elmentett játék betöltése
- Fájlválasztó ablak megjelenik
- Az összes cella és a rögzített információ visszakerül

**Figyelem**: A betöltés felülírja az aktuális játékot!

### Menü → Ellenőrzés
- A jelenlegi tábla validálása
- **Ha nincs hiba**: Zöld ablak jelenik meg "Gratulálok! Kész a sudoku!" üzenettel
- **Ha van hiba**: 
  - Piros ablak jelenik meg "Hibás vagy hiányos mezők találhatók!"
  - A hibás cellák pirosra változnak
  - Az üres cellák zöldre változnak (tájékoztatásként)
  - Folytathatod a játékot

### Menü → Kilépés
- A program bezárása
- Ha van nem mentett módosítás, az elveszik

---

## Nehézségi szintek

| Szint | Kitöltött cellák | Üresen hagyott | Ajánlott |
|-------|------------------|-----------------|----------|
| **Kezdő** | 50 | 31 | Kezdőknek, gyakorláshoz |
| **Haladó** | 35 | 46 | Középes szintű játékosoknak |
| **Profi** | 25 | 56 | Tapasztalt játékosoknak |

---

## Tippek és trükkök

### Stratégia

1. **Szám-nyomkövetés**: Nézd meg, hogy melyik sorokba, oszlopokba és blokkokba hiányzik az 1-es, 2-es stb.

2. **Csak lehetséges számok**: Egy cellába csak olyan szám írható, amely nem jelenik meg az ugyanabban a sorban, oszlopban vagy 3×3-as blokkban.

3. **Egyedüli jelölt**: Ha egy cellában csak egy lehetséges szám létezik, az az első kitöltendő cella.

4. **Láncos következtetés**: Több logikai lépés összekapcsolása.

### Tudnivalók

- A rögzített cellák (szürke) nem módosíthatók
- Az **Ellenőrzés** gomb csak azt jelzi, hogy van-e hiba, de nem oldja meg a feladványt
- Az **Új játék** bármikor elkezdhet új rejtvényt
- Az **Mentés/Betöltés** funkció lehetővé teszi több játékot párhuzamosan játszani
