package com.samoylov.gameproject.locations;

import java.util.ArrayList;

public class DropList implements RowType {
    private ArrayList<String> dropList = new ArrayList<String>();

    public void addDropList(String name) {
        dropList.add(name);
    }
}
