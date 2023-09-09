package de.podszus;

import javafx.application.Application;
import javafx.stage.Stage;

public class CAMain extends Application {
    public static void main(String[] args) {launch(args);


    }

    @Override
        public void start (Stage stage){
        Automaton automaton = new KruemelmonsterAutomaton(10,10,true);
        automaton.randomPopulation();
        CAStage caStage = new CAStage(automaton);
        caStage.show();
    }

}
