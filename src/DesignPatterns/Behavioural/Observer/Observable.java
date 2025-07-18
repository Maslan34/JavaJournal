package DesignPatterns.Behavioural.Observer;

import java.util.ArrayList;
import java.util.List;


public abstract class Observable {

    private List<Observer> observers;

    public Observable() {
        observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.report(this);
        }
    }
}
