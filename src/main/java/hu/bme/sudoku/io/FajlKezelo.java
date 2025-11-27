package hu.bme.sudoku.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A Sudoku játék állapotának mentése és betöltése.
 * Szöveges fájlformátumot használ a tábla és a rögzített cellák tárolásához.
 */
public class FajlKezelo {

    /**
     * A betöltés eredményét tartalmazó rekord.
     * Tartalmazza a táblát és a rögzített cellák információját.
     */
    public record BetoltesEredmeny(int[][] tabla, boolean[][] fix) {}

    /**
     * A Sudoku játék állapotát menti egy fájlba.
     * A fájl formátuma: "#tabla" után a tábla adatai, "#fix" után a rögzített cellák jelölése.
     * 
     * @param path a mentés helye (fájl elérési útja)
     * @param tabla a 9x9-es Sudoku tábla
     * @param fix a rögzített cellák jelöléseinek mátrixa
     * @throws IOException ha a fájl írása meghiúsul
     */
    public void mentes(String path, int[][] tabla, boolean[][] fix) throws IOException {
        try (PrintWriter pw = new PrintWriter(path)) {
            pw.println("#tabla");
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    pw.print(tabla[r][c] + " ");
                }
                pw.println();
            }
            pw.println("#fix");
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    pw.print((fix[r][c] ? 1 : 0) + " ");
                }
                pw.println();
            }
        }
    }

    /**
     * Egy korábban mentett Sudoku játékot tölt be.
     * 
     * @param path a fájl elérési útja
     * @return a betöltött tábla és fix cellák információja
     * @throws IOException ha a fájl olvasása meghiúsul (pl. nem létezik)
     */
    public BetoltesEredmeny betoltes(String path) throws IOException {
    int[][] tabla = new int[9][9];
    boolean[][] fix = new boolean[9][9];

    try (Scanner sc = new Scanner(new File(path))) {

        sc.nextLine();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                tabla[r][c] = sc.nextInt();
            }
        }

        sc.nextLine();
        sc.nextLine();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                fix[r][c] = sc.nextInt() == 1;
            }
        }
    }

    return new BetoltesEredmeny(tabla, fix);
}
}

