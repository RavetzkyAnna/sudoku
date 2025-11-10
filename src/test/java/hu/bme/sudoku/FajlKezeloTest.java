package hu.bme.sudoku;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class FajlKezeloTest {

    @Test
    void testMentesEsBetoltes() throws IOException {
        FajlKezelo fk = new FajlKezelo();

        File temp = File.createTempFile("sudoku_test", ".txt");
        temp.deleteOnExit();

        int[][] tabla = new int[9][9];
        boolean[][] fix = new boolean[9][9];

        tabla[3][4] = 7;
        fix[3][4] = true;

        fk.mentes(temp.getAbsolutePath(), tabla, fix);

        FajlKezelo.BetoltesEredmeny eredm = fk.betoltes(temp.getAbsolutePath());
        int[][] beolvTab = eredm.tabla();
        boolean[][] beolvFix = eredm.fix();

        assertEquals(7, beolvTab[3][4]);
        assertTrue(beolvFix[3][4]);
    }

    @Test
    void testMentesBeolvasasTeljes() throws IOException {
        FajlKezelo fk = new FajlKezelo();

        File temp = File.createTempFile("sudoku_test2", ".txt");
        temp.deleteOnExit();

        int[][] tabla = new int[9][9];
        boolean[][] fix = new boolean[9][9];

        tabla[1][1] = 9;
        tabla[8][8] = 4;

        fix[1][1] = false;
        fix[8][8] = true;

        fk.mentes(temp.getAbsolutePath(), tabla, fix);

        FajlKezelo.BetoltesEredmeny eredm = fk.betoltes(temp.getAbsolutePath());

        int[][] beolvTab = eredm.tabla();
        boolean[][] beolvFix = eredm.fix();

        assertEquals(9, beolvTab[1][1]);
        assertEquals(4, beolvTab[8][8]);

        assertFalse(beolvFix[1][1]);
        assertTrue(beolvFix[8][8]);
    }
}
