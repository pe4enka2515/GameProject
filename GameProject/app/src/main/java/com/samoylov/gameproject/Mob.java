package com.samoylov.gameproject;

import java.util.ArrayList;

public class Mob extends Kostyl implements Person {
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {

    }

    public int getId() {
        return id;
    }

    @Override
    public double getLvl() {
        return Lvl;
    }

    @Override
    public void setLvl(double Lvl) {

    }

    @Override
    public void setStr(double Str) {

    }

    @Override
    public double getEXP() {
        return EXP;
    }

    @Override
    public void setEXP(double EXP) {

    }

    @Override
    public double getHp() {
        return hp;
    }

    @Override
    public void setHp(double Hp) {
        hp = Hp;
    }

    @Override
    public double getHp_now() {
        return Math.floor(hp_now);
    }

    @Override
    public void setHp_now(double Hp_now) {
        hp_now = Hp_now;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public double getArmor() {
        return ((100 * Armor * Armor) / (Armor * Armor + 80 * Armor));//броня хранится в числах, возвращается в %;
    }

    @Override
    public double getAtribut(int num) {
        return 0;
    }

    @Override
    public void setAtribut(int num, double atribut) {

    }

    @Override
    public void setArmor(double Armor) {
        this.Armor = Armor;
    }

    //Поменял 10.05
    @Override
    public double getDmg() {
        return Math.floor((1 + Lvl / 10) * (5 + (Str / 4) + (Str / 10) * 2));//крит не прошел, расчет урон без крита
    }

    @Override
    public void setDmg(double Dmg) {

    }

    @Override
    public double getHr() {
        return 0;
    }

    @Override
    public void setHr(double Hr) {

    }

    @Override
    public void setAgi(double Agi) {

    }

    @Override
    public double getDdg() {
        return 0;
    }

    @Override
    public void setDdg(double Ddg) {

    }

    @Override
    public double getAcc() {
        return 80;
//        return (50 + (40 * Agi * Agi) / (Agi * Agi + 40 * Agi) / 10);//50% - базовая меткость
    }

    @Override
    public void setAcc(double Acc) {

    }

    @Override
    public double getCritPower() {
        return 1.5;
//        return (1.5 + (190 * Agi * Agi) / (Agi * Agi + 100 * Agi)/100);//110% - базовый крит у всех (пока 150%, для тестов)
    }

    @Override
    public void setCritPower(double CritPower) {

    }

    @Override
    public void setInt(double Int) {

    }

    @Override
    public double getSkillsPower() {
        return 0;
    }

    @Override
    public void setSkillsPower(double SkillsPower) {

    }

    @Override
    public double getMp() {
        return 0;
    }

    @Override
    public void setMp(double Mp) {

    }

    @Override
    public double getMr() {
        return 0;
    }

    @Override
    public void setMr(double Mr) {

    }

    @Override
    public void setLuc(double Luc) {

    }

    @Override
    public double getCritChance() {
        return 50;
//        return (50 + (90 * Luc * Luc) / (Luc * Luc + 35 * Luc) / 10); //10% - базовый шанс крита (пока 50%, для тестов)
    }

    @Override
    public void setCritChance(double CritChance) {

    }

    @Override
    public double getDropChance() {
        return 0;
    }

    @Override
    public void setDropChance(double DropChance) {

    }

    @Override
    public double getSkillChance() {
        return 0;
    }

    @Override
    public void setSkillChance(double SkillChance) {

    }


//    @Override
//    public String getName2() {
//        return name;
//    }
//
//    @Override
//    public double getAcc2() {
//        return 80;
//    }
//
//    @Override
//    public double getCritChance2() {
//        return 50;
//    }
//
//    @Override
//    public double getCritPower2() {
//        return 1.5;
//    }
//
//    @Override
//    public double getDmg2() {
//        return Math.floor((1 + Lvl / 10) * (5 + (Str / 4) + (Str / 10) * 2));
//    }
//
//    @Override
//    public double getHp_now2() {
//        return hp_now;
//    }
//
//    @Override
//    public void setHp_now2(double Hp_now) {
//        hp_now = Hp_now;
//    }
}

