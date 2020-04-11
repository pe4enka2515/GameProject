package com.samoylov.gameproject.locations;

import com.samoylov.gameproject.Data;
import com.samoylov.gameproject.Hero;

import java.util.ArrayList;

public class PlayersList implements LocationList {
    private ArrayList<String> playersList = new ArrayList<>();
    public void addPlayersOnLocationList(String locationName) {
        playersList.clear();
        for (int i = 0; i< Data.bdHeros.size(); i++){
            if(locationName==Data.bdHeros.get(i).getLocation()){
                playersList.add(Data.bdHeros.get(i).getName());
            }
        }
}
}
