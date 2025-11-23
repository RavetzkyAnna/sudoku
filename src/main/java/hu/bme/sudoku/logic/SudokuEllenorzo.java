package hu.bme.sudoku.logic;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class SudokuEllenorzo {
    public Set<Point> hibasPoziciok(int[][] t) {
        Set<Point> hibak = new HashSet<>();

        // üresek (0) is hibának számítanak
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (t[r][c] < 1 || t[r][c] > 9) {
                    hibak.add(new Point(r, c));
                }
            }
        }

        // sorhibák
        for (int r = 0; r < 9; r++) {
            int[] cnt = new int[10];
            for (int c = 0; c < 9; c++) {
                int v = t[r][c];
                if (v >= 1 && v <= 9 && ++cnt[v] > 1) {
                    // jelöljük duplumként azokat a cellákat, ahol ez a szám van
                    for (int cc = 0; cc < 9; cc++)
                        if (t[r][cc] == v) hibak.add(new Point(r, cc));
                }
            }
        }

        // oszlophibák
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

        // blokkhibák
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

    public boolean teljesenKesz(int[][] t) {
        return hibasPoziciok(t).isEmpty();
    }

    public boolean beteheto(int[][] tabla, int sor, int oszlop, int ertek) {

        // sor vizsgálat
        for (int c = 0; c < 9; c++) {
            if (c != oszlop && tabla[sor][c] == ertek) return false;
        }
    
        // oszlop vizsgálat
        for (int r = 0; r < 9; r++) {
            if (r != sor && tabla[r][oszlop] == ertek) return false;
        }
    
        // blokk vizsgálat
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
