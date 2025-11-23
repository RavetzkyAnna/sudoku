package hu.bme.sudoku.gui;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class SudokuCellEditor extends DefaultCellEditor {

    public SudokuCellEditor() {
        super(new JTextField());
        JTextField tf = (JTextField) getComponent();
        tf.setHorizontalAlignment(JTextField.CENTER);
        ((AbstractDocument) tf.getDocument()).setDocumentFilter(new DigitFilter());
    }

    // Csak 1 karakter hosszú és csak 1–9 lehet
    private static class DigitFilter extends DocumentFilter {

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {

            if (text == null) {
                super.replace(fb, offset, length, null, attrs);
                return;
            }

            String uj = text.trim();

            // üres → engedjük (törlés)
            if (uj.isEmpty()) {
                super.replace(fb, offset, length, "", attrs);
                return;
            }

            // csak egy számjegy
            if (uj.length() == 1 && uj.charAt(0) >= '1' && uj.charAt(0) <= '9') {
                fb.replace(0, fb.getDocument().getLength(), uj, attrs);
            }
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            replace(fb, offset, 0, string, attr);
        }
    }
}
