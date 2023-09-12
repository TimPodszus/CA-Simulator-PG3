package de.podszus;

import de.podszus.controller.CAStageController;
import de.podszus.controller.PopulationPanelController;
import de.podszus.controller.StatePanelController;
import de.podszus.model.Automaton;
import de.podszus.model.KruemelmonsterAutomaton;
import de.podszus.view.CAStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class CAMain extends Application {
    static CAStageController caStageController;
    static PopulationPanelController populationPanelController;
    static StatePanelController statePanelController;

    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage stage) {
        newGame();
    }

    public static void newGame() {
        Automaton automaton = new KruemelmonsterAutomaton(10, 10, true);
        automaton.randomPopulation();
        CAStage caStage = new CAStage(automaton);
        caStageController = new CAStageController(automaton, caStage);
        populationPanelController = new PopulationPanelController(automaton, caStage);
        statePanelController = new StatePanelController(caStage);
        caStage.show();
    }
}
