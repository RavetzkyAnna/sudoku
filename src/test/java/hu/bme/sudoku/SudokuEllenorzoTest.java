package hu.bme.sudoku;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.Set;

public class SudokuEllenorzoTest {

    @Test
    void testUresTablaHibas() {
        SudokuEllenorzo e = new SudokuEllenorzo();
        int[][] t = new int[9][9];

        Set<Point> hibak = e.hibasPoziciok(t);

        assertFalse(hibak.isEmpty());
    }

    @Test
    void testJoSorOszlopBlokk() {
        SudokuEllenorzo e = new SudokuEllenorzo();

        int[][] t = {
            {5,3,4, 6,7,8, 9,1,2},
            {6,7,2, 1,9,5, 3,4,8},
            {1,9,8, 3,4,2, 5,6,7},

            {8,5,9, 7,6,1, 4,2,3},
            {4,2,6, 8,5,3, 7,9,1},
            {7,1,3, 9,2,4, 8,5,6},

            {9,6,1, 5,3,7, 2,8,4},
            {2,8,7, 4,1,9, 6,3,5},
            {3,4,5, 2,8,6, 1,7,9}
        };

        Set<Point> hibak = e.hibasPoziciok(t);

        assertTrue(hibak.isEmpty());
    }

    @Test
    void testSorDuplikacioHibas() {
        SudokuEllenorzo e = new SudokuEllenorzo();

        int[][] t = new int[9][9];
        t[0][0] = 5;
        t[0][3] = 5;  // duplikáció

        Set<Point> hibak = e.hibasPoziciok(t);

        assertTrue(hibak.contains(new Point(0, 0)));
        assertTrue(hibak.contains(new Point(0, 3)));
    }

    @Test
    void testOszlopDuplikacioHibas() {
        SudokuEllenorzo e = new SudokuEllenorzo();

        int[][] t = new int[9][9];
        t[1][2] = 8;
        t[8][2] = 8;

        Set<Point> hibak = e.hibasPoziciok(t);

        assertTrue(hibak.contains(new Point(1, 2)));
        assertTrue(hibak.contains(new Point(8, 2)));
    }
}

