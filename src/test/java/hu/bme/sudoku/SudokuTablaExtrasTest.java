package hu.bme.sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import hu.bme.sudoku.logic.SudokuTabla;

public class SudokuTablaExtrasTest {

    @Test
    void testInvalidIndexGetSet() {
        SudokuTabla t = new SudokuTabla();
        assertThrows(IllegalArgumentException.class, () -> t.get(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> t.set(9, 0, 5));
    }

    @Test
    void testInvalidValueSet() {
        SudokuTabla t = new SudokuTabla();
        assertThrows(IllegalArgumentException.class, () -> t.set(0, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> t.set(0, 0, 10));
    }

    @Test
    void testTorlesAndUresekSzama() {
        SudokuTabla t = new SudokuTabla();
        t.set(0, 0, 5);
        t.set(1, 1, 3);

        int before = t.uresekSzama();
        // 81 cells total, 2 filled => 79 empty
        assertEquals(79, before);

        t.torles();
        assertEquals(81, t.uresekSzama());
    }
}
