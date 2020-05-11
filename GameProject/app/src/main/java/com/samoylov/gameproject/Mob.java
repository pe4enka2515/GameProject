package com.samoylov.gameproject;

import java.util.ArrayList;

public class Mob extends Kostyl {
    //Класс который описывает моба, с которым игрок может сражаться
    private String name;
    private int id = Data.bdMob.size();
    private double Str, Agi, Int, Luc, Lvl, hp, hp_now, EXP, Armor;
    private String location;

    // Конструктор
    public Mob(String name, double hp_now, double Str, double Agi, double Int, double Luc,
               double Lvl, String location, double hp, double EXP, double Armor) {
        super(name, hp_now, Str, Agi, Int, Luc, Armor);
        this.name = name;
        this.hp_now = hp_now;
        this.Lvl = Lvl;
        this.location = location;
        this.hp = hp;
        this.EXP = EXP;
        this.Armor = Armor;
    }

    //    список выпадаемых предметов
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
        return Lvl;
    }

    public double getEXP() {
        return EXP;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double Hp) {
        hp = Hp;
    }

    public double getHp_now() {
        return hp_now;
    }

    public void setHp_now(double Hp_now) {
        hp_now = Hp_now;
    }

    public String getLocation() {
        return location;
    }

    public double getArmor() {
        return ((100 * Armor * Armor) / (Armor * Armor + 80 * Armor));//броня хранится в числах, возвращается в %;
    }

    public void setArmor() {
        Armor = Armor;
    }

    //Поменял 10.05
    public double getDmg() {
        return Math.floor((1 + Lvl / 10) * (5 + (Str / 4) + (Str / 10) * 2));//крит не прошел, расчет урон без крита
    }

    public double getAcc() {
        return 80;
//        return (50 + (40 * Agi * Agi) / (Agi * Agi + 40 * Agi) / 10);//50% - базовая меткость
    }

    public double getCritPower() {
        return 1.5;
//        return (1.5 + (190 * Agi * Agi) / (Agi * Agi + 100 * Agi)/100);//110% - базовый крит у всех (пока 150%, для тестов)
    }

    public double getCritChance() {
        return 50;
//        return (50 + (90 * Luc * Luc) / (Luc * Luc + 35 * Luc) / 10); //10% - базовый шанс крита (пока 50%, для тестов)
    }

}

