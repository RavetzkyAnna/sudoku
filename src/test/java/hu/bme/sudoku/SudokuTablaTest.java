package hu.bme.sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import hu.bme.sudoku.logic.SudokuTabla;

public class SudokuTablaTest {

    @Test
    void testUresTablaLetrejon() {
        SudokuTabla t = new SudokuTabla();
        int[][] m = t.getTabla();

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                assertEquals(0, m[i][j]);
    }

    @Test
    void testErtekBeallitasa() {
        SudokuTabla t = new SudokuTabla();
        t.set(3, 4, 7);

        assertEquals(7, t.getTabla()[3][4]);
    }

    @Test
    void testErtekTorlese() {
        SudokuTabla t = new SudokuTabla();
        t.set(1, 1, 5);
        t.set(1, 1, 0);

        assertEquals(0, t.getTabla()[1][1]);
    }

    @Test
    void testTablaMasolasa() {
        int[][] adat = new int[9][9];
        adat[2][2] = 9;

        SudokuTabla t = new SudokuTabla(adat);

        assertEquals(9, t.getTabla()[2][2]);
    }
}
