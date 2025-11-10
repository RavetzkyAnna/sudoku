package hu.bme.sudoku;

import javax.swing.table.AbstractTableModel;

public class SudokuTableModel extends AbstractTableModel {

    private SudokuTabla tabla;
    private boolean[][] fixCellak = new boolean[9][9];  // előre kitöltött cellák zárolva
    
    public SudokuTableModel(SudokuTabla tabla) {
        this.tabla = tabla;
        inicializalFixCellak();
    }

    public void setTabla(SudokuTabla tabla) {
        this.tabla = tabla;
        inicializalFixCellak();
        fireTableDataChanged();
    }

    // beállítja, mely cellák nem módosíthatók (ahol nem 0 a generált tábla)
    private void inicializalFixCellak() {
        int[][] t = tabla.getTabla();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fixCellak[i][j] = (t[i][j] != 0);
            }
        }
    }

    @Override
    public int getRowCount() {
        return 9;
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int ertek = tabla.get(rowIndex, columnIndex);
        return ertek == 0 ? "" : ertek;   // üres cella → üres string
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return !fixCellak[row][column];  // előre kitöltött mezők TILTVA
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
    if (fixCellak[row][column]) return;

    if (aValue == null) {
        tabla.set(row, column, 0);
        fireTableCellUpdated(row, column);
        return;
    }

    String s = aValue.toString().trim();

    // üres input → cella törlése
    if (s.isEmpty()) {
        tabla.set(row, column, 0);
        fireTableCellUpdated(row, column);
        return;
    }

    // CSAK EGY DARAB SZÁMJEGY
    if (s.length() == 1 && Character.isDigit(s.charAt(0))) {
        int ertek = s.charAt(0) - '0';
        if (ertek >= 1 && ertek <= 9) {
            tabla.set(row, column, ertek);
        }
    }

    fireTableCellUpdated(row, column);
}

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;  // a JTable szövegként kezeli a számokat
    }

    public boolean[][] getFixCellak() {
        return fixCellak;
    }
}
