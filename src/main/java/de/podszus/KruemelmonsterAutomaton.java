package de.podszus;

public class KruemelmonsterAutomaton extends Automaton {
    public KruemelmonsterAutomaton(int rows, int columns, boolean isTorus){
        super(rows,columns, Integer.MAX_VALUE,false,isTorus);
    }
    public KruemelmonsterAutomaton() {
        this(100, 100, true);
    }
    protected Cell transform(Cell cell, Cell[] neighbors){
        Cell outputCell = new Cell(cell);

        for (int i = 0 ; i < neighbors.length; i++){
            if (neighbors[i].getState() == 0){
                if( cell.getState() == Integer.MAX_VALUE){
                    outputCell.setState(0);
                    break;
                }

            }
            if (neighbors[i].getState() == (cell.getState()+1)){

                outputCell.setState(cell.getState()+1);
                break;
            }
        }

        return outputCell;
    }
}
