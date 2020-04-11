package com.samoylov.gameproject.locations;

import java.util.ArrayList;

public class Transitions implements LocationList {

    private ArrayList<String> transition = new ArrayList<>();

    public void addTransition(String name) {
        transition.add(name);
    }

}
