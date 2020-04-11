package com.samoylov.gameproject;

import java.util.ArrayList;

public class Hero extends Kostyl {
    HeroStat heroStat;
   private String name;

    double Lvl = 1;
    double point = 10;
    double EXP = 0;

    double Str = 1;
    private double hp = 20;
    double Dmg = 5;
    double Hr = 0.4;

    double Agi = 1;
    double Ddg = 1;
    double Acc = 0.6;
    double CritPower = 1.1;

    double Int = 1;
    double SkillsPower = 1;
    double Mp = 10;
    double Mr = 0.2;

    double Luc = 1;
    double CritChance = 10;
    double DropChance;
    double SkillChance = 60;

    public Hero(String name,double hp) {
        super(name,hp);
        this.name = name;

        addStat(heroStats);
        addStat(newHeroStats);
    }


    public ArrayList<HeroStat> heroStats = new ArrayList<HeroStat>();
    public ArrayList<HeroStat> newHeroStats = new ArrayList<HeroStat>();

    public ArrayList<HeroStat> getNewHeroStats() {
        return newHeroStats;
    }

    public void setNewHeroStats(ArrayList<HeroStat> newHeroStats) {
        this.newHeroStats = newHeroStats;
    }

    public ArrayList<HeroStat> getHeroStats() {
        return heroStats;
    }

    public void setHeroStats(ArrayList<HeroStat> heroStats) {
        this.heroStats = heroStats;
    }

    void addStat(ArrayList<HeroStat> heroStats1) {
        heroStats1.add(new HeroStat("Сила", Str));
        heroStats1.add(new HeroStat("Ловкость", Agi));
        heroStats1.add(new HeroStat("Интелект", Int));
        heroStats1.add(new HeroStat("Удача", Luc));
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {

        for (int i = 0; i < Data.bdLocations.size(); i++) {
            if (Data.bdLocations.get(i).getLocName().equals(location)) {
                this.locationId = i;
            }
        }
        this.location = location;
    }

    private String location = "Москва";
    private int locationId;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int location) {
        this.locationId = location;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Инвентарь
    private ArrayList<Equipment> inventory = new ArrayList<>();

    //Добавить предмет в инвенарь(в начало инвенторя)
    public void addItemOnInventory(Equipment item) {
        this.inventory.add(0, item);
    }

    //Удалить предмет из инвенторя
    public void removeItemOnInventory(Equipment item) {
        this.inventory.remove(item);
    }

    //Надетая экипировка
    private ArrayList<Equipment> onEquip = new ArrayList<>();

    //одеть придмет
    public void onEquip(Equipment item) {
        this.onEquip.add(0, item);
        setEquipStat(item);
        removeItemOnInventory(item);
    }

    //снять предмет
    public void removeEquip(Equipment item) {
        this.onEquip.remove(item);
        reEquipStat(item);
        addItemOnInventory(item);
    }

    //Получение стат с одетой экипировки
    public void setEquipStat(Equipment item) {
        for (int e = 0; e < heroStats.size(); e++) {
            if (item.getStat().get(e) != 0) {
                heroStats.get(e).setCount(heroStats.get(e).getCount() + item.getStat().get(e));
                newHeroStats.get(e).setCount(newHeroStats.get(e).getCount() + item.getStat().get(e));
            }
        }
    }

    //Убираем статы при снятие экипировки
    public void reEquipStat(Equipment item) {
        for (int e = 0; e < heroStats.size(); e++) {
            if (item.getStat().get(e) != 0) {
                heroStats.get(e).setCount(heroStats.get(e).getCount() - item.getStat().get(e));
                newHeroStats.get(e).setCount(newHeroStats.get(e).getCount() + item.getStat().get(e));
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }


    public void setEXP(double EXP) {
        this.EXP = EXP;
    }

    public double getEXP() {
        return EXP;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getLvl() {
        return Lvl;
    }

    public void setLvl(double lvl) {
        Lvl = lvl;
    }

    public double getStr() {
        return Str;
    }

    public void setStr(double str) {
        Str = str;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getDmg() {
        return Dmg;
    }

    public void setDmg(double dmg) {
        Dmg = dmg;
    }

    public double getHr() {
        return Hr;
    }

    public void setHr(double hr) {
        Hr = hr;
    }

    public double getAgi() {
        return Agi;
    }

    public void setAgi(double agi) {
        Agi = agi;
    }

    public double getDdg() {
        return Ddg;
    }

    public void setDdg(double ddg) {
        Ddg = ddg;
    }

    public double getAcc() {
        return Acc;
    }

    public void setAcc(double acc) {
        Acc = acc;
    }

    public double getCritPower() {
        return CritPower;
    }

    public void setCritPower(double critPower) {
        CritPower = critPower;
    }

    public double getInt() {
        return Int;
    }

    public void setInt(double anInt) {
        Int = anInt;
    }

    public double getSkillsPower() {
        return SkillsPower;
    }

    public void setSkillsPower(double skillsPower) {
        SkillsPower = skillsPower;
    }

    public double getMp() {
        return Mp;
    }

    public void setMp(double mp) {
        Mp = mp;
    }

    public double getMr() {
        return Mr;
    }

    public ArrayList<Equipment> getOnEquip() {
        return onEquip;
    }


    public ArrayList<Equipment> getInventory() {
        return inventory;
    }


    public void setMr(double mr) {
        Mr = mr;
    }

    public double getLuc() {
        return Luc;
    }

    public void setLuc(double luc) {
        Luc = luc;
    }

    public double getCritChance() {
        return CritChance;
    }

    public void setCritChance(double critChance) {
        CritChance = critChance;
    }

    public double getDropChance() {
        return DropChance;
    }

    public void setDropChance(double dropChance) {
        DropChance = dropChance;
    }

    public double getSkillChance() {
        return SkillChance;
    }

    public void setSkillChance(double skillChance) {
        SkillChance = skillChance;

    }

}