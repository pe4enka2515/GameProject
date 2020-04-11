package com.samoylov.gameproject.locations;

import com.samoylov.gameproject.Mob;

import java.util.ArrayList;

public class MobList implements LocationList {
    private ArrayList<Mob> mobList = new ArrayList<>();

    public void addMobList(Mob mob) {
        mobList.add(mob);
    }
}
