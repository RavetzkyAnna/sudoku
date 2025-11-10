package hu.bme.sudoku;

public class SudokuTabla {

    public static final int MERET = 9;

    private int[][] tabla;   // 0 = üres cella

    public SudokuTabla() {
        tabla = new int[MERET][MERET];
    }

    public SudokuTabla(int[][] masolat) {
        tabla = new int[MERET][MERET];
        for (int i = 0; i < MERET; i++) {
            System.arraycopy(masolat[i], 0, tabla[i], 0, MERET);
        }
    }

    // cella lekérése
    public int get(int sor, int oszlop) {
        if (!ervenyesIndex(sor, oszlop)) throw new IllegalArgumentException("Érvénytelen index");
        return tabla[sor][oszlop];
    }

    // cella beállítása (0 = törlés)
    public void set(int sor, int oszlop, int ertek) {
        if (!ervenyesIndex(sor, oszlop)) throw new IllegalArgumentException("Érvénytelen index");
        if (ertek < 0 || ertek > 9) throw new IllegalArgumentException("Érték 0–9 lehet");
        tabla[sor][oszlop] = ertek;
    }

    // üres-e a cella?
    public boolean ures(int sor, int oszlop) {
        return get(sor, oszlop) == 0;
    }

    // teljes tábla visszaadása (védett másolat)
    public int[][] getTabla() {
        int[][] m = new int[MERET][MERET];
        for (int i = 0; i < MERET; i++)
            System.arraycopy(tabla[i], 0, m[i], 0, MERET);
        return m;
    }

    // tábla törlése (nullázás)
    public void torles() {
        for (int i = 0; i < MERET; i++) {
            for (int j = 0; j < MERET; j++) {
                tabla[i][j] = 0;
            }
        }
    }

    // üres cellák számolása (tesztben hasznos)
    public int uresekSzama() {
        int db = 0;
        for (int i = 0; i < MERET; i++)
            for (int j = 0; j < MERET; j++)
                if (tabla[i][j] == 0) db++;
        return db;
    }

    // segédfüggvény
    private boolean ervenyesIndex(int sor, int oszlop) {
        return sor >= 0 && sor < MERET && oszlop >= 0 && oszlop < MERET;
    }
}

