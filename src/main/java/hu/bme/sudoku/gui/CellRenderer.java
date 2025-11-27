package hu.bme.sudoku.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * JTable cellák megjelenítésre szokásos Sudoku renderere.
 * Kezeli a rögzített cellák, hibás cellák és kiválasztott cellák megjelenítését.
 */
public class CellRenderer extends DefaultTableCellRenderer {
    
    private boolean[][] fix;
    private Set<Point> hibak;
    
    /**
     * A CellRenderer konstruktora.
     * 
     * @param fixCellak a rögzített cellák mátrixa (true = rögzített)
     */
    public CellRenderer(boolean[][] fixCellak) {
        this.fix = fixCellak;
        setHorizontalAlignment(CENTER);
    }

    /**
     * A rögzített cellák mátrixát frissíti.
     * 
     * @param fix az új rögzített cellák mátrixa
     */
    public void setFixCellak(boolean[][] fix) {
        this.fix = fix;
    }
    
    /**
     * A hibás cellák halmazát frissíti.
     * 
     * @param hibak a hibás cellák pozícióinak halmaza
     */
    public void setHibak(Set<Point> hibak) {
        this.hibak = hibak;
    }
    
    /**
     * Renderelje egy cellát megfelelő színek és bordák alapján.
     * Szürke: rögzített cella
     * Piros: hibás cella
     * Zöld: helyes nem rögzített cella
     * Kék: kiválasztott cella
     * 
     * @param table a JTable objektum
     * @param value a cellában megjelenítendő érték
     * @param isSelected igaz ha a cella ki van választva
     * @param hasFocus igaz ha a cellának van fókusza
     * @param row a sor indexe
     * @param column az oszlop indexe
     * @return a renderelt komponens
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                  boolean isSelected, boolean hasFocus,
                                                  int row, int column) {
        
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        Color bg = Color.WHITE;
        Color fg = Color.BLACK;
        
        if (fix != null && fix[row][column]) {
            bg = new Color(230, 230, 230);
        }
        
        if (hibak != null && !hibak.isEmpty()) {
            if (hibak.contains(new Point(row, column))) {
                bg = new Color(255, 200, 200);
            } 
            else if (!fix[row][column]) {
                bg = new Color(200, 255, 200);
            }
        }
        
        if (isSelected) {
            c.setBackground(new Color(180, 200, 255));
            c.setForeground(Color.BLACK);
            return c;
        }
        
        c.setBackground(bg);
        c.setForeground(fg);
        
        
        int top = (row % 3 == 0) ? 3 : 1;
        int left = (column % 3 == 0) ? 3 : 1;
        int bottom = (row == 8) ? 3 : 1;
        int right = (column == 8) ? 3 : ((column + 1) % 3 == 0 ? 3 : 1);
        
        setBorder(new MatteBorder(top, left, bottom, right, Color.BLACK));
        
        return c;
    }
}