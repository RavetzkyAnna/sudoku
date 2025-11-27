package hu.bme.sudoku.logic;

public class SudokuTabla {

    public static final int MERET = 9;
    private int[][] tabla;

    public SudokuTabla() {
        tabla = new int[MERET][MERET];
    }

    public SudokuTabla(int[][] masolat) {
        tabla = new int[MERET][MERET];
        for (int i = 0; i < MERET; i++) {
            System.arraycopy(masolat[i], 0, tabla[i], 0, MERET);
        }
    }

    public int get(int sor, int oszlop) {
        if (!ervenyesIndex(sor, oszlop)) throw new IllegalArgumentException("Érvénytelen index");
        return tabla[sor][oszlop];
    }

    public void set(int sor, int oszlop, int ertek) {
        if (!ervenyesIndex(sor, oszlop)) throw new IllegalArgumentException("Érvénytelen index");
        if (ertek < 0 || ertek > 9) throw new IllegalArgumentException("Érték 0–9 lehet");
        tabla[sor][oszlop] = ertek;
    }

    public boolean ures(int sor, int oszlop) {
        return get(sor, oszlop) == 0;
    }

    public int[][] getTabla() {
        int[][] m = new int[MERET][MERET];
        for (int i = 0; i < MERET; i++)
            System.arraycopy(tabla[i], 0, m[i], 0, MERET);
        return m;
    }

    public void torles() {
        for (int i = 0; i < MERET; i++) {
            for (int j = 0; j < MERET; j++) {
                tabla[i][j] = 0;
            }
        }
    }

    public int uresekSzama() {
        int db = 0;
        for (int i = 0; i < MERET; i++)
            for (int j = 0; j < MERET; j++)
                if (tabla[i][j] == 0) db++;
        return db;
    }

    private boolean ervenyesIndex(int sor, int oszlop) {
        return sor >= 0 && sor < MERET && oszlop >= 0 && oszlop < MERET;
    }
}

