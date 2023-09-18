package de.podszus.model;

import de.podszus.util.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * Abstrakte Klasse zur Repräsentation eines zellulären Automaten
 */
public abstract class Automaton extends Observable {
    int rows;
    int columns;
    private  transient Cell[][] cells;
    int numberOfStates;
    boolean isMooreNeighborHood;
    boolean isTorus;

    /**
     * Konstruktor
     *
     * @param rows                Anzahl an Reihen
     * @param columns             Anzahl an Spalten
     * @param numberOfStates      Anzahl an Zuständen; die Zustände
     *                            des Automaten
     *                            sind dann die Werte 0 bis
     *                            numberOfStates-1
     * @param isMooreNeighborHood true, falls der Automat die
     *                            Moore-Nachbarschaft
     *                            benutzt; false, falls der Automat die
     *                            von-Neumann-Nachbarschaft benutzt
     * @param isTorus             true, falls die Zellen als
     *                            Torus betrachtet werden
     */
    protected Automaton(int rows, int columns, int numberOfStates,
                        boolean isMooreNeighborHood, boolean isTorus) {
        this.rows = rows;
        this.columns = columns;
        this.numberOfStates = numberOfStates;
        this.isMooreNeighborHood = isMooreNeighborHood;
        this.isTorus = isTorus;
        this.cells = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.cells[i][j] = new Cell(0);
            }
        }
    }


    /**
     * Implementierung der Transformationsregel
     *
     * @param cell      die betroffene Zelle (darf nicht verändert
     *                  werden!)
     * @param neighbors die Nachbarn der betroffenen Zelle (dürfen nicht
     *                  verändert werden!)
     * @return eine neu erzeugte Zelle, die gemäß der
     * Transformationsregel aus der
     * betroffenen Zelle hervorgeht
     *
     */
    protected abstract Cell transform(Cell cell, Cell[] neighbors);


    /**
     * Liefert die Anzahl an Zuständen des Automaten; gültige Zustände sind
     * int-Werte zwischen 0 und Anzahl-1
     *
     * @return die Anzahl an Zuständen des Automaten
     */
    public synchronized int getNumberOfStates() {
        return this.numberOfStates;

    }

    /**
     * Liefert die Anzahl an Reihen
     *
     * @return die Anzahl an Reihen
     */
    public synchronized int getNumberOfRows() {
        return rows;
    }

    /**
     * Liefert die Anzahl an Spalten
     *
     * @return die Anzahl an Spalten
     */
    public synchronized int getNumberOfColumns() {
        return columns;
    }

    /**
     * Ändert die Größe des Automaten; Achtung: aktuelle Belegungen nicht
     * gelöschter Zellen sollen beibehalten werden; neue Zellen sollen im
     * Zustand 0 erzeugt werden
     *
     * @param rows    die neue Anzahl an Reihen
     * @param columns die neue Anzahl an Spalten
     */
    public void changeSize(int rows, int columns) {
        synchronized (this){
        Cell[][] newCells = new Cell[rows][columns];
        for (int r = 0; r < newCells.length; r++) {
            for (int c = 0; c < newCells[r].length; c++) {
                if (r < this.rows && c < this.columns) {
                    newCells[r][c] = cells[r][c];
                } else {
                    newCells[r][c] = new Cell();
                }
            }
        }
        this.rows = rows;
        this.columns = columns;
        this.cells = newCells;}
        notifyObserver();
    }


    /**
     * Liefert Informationen, ob der Automat als Torus betrachtet wird
     *
     * @return true, falls der Automat als Torus betrachtet wird; false
     * sonst
     */

    public synchronized boolean isTorus() {
        return this.isTorus;
    }

    /**
     * Ändert die Torus-Eigenschaft des Automaten
     *
     * @param isTorus true, falls der Automat als Torus betrachtet wird;
     *                false sonst
     */
    public void setTorus(boolean isTorus) { //Sonar Lint Disabled Rule S2886
       synchronized (this){
        this.isTorus = isTorus;}
        notifyObserver();
    }

    /**
     * Liefert Informationen über die Nachbarschaft-Eigenschaft des
     * Automaten
     * (Hinweis: Die Nachbarschafts eigenschaft kann nicht verändert werden)
     *
     * @return true, falls der Automat die Moore-Nachbarschaft berücksichtigt;
     * false, falls er die von-Neumann-Nachbarschaft berücksichtigt
     */
    public synchronized boolean isMooreNeighborHood() {
        return isMooreNeighborHood;
    }

    /**
     * setzt alle Zellen in den Zustand 0
     */
    public void clearPopulation() {
        synchronized (this) {
            for (int r = 0; r < this.getNumberOfRows(); r++) {
                for (int c = 0; c < this.getNumberOfColumns(); c++) {
                    Cell cell = getCell(r, c);
                    cell.setState(0);
                }
            }
        }
        notifyObserver();
    }

    /**
     * setzt für jede Zelle einen zufällig erzeugten Zustand
     */
    Random ran = new Random();

    public void randomPopulation() {
        synchronized (this){
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.columns; c++) {
                if (this.cells[r][c].getState() != ran.nextInt(0, this.numberOfStates)) {
                    getCell(r,  c).setState(ran.nextInt(0, this.numberOfStates));
                }
            }
        }}
        notifyObserver();
    }


    /**
     * Liefert eine Zelle des Automaten
     *
     * @param row    Reihe der Zelle
     * @param column Spalte der Zelle
     * @return Cell-Objekt Position row/column
     */
    public synchronized Cell getCell(int row, int column) {
        return cells[row][column];
    }

    /**
     * Ändert den Zustand einer Zelle
     *
     * @param row    Reihe der Zelle
     * @param column Spalte der Zelle
     * @param state  neuer Zustand der Zelle
     */
    public void setState(int row, int column, int state) {
      synchronized (this){
        if (this.cells[row][column].getState() != state) {
            getCell(row, column).setState(state);
        }}
        notifyObserver();
    }

    /**
     * Ändert den Zustand eines ganzen Bereichs von Zellen
     *
     * @param fromRow    Reihe der obersten Zelle
     * @param fromColumn Spalte der obersten Zelle
     * @param toRow      Reihe der untersten Zelle
     * @param toColumn   Spalte der untersten Zelle
     * @param state      neuer Zustand der Zellen
     */
    public void setState(int fromRow, int fromColumn, int toRow,
                         int toColumn, int state) {
        synchronized (this){
        for (int r = fromRow; r <= toRow; r++) {
            for (int c = fromColumn; c <= toColumn; c++) {
                Cell cell = getCell(r, c);
                cell.setState(state);
            }
        }}
        notifyObserver();

    }

    /**
     * überführt den Automaten in die nächste Generation; ruft dabei die
     * abstrakte Methode "transform" für alle Zellen auf; Hinweis: zu
     * berücksichtigen sind die Nachbarschafts eigenschaft und die
     * Torus-Eigenschaft des Automaten
     *
     *
     */
    public void nextGeneration() {
        synchronized (this){
        Cell[][] nextGeneration = new Cell[rows][columns];

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < columns; c++) {
                Cell cell = getCell(r, c);
                Cell[] neighbors = getNeighbors( r, c);
                nextGeneration[r][c] = transform(cell, neighbors);
            }
        }

        this.cells = nextGeneration;}
        notifyObserver();
    }

    private Cell[] getNeighbors( int row, int col) {
        if (this.isTorus()) {
            if (this.isMooreNeighborHood()) {
                return getTorusMooreNeighbors( row, col);
            } else {
                return getTorusNeumannNeighbors(row, col);
            }
        } else {
            if (this.isMooreNeighborHood()) {
                return getMooreNeighbors(row, col);
            } else {
                return getNeumannNeighbors( row, col);
            }
        }
    }

    private Cell[] getNeumannNeighbors(int r, int c) {
        ArrayList<Cell> neumannNeighbor = new ArrayList<>();
        int[] dr = {-1, 0, 0, 1};
        int[] dc = {0, -1, 1, 0};
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isValidCell(nr, nc, getNumberOfRows(),getNumberOfColumns())) {
                neumannNeighbor.add(new Cell(getCell(nr, nc)));
            }
        }
        return neumannNeighbor.toArray(new Cell[0]);
    }

    private boolean isValidCell(int r, int c, int numRows, int numCols) {
        return r >= 0 && r < numRows && c >= 0 && c < numCols;
    }





    private Cell[] getMooreNeighbors(int r, int c) {
        ArrayList<Cell> mooreNeighbor = new ArrayList<>();
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (isValidCell(nr, nc, getNumberOfRows(), getNumberOfColumns())) {
                mooreNeighbor.add(new Cell(getCell(nr, nc)));
            }
        }

        return mooreNeighbor.toArray(new Cell[0]);
    }

    private Cell[] getTorusNeumannNeighbors( int r, int c) {
        ArrayList<Cell> torusNeumannNeighbor = new ArrayList<>();
        int[] dr = {-1, 0, 0, 1,};
        int[] dc = {0, -1, 1, 0,};
        for (int i = 0; i < 4; i++) {
            torusNeumannNeighbor.add(new Cell(getCell((r + dr[i] + getNumberOfRows()) % getNumberOfRows(), (c + dc[i] + getNumberOfColumns()) % getNumberOfColumns())));
        }
        return torusNeumannNeighbor.toArray(new Cell[0]);
    }

    private Cell[] getTorusMooreNeighbors( int r, int c) {
        ArrayList<Cell> torusMooreNeighbors = new ArrayList<>();
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1,};
        for (int i = 0; i < 8; i++) {
            torusMooreNeighbors.add(new Cell(getCell((r + dr[i] + getNumberOfRows()) % getNumberOfRows(), (c + dc[i] + getNumberOfColumns()) % getNumberOfColumns())));
        }
        return torusMooreNeighbors.toArray(new Cell[0]);
    }

    @Override
    public synchronized boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Automaton observers = (Automaton) o;
        return rows == observers.rows && columns == observers.columns && numberOfStates == observers.numberOfStates && isMooreNeighborHood == observers.isMooreNeighborHood && isTorus == observers.isTorus && Arrays.deepEquals(cells, observers.cells) && Objects.equals(ran, observers.ran);
    }

    @Override
    public synchronized int hashCode() {
        int result = Objects.hash(super.hashCode(), rows, columns, numberOfStates, isMooreNeighborHood, isTorus, ran);
        result = 31 * result + Arrays.deepHashCode(cells);
        return result;
    }
}


