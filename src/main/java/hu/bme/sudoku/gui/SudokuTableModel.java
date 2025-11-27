package hu.bme.sudoku.gui;

import javax.swing.table.AbstractTableModel;

import hu.bme.sudoku.logic.SudokuTabla;

public class SudokuTableModel extends AbstractTableModel {

    private SudokuTabla tabla;
    private boolean[][] fixCellak = new boolean[9][9];
    
    public SudokuTableModel(SudokuTabla tabla) {
        this.tabla = tabla;
        inicializalFixCellak();
    }

    public void setTabla(SudokuTabla tabla) {
        this.tabla = tabla;
        inicializalFixCellak();
        fireTableDataChanged();
    }

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
        return ertek == 0 ? "" : ertek;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return !fixCellak[row][column];
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

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public boolean[][] getFixCellak() {
        return fixCellak;
    }

    public void setFixCellak(boolean[][] fix) {
        this.fixCellak = fix;
    }
    
}
