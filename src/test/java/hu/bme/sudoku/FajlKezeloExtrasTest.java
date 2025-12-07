package hu.bme.sudoku;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import hu.bme.sudoku.io.FajlKezelo;

public class FajlKezeloExtrasTest {

    @Test
    void testBetoltesNonexistentFileThrows() {
        FajlKezelo fk = new FajlKezelo();
        assertThrows(IOException.class, () -> fk.betoltes("/this/file/does/not/exist.sudoku"));
    }

    @Test
    void testBetoltesInvalidFormatThrows() throws IOException {
        FajlKezelo fk = new FajlKezelo();
        
        File temp = File.createTempFile("sudoku_invalid", ".txt");
        temp.deleteOnExit();
        
        // Rossz formátumú fájl: nem tartalmazz számokat
        try (PrintWriter pw = new PrintWriter(temp)) {
            pw.println("#tabla");
            pw.println("nem szám");
        }
        
        assertThrows(IOException.class, () -> fk.betoltes(temp.getAbsolutePath()));
    }

    @Test
    void testBetoltesMissingMarkerThrows() throws IOException {
        FajlKezelo fk = new FajlKezelo();
        
        File temp = File.createTempFile("sudoku_no_marker", ".txt");
        temp.deleteOnExit();
        
        // Hiányzik a #fix marker
        try (PrintWriter pw = new PrintWriter(temp)) {
            pw.println("#tabla");
            for (int i = 0; i < 9; i++) {
                pw.println("0 0 0 0 0 0 0 0 0");
            }
            // #fix hiányzik
        }
        
        assertThrows(IOException.class, () -> fk.betoltes(temp.getAbsolutePath()));
    }
}
