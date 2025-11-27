package hu.bme.sudoku.logic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Sudoku rejtvények generálása.
 * A backtracking algoritmus segítségével teljes megoldásokat és játékkereteket hoz létre.
 */
public class SudokuGenerator {

    private final Random rand = new Random();
    private static final int MERET = 9;
    private SudokuEllenorzo ellenorzo = new SudokuEllenorzo();

    /**
     * Egy teljes, érvényes Sudoku megoldást generál.
     * 
     * @return egy 9x9-es kitöltött Sudoku tábla
     */
    public int[][] generalTeljesMegoldas() {
        int[][] tabla = new int[MERET][MERET];
        general(0, 0, tabla);
        return tabla;
    }

    /**
     * Rekurzív backtracking solver a teljes megoldás létrehozásához.
     * Végigmegy a táblán, és minden cellához véletlenszerűen próbálja meg az 1-9 számokat.
     * 
     * @param sor az aktuális sor indexe
     * @param oszlop az aktuális oszlop indexe
     * @param tabla a Sudoku tábla
     * @return true ha sikeres megoldás, false ha nincs lehetséges megoldás
     */
    private boolean general(int sor, int oszlop, int[][] tabla) {
        if (sor == MERET) return true;

        int kovSor = (oszlop == MERET - 1) ? sor + 1 : sor;
        int kovOszlop = (oszlop + 1) % MERET;

        List<Integer> szamok = new ArrayList<>();
        for (int i = 1; i <= 9; i++) szamok.add(i);
        Collections.shuffle(szamok, rand);

        for (int szam : szamok) {
            if (ellenorzo.beteheto(tabla, sor, oszlop, szam)) {
                tabla[sor][oszlop] = szam;
                if (general(kovSor, kovOszlop, tabla)) return true;
                tabla[sor][oszlop] = 0;
            }
        }

        return false;
    }

    /**
     * Sudoku nehézségi szintek.
     * KEZDO: könnyű (50 kitöltött cella)
     * HALADO: közepes (35 kitöltött cella)
     * PROFI: nehéz (25 kitöltött cella)
     */
    public enum Nehezseg {
        KEZDO, HALADO, PROFI
    }

    /**
     * Egy új játékkeretkezetet generál az adott nehézségi szinten.
     * Egy teljes megoldást hoz létre, majd eltávolít egy bizonyos számú cellát.
     * 
     * @param nehezseg a játék nehézségi szintje (KEZDO, HALADO, PROFI)
     * @return egy 9x9-es játékkeret cellákkal kiürítve az adott nehézség szerint
     */
    public int[][] generalUjJatek(Nehezseg nehezseg) {
        int[][] tabla = generalTeljesMegoldas();

        int uresekSzama = switch (nehezseg) {
            case KEZDO -> 81 - 50;
            case HALADO -> 81 - 35;
            case PROFI -> 81 - 25;
        };

        List<int[]> cellak = new ArrayList<>();
        for (int i = 0; i < MERET; i++)
            for (int j = 0; j < MERET; j++)
                cellak.add(new int[]{i, j});

        Collections.shuffle(cellak, rand);

        for (int i = 0; i < uresekSzama && i < cellak.size(); i++) {
            int sor = cellak.get(i)[0];
            int oszlop = cellak.get(i)[1];
            tabla[sor][oszlop] = 0;
        }

        return tabla;
    }
}

