package com.samoylov.gameproject.locations;

import java.util.ArrayList;

public class NameList implements LocationList{
    private ArrayList<String> name = new ArrayList<String>();

    public void addName(String name) {
        this.name.add(name);
    }
}
