package de.podszus.model;

public class KruemelmonsterAutomaton extends Automaton {
    public KruemelmonsterAutomaton(int rows, int columns, boolean isTorus){
        super(rows,columns, 10,false,isTorus);
    }
    public KruemelmonsterAutomaton() {
        this(100, 100, true);
    }
    protected Cell transform(Cell cell, Cell[] neighbors) {
        int cellState = cell.getState();
        Cell outputCell = new Cell(cell);

        for (Cell neighbor : neighbors) {
            int neighborState = neighbor.getState();

            if (neighborState == 0 && cellState+1 == getNumberOfStates()) {
                outputCell.setState(0);
                return outputCell;
            }

            if (neighborState == cellState + 1) {
                outputCell.setState(cellState + 1);
                return outputCell;
            }

        }
        return outputCell;
    }

}
