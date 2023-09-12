package de.podszus.util;

import java.util.ArrayList;

public class Observable extends ArrayList<Observer> {

    public void notifyObserver() {
        for (Observer ob : this) {
            ob.update();
        }
    }

}