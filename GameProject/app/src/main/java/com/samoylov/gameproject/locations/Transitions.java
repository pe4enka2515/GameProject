package com.samoylov.gameproject.locations;

import java.util.ArrayList;

public class Transitions implements RowType {

    private String locationName;
    public Transitions(String locationName){
        this.locationName=locationName;
    }

    public String getLocationName() {
        return locationName;
    }
}
