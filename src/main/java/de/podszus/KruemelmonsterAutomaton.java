package de.podszus;

public class KruemelmonsterAutomaton extends Automaton {
    public KruemelmonsterAutomaton(int rows, int columns, boolean isTorus){
        super(rows,columns, 20,false,isTorus);
    }
    public KruemelmonsterAutomaton() {
        this(100, 100, true);
    }
    protected Cell transform(Cell cell, Cell[] neighbors){
        Cell outputCell = new Cell(cell);

        for (Cell neighbor : neighbors) {
            if (neighbor.getState() == 0 && (cell.getState() == Integer.MAX_VALUE)) {
                    outputCell.setState(0);
                    break;


            }
            if (neighbor.getState() == (cell.getState() + 1)) {

                outputCell.setState(cell.getState() + 1);
                break;
            }
        }

        return outputCell;
    }
}
