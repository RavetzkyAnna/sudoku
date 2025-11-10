package hu.bme.sudoku;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FajlKezelo {

    public record BetoltesEredmeny(int[][] tabla, boolean[][] fix) {}

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    // Sudoku tábla mentése JSON fájlba
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

    // Sudoku tábla betöltése JSON fájlból
    public BetoltesEredmeny betoltes(String path) throws IOException {
    int[][] tabla = new int[9][9];
    boolean[][] fix = new boolean[9][9];

    try (Scanner sc = new Scanner(new File(path))) {

        // átugorjuk a "#tabla" sort
        sc.nextLine();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                tabla[r][c] = sc.nextInt();
            }
        }

        sc.nextLine(); // #fix sort átugorja
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

