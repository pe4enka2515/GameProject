package com.samoylov.gameproject;

public class Kostyl {
    private String name;
    private double hp;
     public Kostyl(String name,double hp){
         this.hp=hp;
         this.name=name;
     }

    public String getName() {
        return name;
    }

    public double getHp() {
        return hp;
    }
}
