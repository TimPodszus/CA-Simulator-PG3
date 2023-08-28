package de.podszus;

public class MainWithoutVMArgs {
    public static void main(String[] args) throws Throwable {
        Main.main(args);
        GameOfLifeAutomaton test = new GameOfLifeAutomaton(8,8,true);


        while (true) {
            IO.readInt();
            test.randomPopulation();
            for (int r = 0; r <= 7 ; r++){
                for (int c = 0; c <= 7; c++){
                    if((test.getCell(r,c)).getState() == 0){
                        System.out.print(" □");
                    }
                    if ((test.getCell(r,c)).getState() == 1){
                        System.out.print(" ■");
                    }
                }       System.out.println();

            }
            test.nextGeneration();

        }

    }

}