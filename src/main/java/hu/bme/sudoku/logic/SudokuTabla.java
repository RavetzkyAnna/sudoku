package hu.bme.sudoku.logic;

/**
 * Sudoku tábla reprezentációja és kezelése.
 * 9x9-es tábla, ahol 0 az üres cella.
 */
public class SudokuTabla {

    public static final int MERET = 9;
    private int[][] tabla;

    /**
     * Üres Sudoku tábla inicializálása.
     */
    public SudokuTabla() {
        tabla = new int[MERET][MERET];
    }

    /**
     * Sudoku tábla inicializálása egy meglévő tábla alapján.
     * 
     * @param masolat a másolandó tábla
     */
    public SudokuTabla(int[][] masolat) {
        tabla = new int[MERET][MERET];
        for (int i = 0; i < MERET; i++) {
            System.arraycopy(masolat[i], 0, tabla[i], 0, MERET);
        }
    }

    /**
     * Egy cella értékét lekéri.
     * 
     * @param sor a sor indexe (0-8)
     * @param oszlop az oszlop indexe (0-8)
     * @return a cella értéke (0 ha üres, 1-9 ha kitöltött)
     * @throws IllegalArgumentException ha az index érvénytelen
     */
    public int get(int sor, int oszlop) {
        if (!ervenyesIndex(sor, oszlop)) throw new IllegalArgumentException("Érvénytelen index");
        return tabla[sor][oszlop];
    }

    /**
     * Egy cella értékét beállítja.
     * 
     * @param sor a sor indexe (0-8)
     * @param oszlop az oszlop indexe (0-8)
     * @param ertek az új érték (0-9, ahol 0 az üres cella)
     * @throws IllegalArgumentException ha az index vagy érték érvénytelen
     */
    public void set(int sor, int oszlop, int ertek) {
        if (!ervenyesIndex(sor, oszlop)) throw new IllegalArgumentException("Érvénytelen index");
        if (ertek < 0 || ertek > 9) throw new IllegalArgumentException("Érték 0–9 lehet");
        tabla[sor][oszlop] = ertek;
    }

    /**
     * Ellenőrzi, hogy egy cella üres-e.
     * 
     * @param sor a sor indexe (0-8)
     * @param oszlop az oszlop indexe (0-8)
     * @return true ha a cella üres (0), false egyébként
     */
    public boolean ures(int sor, int oszlop) {
        return get(sor, oszlop) == 0;
    }

    /**
     * A teljes tábla másolatát adja vissza.
     * 
     * @return a tábla másolata
     */
    public int[][] getTabla() {
        int[][] m = new int[MERET][MERET];
        for (int i = 0; i < MERET; i++)
            System.arraycopy(tabla[i], 0, m[i], 0, MERET);
        return m;
    }

    /**
     * A tábla összes celláját nullázza (üressé teszi).
     */
    public void torles() {
        for (int i = 0; i < MERET; i++) {
            for (int j = 0; j < MERET; j++) {
                tabla[i][j] = 0;
            }
        }
    }

    /**
     * Az üres cellák számát adja vissza.
     * 
     * @return az üres (0 értékű) cellák száma
     */
    public int uresekSzama() {
        int db = 0;
        for (int i = 0; i < MERET; i++)
            for (int j = 0; j < MERET; j++)
                if (tabla[i][j] == 0) db++;
        return db;
    }

    /**
     * Ellenőrzi, hogy a megadott index érvényes-e.
     * 
     * @param sor a sor indexe
     * @param oszlop az oszlop indexe
     * @return true ha mindkét index 0-8 között van
     */
    private boolean ervenyesIndex(int sor, int oszlop) {
        return sor >= 0 && sor < MERET && oszlop >= 0 && oszlop < MERET;
    }
}

