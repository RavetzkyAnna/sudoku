This is an implementation of the well-known Sudoku game in Java using Swing GUI.

## Documentation

Complete Javadoc documentation is available in PDF format (34 pages):
- **Build**: `mvn javadoc:javadoc` generates HTML documentation in `target/reports/apidocs/`
- **Generate PDF**: Run `mvn clean javadoc:javadoc` and use the build scripts to convert to PDF

### Architecture

- **hu.bme.sudoku.logic**: Core game logic (SudokuTabla, SudokuEllenorzo, SudokuGenerator)
- **hu.bme.sudoku.io**: File I/O operations (FajlKezelo for save/load)
- **hu.bme.sudoku.gui**: Swing GUI components (GameWindow, CellRenderer, SudokuTableModel, SudokuCellEditor)

### Building and Running

Requirements: Java 21, Maven 3.9.11

```bash
mvn clean test      # Run all 17 unit tests
mvn package         # Build the application
java -jar target/sudoku-1.0-SNAPSHOT.jar  # Run the game
```

### Code Quality

- All methods documented with Javadoc
- 17 comprehensive unit tests (logic, I/O, edge cases)
- Maven plugins: compiler-3.13.0, surefire-3.3.0, javadoc-3.12.0

