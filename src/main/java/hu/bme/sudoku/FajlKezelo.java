package hu.bme.sudoku;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FajlKezelo {

    // belső osztály, amit JSON-ben tárolunk
    private static class JatekAllapot {
        int[][] tabla;
    }

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    // Sudoku tábla mentése JSON fájlba
    public void mentes(String fajlNev, int[][] tabla) throws IOException {
        JatekAllapot allapot = new JatekAllapot();
        allapot.tabla = tabla;

        try (FileWriter writer = new FileWriter(fajlNev)) {
            gson.toJson(allapot, writer);
        }
    }

    // Sudoku tábla betöltése JSON fájlból
    public int[][] betoltes(String fajlNev) throws IOException {
        try (FileReader reader = new FileReader(fajlNev)) {
            JatekAllapot allapot = gson.fromJson(reader, JatekAllapot.class);
            return allapot.tabla;
        }
    }
}

