package hu.bme.sudoku;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import hu.bme.sudoku.logic.SudokuEllenorzo;

public class SudokuEllenorzoExtrasTest {

    @Test
    void testBetehetoTrueOnEmpty() {
        SudokuEllenorzo e = new SudokuEllenorzo();
        int[][] t = new int[9][9];
        assertTrue(e.beteheto(t, 0, 0, 5));
    }

    @Test
    void testBetehetoFalseRowColumnBlock() {
        SudokuEllenorzo e = new SudokuEllenorzo();
        int[][] t = new int[9][9];

        // same row
        t[0][3] = 7;
        assertFalse(e.beteheto(t, 0, 0, 7));

        // same column
        t[4][2] = 6;
        assertFalse(e.beteheto(t, 0, 2, 6));

        // same block (top-left 3x3)
        t[1][1] = 9;
        assertFalse(e.beteheto(t, 2, 2, 9));
    }

    @Test
    void testBetehetoWithInvalidIndices() {
        SudokuEllenorzo e = new SudokuEllenorzo();
        int[][] t = new int[9][9];
        // ensure no ArrayIndexOutOfBounds is silently swallowed
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> e.beteheto(t, 9, 0, 1));
    }
}
