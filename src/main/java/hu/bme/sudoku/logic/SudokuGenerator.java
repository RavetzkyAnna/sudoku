package hu.bme.sudoku.logic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {

    private final Random rand = new Random();
    private static final int MERET = 9;
    private SudokuEllenorzo ellenorzo = new SudokuEllenorzo();

    public int[][] generalTeljesMegoldas() {
        int[][] tabla = new int[MERET][MERET];
        general(0, 0, tabla);
        return tabla;
    }

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

    public enum Nehezseg {
        KEZDO, HALADO, PROFI
    }

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

