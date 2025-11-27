package hu.bme.sudoku.gui;

import javax.swing.table.AbstractTableModel;

import hu.bme.sudoku.logic.SudokuTabla;

/**
 * A JTable modellje a Sudoku táblához.
 * Összekötés a Sudoku logika (SudokuTabla) és a GUI (JTable) között.
 */
public class SudokuTableModel extends AbstractTableModel {

    private SudokuTabla tabla;
    private boolean[][] fixCellak = new boolean[9][9];
    
    /**
     * A SudokuTableModel konstruktora.
     * 
     * @param tabla a Sudoku tábla objektum
     */
    public SudokuTableModel(SudokuTabla tabla) {
        this.tabla = tabla;
        inicializalFixCellak();
    }

    /**
     * A tábla objektum frissítése és az adatok újrainicializálása.
     * 
     * @param tabla az új Sudoku tábla objektum
     */
    public void setTabla(SudokuTabla tabla) {
        this.tabla = tabla;
        inicializalFixCellak();
        fireTableDataChanged();
    }

    /**
     * A rögzített cellák inicializálása a tábla alapján.
     * Az összes nem nulla érték rögzítettnek számít.
     */
    private void inicializalFixCellak() {
        int[][] t = tabla.getTabla();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fixCellak[i][j] = (t[i][j] != 0);
            }
        }
    }

    /**
     * A sorok számát adja vissza.
     * 
     * @return 9
     */
    @Override
    public int getRowCount() {
        return 9;
    }

    /**
     * Az oszlopok számát adja vissza.
     * 
     * @return 9
     */
    @Override
    public int getColumnCount() {
        return 9;
    }

    /**
     * Egy cella értékét lekéri szövegként.
     * Üres ha a tábla értéke 0.
     * 
     * @param rowIndex a sor indexe
     * @param columnIndex az oszlop indexe
     * @return a cella szöveges értéke
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int ertek = tabla.get(rowIndex, columnIndex);
        return ertek == 0 ? "" : ertek;
    }

    /**
     * Eldönti, hogy egy cella szerkeszthető-e.
     * Rögzített cellák nem szerkeszthetők.
     * 
     * @param row a sor indexe
     * @param column az oszlop indexe
     * @return true ha szerkeszthető, false ha rögzített
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return !fixCellak[row][column];
    }

    /**
     * Egy cella értékét beállítja.
     * Az érték 0-9 közötti egyjegyű szám vagy üres lehet.
     * 
     * @param aValue az új érték
     * @param row a sor indexe
     * @param column az oszlop indexe
     */
    @Override
    public void setValueAt(Object aValue, int row, int column) {
        if (fixCellak[row][column]) return;

        if (aValue == null) {
            tabla.set(row, column, 0);
            fireTableCellUpdated(row, column);
            return;
        }

        String s = aValue.toString().trim();

        if (s.isEmpty()) {
            tabla.set(row, column, 0);
            fireTableCellUpdated(row, column);
            return;
        }

        if (s.length() == 1 && Character.isDigit(s.charAt(0))) {
            int ertek = s.charAt(0) - '0';
            if (ertek >= 1 && ertek <= 9) {
                tabla.set(row, column, ertek);
            }
        }

        fireTableCellUpdated(row, column);
    }

    /**
     * Az oszlopok osztályát adja vissza.
     * 
     * @param columnIndex az oszlop indexe
     * @return String.class
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    /**
     * A rögzített cellák mátrixát lekéri.
     * 
     * @return a rögzített cellák mátrixa
     */
    public boolean[][] getFixCellak() {
        return fixCellak;
    }

    /**
     * A rögzített cellák mátrixát beállítja.
     * 
     * @param fix az új mátrix
     */
    public void setFixCellak(boolean[][] fix) {
        this.fixCellak = fix;
    }
}
