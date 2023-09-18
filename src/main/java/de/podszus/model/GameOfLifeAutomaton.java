package de.podszus.model;

public class GameOfLifeAutomaton extends Automaton { //Regel Java S110 disabled
    public GameOfLifeAutomaton(int rows, int columns, boolean isTorus){
        super(rows,columns, 2, true , isTorus);


    }
    public GameOfLifeAutomaton() {
        this(50, 50, true);

    }
    protected Cell transform(Cell cell, Cell[] neighbors){
        int livingNeighbors = 0;
        Cell outputCell = new Cell(cell);
        for (Cell neighbor : neighbors) {
            if (neighbor.getState() == 1) {
                livingNeighbors++;
            }

        }

        if (cell.getState() ==1 ){
            if (livingNeighbors < 2 || livingNeighbors > 3 ){

                outputCell.setState(0);
            }
        } else if (cell.getState() == 0 && (livingNeighbors == 3 )){
                outputCell.setState(1);


        }


        return outputCell;
    }
}