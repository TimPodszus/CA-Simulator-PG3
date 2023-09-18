package de.podszus.controller;

import de.podszus.CAMain;
import de.podszus.model.Automaton;
import de.podszus.view.CAStage;
import de.podszus.view.PopulationChangeBox;
import de.podszus.view.PopulationsPanel;
import javafx.application.Platform;

public class CAStageController {
    Automaton automaton;
    PopulationsPanel populationsPanel;
    CAStage stage;

    public CAStageController(Automaton automaton, CAStage stage) {
        this.stage = stage;
        this.populationsPanel = stage.getPopulationsPanel();
        this.automaton = automaton;
        stage.getItemNeu().setOnAction(e -> CAMain.newGame());
        stage.getItemBeenden().setOnAction(e -> Platform.exit());
        stage.getItemGroesseAendern().setOnAction(e -> {
            PopulationChangeBox changeBox = new PopulationChangeBox(automaton);
            automaton.changeSize(changeBox.getRowsOutput(), changeBox.getColumnsOutput());
            populationsPanel.update();
        });
        stage.getItemLoeschen().setOnAction(e -> {
            automaton.clearPopulation();
            populationsPanel.update();
        });
        stage.getItemErzeugen().setOnAction(e -> {
            automaton.randomPopulation();
            populationsPanel.update();
        });
        stage.getItemTorus().setOnAction(e -> {
            automaton.setTorus(!automaton.isTorus());
            stage.getButtonTorus().setSelected(!stage.getButtonTorus().isSelected());
        });
        stage.getButtonNeuerAutomat().setOnAction(e -> CAMain.newGame());
        stage.getButtonGroesserePopulation().setOnAction(e -> {
            PopulationChangeBox changeBox = new PopulationChangeBox(automaton);
            automaton.changeSize(changeBox.getRowsOutput(), changeBox.getColumnsOutput());
            populationsPanel.update();
        });
        stage.getButtonZustandNull().setOnAction(e -> {
            automaton.clearPopulation();
            populationsPanel.update();
        });
        stage.getButtonZufaelligePopulation().setOnAction(e -> {
            automaton.randomPopulation();
            populationsPanel.update();
        });
        stage.getButtonTorus().setOnAction(e -> {
            automaton.setTorus(!automaton.isTorus());
            stage.getItemTorus().setSelected(!stage.getItemTorus().isSelected());
        });




        stage.getButtonStep().setOnAction(e->{
            automaton.nextGeneration();
            populationsPanel.update();
        });

        stage.getItemSchritt().setOnAction(e->{
            automaton.nextGeneration();
            populationsPanel.update();
        });




    }

}
