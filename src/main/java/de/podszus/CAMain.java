package de.podszus;

import de.podszus.controller.CAStageController;
import de.podszus.controller.PopulationPanelController;
import de.podszus.controller.SimulationController;
import de.podszus.controller.StatePanelController;
import de.podszus.model.Automaton;
import de.podszus.model.GameOfLifeAutomaton;
import de.podszus.view.CAStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class CAMain extends Application {
    static CAStageController caStageController;
    static PopulationPanelController populationPanelController;
    static StatePanelController statePanelController;
    static SimulationController simulationController;

    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage stage) {
        newGame();
    }

    public static void newGame() {
        Automaton automaton = new GameOfLifeAutomaton(100,100,true);
        automaton.randomPopulation();
        CAStage caStage = new CAStage(automaton);
        caStageController = new CAStageController(automaton, caStage);
        simulationController = new SimulationController(automaton,caStage);
        populationPanelController = new PopulationPanelController(automaton, caStage);
        statePanelController = new StatePanelController(caStage);
        caStage.show();
    }

    public static void newGame(Automaton automaton1) {
        automaton1.randomPopulation();
        CAStage caStage = new CAStage(automaton1);
        caStageController = new CAStageController(automaton1, caStage);
        simulationController = new SimulationController(automaton1,caStage);
        populationPanelController = new PopulationPanelController(automaton1, caStage);
        statePanelController = new StatePanelController(caStage);
        caStage.show();



}}
