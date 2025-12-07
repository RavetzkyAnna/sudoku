package hu.bme.sudoku.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import hu.bme.sudoku.io.FajlKezelo;
import hu.bme.sudoku.io.FajlKezelo.BetoltesEredmeny;
import hu.bme.sudoku.logic.SudokuEllenorzo;
import hu.bme.sudoku.logic.SudokuGenerator;
import hu.bme.sudoku.logic.SudokuTabla;

/**
 * Sudoku játék grafikus felülete.
 * A fő alkalmazás ablaka, amely a játék kezelőfelületét biztosítja.
 */
public class GameWindow extends JFrame {
    private final CellRenderer renderer;
    private SudokuTabla tabla;
    private final SudokuTableModel tableModel;
    private final JTable table;
    private final SudokuEllenorzo ellenorzo = new SudokuEllenorzo();
    private final SudokuGenerator generator = new SudokuGenerator();
    private final FajlKezelo fajlkezelo = new FajlKezelo();

    /**
     * A GameWindow konstruktora.
     * Inicializálja a 600x600 ablakot a Sudoku táblával, menüvel és megfelelő rendererekkek.
     */
    public GameWindow() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        tabla = new SudokuTabla();
        tableModel = new SudokuTableModel(tabla);
        table = new JTable(tableModel);

        table.setDefaultEditor(Object.class, new SudokuCellEditor());

        renderer = new CellRenderer(tableModel.getFixCellak());
        table.setDefaultRenderer(Object.class, renderer);

        table.setRowHeight(50);
        table.setFont(new Font("SansSerif", Font.BOLD, 24));
        table.setCellSelectionEnabled(true);

        add(new JScrollPane(table), BorderLayout.CENTER);
        setJMenuBar(createMenu());

        setVisible(true);
    }

    /**
     * A menübar létrehozása menüelemekkel (új játék, mentés, betöltés, ellenőrzés, kilépés).
     * 
     * @return a kész menübar objektum
     */
    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menü");

        JMenuItem ujJatek = new JMenuItem(new AbstractAction("Új játék") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ujJatekInditas();
            }
        });

        JMenuItem mentes = new JMenuItem(new AbstractAction("Mentés") {
            @Override
            public void actionPerformed(ActionEvent e) {
                jatekMentes();
            }
        });

        JMenuItem betoltes = new JMenuItem(new AbstractAction("Betöltés") {
            @Override
            public void actionPerformed(ActionEvent e) {
                jatekBetoltes();
            }
        });

        JMenuItem ellenorzes = new JMenuItem(new AbstractAction("Ellenőrzés") {
            @Override
            public void actionPerformed(ActionEvent e) {
                jatekEllenorzes();
            }
        });

        JMenuItem kilepes = new JMenuItem(new AbstractAction("Kilépés") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(ujJatek);
        fileMenu.add(mentes);
        fileMenu.add(betoltes);
        fileMenu.add(ellenorzes);
        fileMenu.addSeparator();
        fileMenu.add(kilepes);

        menuBar.add(fileMenu);
        return menuBar;
    }

    /**
     * Új játék indítása.
     * A felhasználó kiválaszt egy nehézségi szintet, majd egy új játékkeret jön létre.
     */
    private void ujJatekInditas() {
        Object[] lehetosegek = { "Kezdő", "Haladó", "Profi" };
        int valasz = JOptionPane.showOptionDialog(this,
                "Válassz nehézséget:",
                "Új játék",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                lehetosegek,
                lehetosegek[0]);

        SudokuGenerator.Nehezseg szint = switch (valasz) {
            case 0 -> SudokuGenerator.Nehezseg.KEZDO;
            case 1 -> SudokuGenerator.Nehezseg.HALADO;
            case 2 -> SudokuGenerator.Nehezseg.PROFI;
            default -> SudokuGenerator.Nehezseg.KEZDO;
        };

        int[][] ujt = generator.generalUjJatek(szint);
        tabla = new SudokuTabla(ujt);
        tableModel.setTabla(tabla);
        tableModel.fireTableDataChanged();

        renderer.setHibak(null);
        table.repaint();
    }

    /**
     * A jelenlegi játék állapotának mentése fájlba.
     * Fájlválasztó dialógus segítségével adható meg a mentés helye.
     */
    private void jatekMentes() {
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                fajlkezelo.mentes(
                    fc.getSelectedFile().getAbsolutePath(),
                    tabla.getTabla(),
                    tableModel.getFixCellak()
                );
                JOptionPane.showMessageDialog(this, "Sikeres mentés!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Hiba történt a mentéskor.");
            }
        }
    }

    /**
     * Egy korábban mentett játék betöltése.
     * Fájlválasztó dialógus segítségével adható meg a betöltendő fájl.
     */
    private void jatekBetoltes() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                BetoltesEredmeny eredm = fajlkezelo.betoltes(
                    fc.getSelectedFile().getAbsolutePath()
                );

                tabla = new SudokuTabla(eredm.tabla());
                tableModel.setTabla(tabla);
                tableModel.setFixCellak(eredm.fix());
                renderer.setFixCellak(eredm.fix());
                renderer.setHibak(null);
                tableModel.fireTableDataChanged();
                table.repaint();

                JOptionPane.showMessageDialog(this, "Betöltés kész!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Hiba történt a betöltéskor.");
            }
        }
    }

    /**
     * A jelenlegi játéktábla ellenőrzése.
     * Megjelenik, ha van hiba, és gratulálok üzenet jöhet ha kész a játék.
     */
    private void jatekEllenorzes() {
        int[][] t = tabla.getTabla();
        Set<Point> hibak = ellenorzo.hibasPoziciok(t);
        renderer.setHibak(hibak);
        table.repaint();

        if (hibak.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Gratulálok! Kész a sudoku!",
                "Kész",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                "Hibás vagy hiányos mezők találhatók a táblán!",
                "Hiba",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * A Sudoku alkalmazás belépési pontja.
     * Az alkalmazást SwingUtilities.invokeLater segítségével indítja az EDT szálon.
     * 
     * @param args parancssor argumentumok (nem használt)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameWindow::new);
    }
}
