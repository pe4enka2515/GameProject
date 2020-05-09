package com.samoylov.gameproject;

public class Kostyl {
    private String name;
    private double hp_now;
    private double Str;
    private double Agi;
    private double Int;
    private double Luc;
    private double Armor;
    public Kostyl(String name,double hp_now, double Str, double Agi, double Int, double Luc, double Armor){
        this.name=name;
        this.hp_now=hp_now;
        this.Str=Str;
        this.Agi=Agi;
        this.Int=Int;
        this.Luc=Luc;
    }

    public String getName() {
        return name;
    }

    public double getHp_now() {
        return hp_now;
    }

    public double getStr() {
        return Str;
    }

    public double getAgi() {
        return Agi;
    }

    public double getInt() {
        return Int;
    }

    public double getLuc() {
        return Luc;
    }
}
