package hu.bme.sudoku;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class CellRenderer extends DefaultTableCellRenderer {
    
    private final boolean[][] fix;
    private Set<Point> hibak; // hibás cellák
    
    public CellRenderer(boolean[][] fixCellak) {
        this.fix = fixCellak;
        setHorizontalAlignment(CENTER);
    }
    
    public void setHibak(Set<Point> hibak) {
        this.hibak = hibak;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                  boolean isSelected, boolean hasFocus,
                                                  int row, int column) {
        
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        Color bg = Color.WHITE;
        Color fg = Color.BLACK;
        
        // FIX cellák szürkével
        if (fix != null && fix[row][column]) {
            bg = new Color(230, 230, 230);
        }
        
        // Ha vannak hibák → jelöljük ki
        if (hibak != null && !hibak.isEmpty()) {
            
            // Hibás cella → piros
            if (hibak.contains(new Point(row, column))) {
                bg = new Color(255, 200, 200);
            } 
            // Nem hibás → zöld (ha nem fix)
            else if (!fix[row][column]) {
                bg = new Color(200, 255, 200);
            }
        }
        
        // Kijelölés ne üsse felül a hátteret
        if (isSelected) {
            fg = Color.BLACK;
        }
        
        c.setBackground(bg);
        c.setForeground(fg);
        
        // 3×3 blokk vastag rácsvonalak
        int top = (row % 3 == 0) ? 3 : 1;
        int left = (column % 3 == 0) ? 3 : 1;
        int bottom = (row == 8) ? 3 : 1;
        int right = (column == 8) ? 3 : ((column + 1) % 3 == 0 ? 3 : 1);
        
        setBorder(new MatteBorder(top, left, bottom, right, Color.BLACK));
        
        return c;
    }
}
