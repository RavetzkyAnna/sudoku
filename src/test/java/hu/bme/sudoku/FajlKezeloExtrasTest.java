package hu.bme.sudoku;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import hu.bme.sudoku.io.FajlKezelo;

public class FajlKezeloExtrasTest {

    @Test
    void testBetoltesNonexistentFileThrows() {
        FajlKezelo fk = new FajlKezelo();
        assertThrows(IOException.class, () -> fk.betoltes("/this/file/does/not/exist.sudoku"));
    }
}
