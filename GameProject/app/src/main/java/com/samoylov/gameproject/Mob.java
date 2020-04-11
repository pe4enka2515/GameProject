package com.samoylov.gameproject;

import java.util.ArrayList;

public class Mob extends Kostyl {
    //Класс который описывает монстра с котром игрок может сражаться
//Моб:
//Имя
    private String name;
    private int id=Data.bdMob.size();
    // лвл// опыт// домаг//
    private double lvl, exp, dmg, hp;
    //    локация
    private String location;

    // Конструктор
    public Mob(String name, double lvl, double dmg, double hp, String location) {
        super(name, hp);
        this.name = name;
        this.lvl = lvl;
        this.dmg = dmg;
        this.hp = hp;
        this.location = location;
//        this.id++;
    }

    //    список выподаемых предметов
    private ArrayList<Equipment> dropItems = new ArrayList<>();

    // добавление выподающего предмета
    public void addDropItem(Equipment dropItem) {
        this.dropItems.add(0, dropItem);
    }

    //функция дропа предметов
    public Equipment getDropItem() {
        return dropItems.get(0);
    }
//    шанс выподения предмета? в мобе или в предмете?

//    агрессивность
//    тип поведения(Груповой/одиночка)

//геторы и сеторы

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getLvl() {
        return lvl;
    }

    public double getExp() {
        return exp;
    }

    public double getDmg() {
        return dmg;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public String getLocation() {
        return location;
    }


    //
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
}
