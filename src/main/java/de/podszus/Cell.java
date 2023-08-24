package de.podszus;

/**
 * Klasse zur Repräsentation einer Zelle eines zellulären Automaten
 */
public class Cell {
    /**
     * Initialisiert die Zelle im Zustand 0
     */
    int state;


    public Cell() {
        this.state = 0;
    }

    /**
     * Initialisiert die Zelle im übergebenen Zustand
     *
     * @param state
     */
    public Cell(int state) {
        this.state = state;
    }

    /**
     * Copy-Konstruktor; initialisiert die Zelle mit dem Zustand der
     * übergebenen Zelle
     *
     * @param cell
     */
    public Cell(Cell cell) {
        this.state = cell.getState();
    }

    /**
     * Liefert den Zustand der Zelle
     *
     * @return Zustand der Zelle
     */
    public int getState() {
        return this.state;
    }

    /**
     * Ändert den Zustand der Zelle
     *
     * @param state der neue Zustand der Zelle
     */
    void setState(int state) {
        this.state = state;
    }
}

