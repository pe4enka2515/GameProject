package com.samoylov.gameproject;

import java.util.ArrayList;

public class Hero extends Kostyl {
    HeroStat heroStat;
    private String name;
    private String location = "Москва";

    private double Lvl = 1;
    private double point = 10;
    private double EXP = 0;

    private double Str = 1;
    private double Agi = 1;
    private double Int = 1;
    private double Luc = 1;

    private double hp;
    private double Dmg = 5;
    private double Hr = 0.4;

    private double Ddg = 1;
    private double Acc = 0.6;
    private double CritPower = 1.1;

    private double SkillsPower = 1;
    private double Mp = 10;
    private double Mr = 0.2;

    private double CritChance = 10;
    private double DropChance;
    private double SkillChance = 60;

    private double hp_now = hp;
    private double Armor;

    public Hero(String name,double hp_now, double Str, double Agi, double Int, double Luc,
                double Lvl, String location, double hp, double Armor) {
        super(name,hp_now, Str, Agi, Int, Luc, Armor);
        this.name = name;
        this.hp_now = hp_now;
        this.Lvl = Lvl;
        this.location = location;
        this.hp = hp;
        this.Armor = Armor;

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

    //Добавить предмет в инвенарь(в начало инвентаря)
    public void addItemOnInventory(Equipment item) {
        this.inventory.add(0, item);
    }

    //Удалить предмет из инвентаря
    public void removeItemOnInventory(Equipment item) {
        this.inventory.remove(item);
    }

    //Надетая экипировка
    private ArrayList<Equipment> onEquip = new ArrayList<>();

    //надеть придмет
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

    public void setPoint(double Point) {
        point = Point;
    }

    public double getEXP() {
        return EXP;
    }

    public void setEXP(double eXp) {
        EXP = eXp;
    }

    public void UpLvl() {
        while (30*Math.pow(4, (Lvl-1)) <= EXP) {
            if (30*Math.pow(4, (Lvl-1)) <= EXP) {
                EXP = EXP - 30*Math.pow(4, (Lvl-1));
                Lvl++;
                setPoint(point + Math.floor(Math.log(Lvl) + 1));
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        name = Name;
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
        return Math.floor((1 + Lvl/10) * (20 + Str + (Str/10 * 4)));
    }

    public void setHp(double Hp) { hp = Hp; }


    public double getHp_now() {
        return hp_now;
    }

    public void setHp_now(double Hp_now) { hp_now = Hp_now; }

    public double getDmg() {
        if (Math.random() * 100 <= getAcc()) {//попадание
            if (Math.random() * 100 <= getCritChance()) //Шанс крита
                return Math.floor(((1 + Lvl / 10) * (5 + (Str / 4) + (Str / 10) * 2)) * getCritPower());//Крит прошел, расчет урона с крита
            else
                return Math.floor((1 + Lvl / 10) * (5 + (Str / 4) + (Str / 10) * 2));//крит не прошел, расчет урон без крита
        } else return 0;
    }

    public void setDmg(double dmg) { Dmg = dmg; }

    //    public double getHr() {
//        return Math.floor(0.4 + Str/15 + Lvl/10);
//    }
    public double getHr() {
        return 1;
    }

    public void setHr(double hr) { Hr = hr; }

    public double getAgi() {
        return Agi;
    }

    public void setAgi(double agi) { Agi = agi; }

    public double getDdg() {
        return Math.floor((20 * Agi *Agi) / (Agi * Agi + 30 * Agi) / 100);
    }

    public void setDdg(double ddg) { Ddg = ddg; }

    public double getAcc() {
        return (50 + (40 * Agi * Agi) / (Agi * Agi + 40 * Agi) / 10);//50% - базовая меткость
    }

    public void setAcc(double acc) { Acc = acc; }

    public double getCritPower() {
        return (1.5 + (190 * Agi * Agi) / (Agi * Agi + 100 * Agi)/100);//110% - базовый крит у всех (пока 150%, для тестов)
    }

    public void setCritPower(double critPower) { CritPower = critPower; }

    public double getInt() {
        return Int;
    }

    public void setInt(double anInt) { Int = anInt; }

    public double getSkillsPower() {
        return Math.floor((1 + Lvl/10) * (Int/2));
    }

    public void setSkillsPower(double skillsPower) { SkillsPower = skillsPower; }

    public double getMp() {
        return Math.floor((1 + Lvl/10) * (10 + Int*2 + (Lvl/10*20)));
    }

    public void setMp(double mp) { Mp = mp; }

    public double getMr() {
        return Math.floor((1 + Lvl/10) * (0.2 + Int*0.03 + Lvl*0.05));
    }

    public void setMr(double mr) { Mr = mr; }

    public double getLuc() {
        return Luc;
    }

    public void setLuc(double luc) {
        Luc = luc;
    }


    public double getCritChance() {
        return (50 + (90 * Luc * Luc) / (Luc * Luc + 35 * Luc) / 10); //10% - базовый шанс крита (пока 50%, для тестов)
    }

    public void setCritChance(double critChance) { CritChance = critChance; }

    public double getDropChance() {
        return DropChance;
    }

    public void setDropChance(double dropChance) { DropChance = dropChance; }

    public double getSkillChance() {
        return (60 + (40 * Luc * Luc) / (Luc * Luc + 35 * Luc) / 100);
    }

    public void setSkillChance(double skillChance) { SkillChance = skillChance; }

    public double getArmor() {
        return ((100 * Armor * Armor) / (Armor * Armor + 80 * Armor));//броня хранится в числах, возвращается в %
    }

    public void setArmor() { Armor = Armor; }

    ////////////////////
    public ArrayList<Equipment> getOnEquip() {
        return onEquip;
    }


    public ArrayList<Equipment> getInventory() {
        return inventory;
    }
    /////////////////
    public double getAtribut(int num) {
        switch (num) {
            case 0:
                return Str;
            case 1:
                return Agi;
            case 2:
                return Int;
            case 3:
                return Luc;
            default:
                return Str;
        }
    }
    public void setAtribut(int num, double atribut) {
        switch (num) {
            case 0:
                Str = atribut;
                break;
            case 1:
                Agi = atribut;
                break;
            case 2:
                Int = atribut;
                break;
            case 3:
                Luc = atribut;
                break;
            default:
                Str = 1000;
                break;
        }
    }
}