package de.podszus;

public class MainWithoutVMArgs {
    public static void main(String[] args) throws Throwable {
        Main.main(args);
        GameOfLifeAutomaton test = new GameOfLifeAutomaton(8,8,true);
     test.getCell(5,5).setState(1);
        test.getCell(5,4).setState(1);
        test.getCell(4,6).setState(1);
        test.getCell(6,5).setState(1);

        while (true) {
            IO.readInt();
            for (int r = 0; r <= 7 ; r++){
                for (int c = 0; c <= 7; c++){
                    if((test.getCell(r,c)).getState() == 0){
                        System.out.print("0");
                    }
                    if ((test.getCell(r,c)).getState() == 1){
                        System.out.print("1");
                    }
                }                    System.out.println();

            }
            test.nextGeneration();
        }

    }

}