package de.podszus;

import java.util.ArrayList;

/**
 * Abstrakte Klasse zur Repräsentation eines zellulären Automaten
 */
public abstract class Automaton {
    int rows;
    int columns;
    private Cell[][] cells;
    int numberofStates;
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
    public Automaton(int rows, int columns, int numberOfStates,
                     boolean isMooreNeighborHood, boolean isTorus) {
        this.rows = rows;
        this.columns = columns;
        this.numberofStates = numberOfStates;
        this.isMooreNeighborHood = isMooreNeighborHood;
        this.isTorus = isTorus;
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
     *                  werden!!!)
     * @param neighbors die Nachbarn der betroffenen Zelle (dürfen nicht
     *                  verändert werden!!!)
     * @return eine neu erzeugte Zelle, die gemäß der
     * Transformationsregel aus der
     * betroffenen Zelle hervorgeht
     * @throws Throwable moeglicherweise wirft die Methode eine Exception
     */
    protected abstract Cell transform(Cell cell, Cell[] neighbors)
            throws Throwable;

    /**
     * Liefert die Anzahl an Zuständen des Automaten; gültige Zustände sind
     * int-Werte zwischen 0 und Anzahl-1
     *
     * @return die Anzahl an Zuständen des Automaten
     */
    public int getNumberOfStates() {
        return this.numberofStates;

    }

    /**
     * Liefert die Anzahl an Reihen
     *
     * @return die Anzahl an Reihen
     */
    public int getNumberOfRows() {
        return rows;
    }

    /**
     * Liefert die Anzahl an Spalten
     *
     * @return die Anzahl an Spalten
     */
    public int getNumberOfColumns() {
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
        Cell[][] newCells = new Cell[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (cells[r][c] != null) {
                    newCells[r][c] = cells[r][c];
                } else {
                    newCells[r][c] = new Cell(0);
                }
            }
        }
    }

    /**
     * Liefert Informationen, ob der Automat als Torus betrachtet wird
     *
     * @return true, falls der Automat als Torus betrachtet wird; false
     * sonst
     */
    public boolean isTorus() {
        return this.isTorus;
    }

    /**
     * Ändert die Torus-Eigenschaft des Automaten
     *
     * @param isTorus true, falls der Automat als Torus betrachtet wird;
     *                false sonst
     */
    public void setTorus(boolean isTorus) {
        this.isTorus = isTorus;
    }

    /**
     * Liefert Informationen über die Nachbarschaft-Eigenschaft des
     * Automaten
     * (Hinweis: Die Nachbarschaftseigenschaft kann nicht verändert werden)
     *
     * @return true, falls der Automat die Moore-Nachbarschaft berücksicht;
     * false, falls er die von-Neumann-Nachbarschaft berücksichtigt
     */
    public boolean isMooreNeighborHood() {
        return isMooreNeighborHood;
    }

    /**
     * setzt alle Zellen in den Zustand 0
     */
    public void clearPopulation() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; i < this.columns; j++) {
                this.setState(i, j, 0);
            }
        }
    }

    /**
     * setzt für jede Zelle einen zufällig erzeugten Zustand
     */
    public void randomPopulation() {
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.columns; c++) {
                this.setState(r, c, (int) (Math.random() * numberofStates));
            }
        }
    }

    /**
     * Liefert eine Zelle des Automaten
     *
     * @param row    Reihe der Zelle
     * @param column Spalte der Zelle
     * @return Cell-Objekt an Position row/column
     */
    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    /**
     * Aendert den Zustand einer Zelle
     *
     * @param row    Reihe der Zelle
     * @param column Spalte der Zelle
     * @param state  neuer Zustand der Zelle
     */
    public void setState(int row, int column, int state) {
        if (this.cells[row][column].getState() != state) {
            this.cells[row][column].setState(state);
        }
    }

    /**
     * Aendert den Zustand eines ganzen Bereichs von Zellen
     *
     * @param fromRow    Reihe der obersten Zelle
     * @param fromColumn Spalte der obersten Zelle
     * @param toRow      Reihe der untersten Zelle
     * @param toColumn   Spalte der untersten Zelle
     * @param state      neuer Zustand der Zellen
     */
    public void setState(int fromRow, int fromColumn, int toRow,
                         int toColumn, int state) {
        for (int r = fromRow; r <= toRow; r++) {
            for (int c = fromColumn; c <= toColumn; c++) {
                Cell cell = getCell(r, c);
                cell.state = state;
            }
        }


    }

    /**
     * überführt den Automaten in die nächste Generation; ruft dabei die
     * abstrakte Methode "transform" für alle Zellen auf; Hinweis: zu
     * berücksichtigen sind die Nachbarschaftseigenschaft und die
     * Torus-Eigenschaft des Automaten
     *
     * @throws Throwable Exceptions der transform-Methode werden
     *                   weitergeleitet
     */
    public void nextGeneration() throws Throwable {
        if (this.isTorus) {
            if (this.isMooreNeighborHood) {
                for (int r = 0; r < this.rows; r++) {
                    for (int c = 0; c < columns; c++) {

                        transform(getCell(r, c), getTorusMooreNeighbors(getCell(r, c)));
                    }
                }
                return;
            }
            for (int r = 0; r < this.rows; r++) {
                for (int c = 0; c < columns; c++) {

                    transform(getCell(r, c), getTorusNeumannNeighbors(getCell(r, c)));
                }
            }
            return;
        }
        if (this.isMooreNeighborHood) {
            for (int r = 0; r < this.rows; r++) {
                for (int c = 0; c < columns; c++) {

                    transform(getCell(r, c), getMooreNeighbors(getCell(r, c)));
                }
            }
            return;
        }
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < columns; c++) {

                transform(getCell(r, c), getNeumannNeighbors(getCell(r, c)));
            }
        }
        return;

    }

    private Cell[] getNeumannNeighbors(Cell cell) {
        ArrayList<Cell> neumannNeighbor = new ArrayList();
        int[] dr = {-1, 0, 0, 0, 1,};
        int[] dc = {0, -1, 0, -1, 0,};
        for (int i = 0; i < 5; i++) {
            if (checkValidNeighbors(dr,dc,i)) {
                neumannNeighbor.add(getCell(this.rows + dr[i], this.columns + dc[i]));
            }
        }
        Cell[] Output = (Cell[]) neumannNeighbor.toArray();
        return Output;
    }


    private Cell[] getMooreNeighbors(Cell cell) {
        ArrayList mooreNeighbor = new ArrayList();
        //Lösungsansatz per deltarow(dr) und deltacolumn(dc) Array in Zusammenarbeit mit Anton Neumann
        int[] dr = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1,};
        for (int i = 0; i < 8; i++) {
            if ((this.rows + dr[i]) >= 0 && (this.rows + dr[i]) <= getNumberOfRows() && (this.columns + dc[i]) >= 0 && (this.columns + dc[i]) <= getNumberOfColumns()) {
                mooreNeighbor.add(getCell(this.rows + dr[i], this.columns + dc[i]));
            }
        }
        Cell[] Output = (Cell[]) mooreNeighbor.toArray();
        return Output;
    }

    private Cell[] getTorusNeumannNeighbors(Cell cell) {
        Cell[] torusNeumannNeighbor = new Cell[5];
        int[] dr = {-1, 0, 0, 0, 1, };
        int[] dc = {0, -1, 0, -1, 0,};
        for (int i = 0; i < 4; i++) {
            torusNeumannNeighbor[i] = getCell((this.rows + dr[i] + getNumberOfRows()) % getNumberOfRows(), (this.columns + dc[i] + getNumberOfColumns()) % getNumberOfColumns());
        }
        return torusNeumannNeighbor;
    }

    private Cell[] getTorusMooreNeighbors(Cell cell) {
        Cell[] torusMooreNeighbors = new Cell[9];
        int[] dr = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1,};
        for (int i = 0; i < 8; i++) {
            torusMooreNeighbors[i] = getCell((this.rows + dr[i] + getNumberOfRows()) % getNumberOfRows(), (this.columns + dc[i] + getNumberOfColumns()) % getNumberOfColumns());
        }
        return torusMooreNeighbors;
    }

    private boolean checkValidNeighbors (int[] dr, int[]dc, int i){

        if ((this.rows + dr[i]) >= 0 && (this.rows + dr[i]) <= getNumberOfRows() && (this.columns + dc[i]) >= 0 && (this.columns + dc[i]) <= getNumberOfColumns()){
            return true;
        }
        else {
            return false;
        }
    }


}


