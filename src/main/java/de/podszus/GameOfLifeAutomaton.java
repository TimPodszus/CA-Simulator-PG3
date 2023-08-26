package de.podszus;

public class GameOfLifeAutomaton extends Automaton {
    public GameOfLifeAutomaton(int rows, int columns, boolean isTorus){
        super(rows,columns, 2, true , isTorus);


    }
    public GameOfLifeAutomaton() {
        this(50, 50, true);

    }
    protected Cell transform(Cell cell, Cell[] neighbors){
        int livingneighbors = 0;
        Cell outputCell = new Cell(cell);
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i].getState() == 1){
                livingneighbors++;
            }

        }

        if (cell.getState() ==1 ){
            if (livingneighbors < 2 || livingneighbors > 3 ){

                outputCell.setState(0);
            }
        } else if (cell.getState() == 0) {
            if (livingneighbors == 3 ){
                outputCell.setState(1);
            }

        }


        return outputCell;
    }
}