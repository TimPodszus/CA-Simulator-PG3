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
    }


    class SimulationThread extends Thread {

        @Override
        public void run() {

            while (!isInterrupted()) {

                automaton.nextGeneration();
                //Quelle: ChatGPT
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
        simThread = new SimulationThread();
        simThread.setDaemon(true);
        simThread.start();
    }

    private void stopSimulation() {
        simThread.interrupt();
    }

}
