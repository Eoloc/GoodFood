package main;

import java.util.Observable;

public class Modele extends Observable {



    public Modele() {


    }


    public void actualiser(int c) {
        setChanged();
        notifyObservers();
    }
}

