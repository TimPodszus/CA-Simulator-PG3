package de.podszus.controller;

import de.podszus.model.Automaton;
import de.podszus.view.CAStage;
import javafx.scene.control.Slider;

public class SimulationController {
    Automaton automaton;
    CAStage caStage;
    SimulationThread simThread;
    private volatile int speed;

    public static final int DEF_SPEED = 300;
    public SimulationController(Automaton automaton, CAStage caStage) {
        this.automaton = automaton;
        this.caStage = caStage;
        Slider slider = caStage.getSliderSchneller();
        slider.setValue(DEF_SPEED);
        slider.setMax(1000);
        slider.setMin(50);
        speed = DEF_SPEED;

        slider.valueProperty().addListener((obs,o,n )-> speed = n.intValue());
        caStage.getButtonStart().setOnAction(e -> startSimulation());
        caStage.getButtonStop().setOnAction(e-> stopSimulation());
        caStage.getItemStopp().setOnAction(e-> stopSimulation());
        caStage.getItemStart().setOnAction(e-> startSimulation());
    }

    /** Der Block
     * try {synchronized (this){wait(speed);}
     * ist Quelle ChatGPT um eine Fehlermeldung zu vermeiden
     */
    class SimulationThread extends Thread {

        @Override
        public void run() {

            while (!isInterrupted()) {

                automaton.nextGeneration();
                try {
                    synchronized (this){
                    wait(speed);}
                } catch (InterruptedException e) {
                    simThread.interrupt();

                }

            }
        }
    }

    private void startSimulation() {
        disableStartButtons();
        simThread = new SimulationThread();
        simThread.setDaemon(true);
        simThread.start();
    }

    private void stopSimulation() {
        disableStopButtons();
        simThread.interrupt();
    }

    private void disableStartButtons(){
        caStage.getButtonStop().setDisable(false);
        caStage.getItemStopp().setDisable(false);
        caStage.getButtonStep().setDisable(true);
        caStage.getItemSchritt().setDisable(true);
        caStage.getButtonStart().setDisable(true);
        caStage.getItemStopp().setDisable(true);

    }
    private void disableStopButtons(){
        caStage.getButtonStop().setDisable(true);
        caStage.getItemStopp().setDisable(true);
        caStage.getButtonStep().setDisable(false);
        caStage.getItemSchritt().setDisable(false);
        caStage.getButtonStart().setDisable(false);
        caStage.getItemStopp().setDisable(false);


    }

}
