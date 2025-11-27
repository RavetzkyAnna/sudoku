package hu.bme.sudoku.gui;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * JTable cellaszerkesztő Sudoku számokhoz.
 * Csak az 1-9 közötti egyjegyű számok beírása engedélyezett.
 */
public class SudokuCellEditor extends DefaultCellEditor {

    /**
     * A SudokuCellEditor konstruktora.
     * Egy JTextField-et hoz létre az 1-9 számok szűrésével.
     */
    public SudokuCellEditor() {
        super(new JTextField());
        JTextField tf = (JTextField) getComponent();
        tf.setHorizontalAlignment(JTextField.CENTER);
        ((AbstractDocument) tf.getDocument()).setDocumentFilter(new DigitFilter());
    }

    /**
     * DocumentFilter az 1-9 számokra korlátozva.
     * Biztosítja, hogy csak Sudoku-kompatibilis számok írhatók be.
     */
    private static class DigitFilter extends DocumentFilter {

        /**
         * Szöveg behelyezésének és helyettesítésének kezelése.
         * Csak az 1-9 közötti egyjegyű számokat engedélyezi.
         * 
         * @param fb a FilterBypass objektum
         * @param offset az offset az azon belül beszúrandó
         * @param length az eltávolítandó hossz
         * @param text az azon belül beszúrandó szöveg
         * @param attrs az attribútumok
         * @throws BadLocationException ha az offset érvénytelen
         */
        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {

            if (text == null) {
                super.replace(fb, offset, length, null, attrs);
                return;
            }

            String uj = text.trim();

            if (uj.isEmpty()) {
                super.replace(fb, offset, length, "", attrs);
                return;
            }

            if (uj.length() == 1 && uj.charAt(0) >= '1' && uj.charAt(0) <= '9') {
                fb.replace(0, fb.getDocument().getLength(), uj, attrs);
            }
        }

        /**
         * Szöveg beszúrásának kezelése.
         * 
         * @param fb a FilterBypass objektum
         * @param offset az offset az azon belül beszúrandó
         * @param string a beszúrandó szöveg
         * @param attr az attribútumok
         * @throws BadLocationException ha az offset érvénytelen
         */
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            replace(fb, offset, 0, string, attr);
        }
    }
}
