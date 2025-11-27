package hu.bme.sudoku.logic;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * Sudoku tábla validálása és ellenőrzése.
 * Ellenőrzi a sorok, oszlopok és 3x3 blokkok helyességét.
 */
public class SudokuEllenorzo {

    /**
     * A tábla hibás pozícióit adja vissza.
     * Hibásnak számít: üres cella (0), duplikált szám ugyanabban a sorban/oszlopban/blokkban.
     * 
     * @param t a Sudoku tábla
     * @return a hibás cellák pozícióinak halmaza
     */
    public Set<Point> hibasPoziciok(int[][] t) {
        Set<Point> hibak = new HashSet<>();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (t[r][c] < 1 || t[r][c] > 9) {
                    hibak.add(new Point(r, c));
                }
            }
        }

        for (int r = 0; r < 9; r++) {
            int[] cnt = new int[10];
            for (int c = 0; c < 9; c++) {
                int v = t[r][c];
                if (v >= 1 && v <= 9 && ++cnt[v] > 1) {
                    for (int cc = 0; cc < 9; cc++)
                        if (t[r][cc] == v) hibak.add(new Point(r, cc));
                }
            }
        }

        for (int c = 0; c < 9; c++) {
            int[] cnt = new int[10];
            for (int r = 0; r < 9; r++) {
                int v = t[r][c];
                if (v >= 1 && v <= 9 && ++cnt[v] > 1) {
                    for (int rr = 0; rr < 9; rr++)
                        if (t[rr][c] == v) hibak.add(new Point(rr, c));
                }
            }
        }

        for (int br = 0; br < 3; br++) for (int bc = 0; bc < 3; bc++) {
            int[] cnt = new int[10];
            for (int r = br*3; r < br*3+3; r++)
                for (int c = bc*3; c < bc*3+3; c++) {
                    int v = t[r][c];
                    if (v >= 1 && v <= 9 && ++cnt[v] > 1) {
                        for (int rr = br*3; rr < br*3+3; rr++)
                            for (int cc = bc*3; cc < bc*3+3; cc++)
                                if (t[rr][cc] == v) hibak.add(new Point(rr, cc));
                    }
                }
        }
        return hibak;
    }

    /**
     * Ellenőrzi, hogy a tábla teljesen és helyesen kitöltött-e.
     * 
     * @param t a Sudoku tábla
     * @return true ha hiba nélküli és nincs üres cella, false egyébként
     */
    public boolean teljesenKesz(int[][] t) {
        return hibasPoziciok(t).isEmpty();
    }

    /**
     * Ellenőrzi, hogy egy szám behelyezhető-e az adott cellába.
     * Szigorú Sudoku szabályok szerint: nem lehet ismétlődés sorban, oszlopban, blokkban.
     * 
     * @param tabla a Sudoku tábla
     * @param sor a sor indexe (0-8)
     * @param oszlop az oszlop indexe (0-8)
     * @param ertek a behelyezendő érték (1-9)
     * @return true ha behelyezhető, false ha sértene egy szabályt
     */
    public boolean beteheto(int[][] tabla, int sor, int oszlop, int ertek) {
        for (int c = 0; c < 9; c++) {
            if (c != oszlop && tabla[sor][c] == ertek) return false;
        }
    
        for (int r = 0; r < 9; r++) {
            if (r != sor && tabla[r][oszlop] == ertek) return false;
        }
    
        int startRow = (sor / 3) * 3;
        int startCol = (oszlop / 3) * 3;
    
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if ((r != sor || c != oszlop) && tabla[r][c] == ertek) {
                    return false;
                }
            }
        }
    
        return true;
    }
}
