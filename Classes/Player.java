package Classes;

import java.util.Scanner;
import java.util.ArrayList;

public class Player {

  private String name;
  private int level;
  private int xp;
  private int pb;
  private int ib;
  private int str;
  private int dex;
  private int con;
  private int itl;
  private int wis;
  private int cha;

  private ArrayList<Weapon> weapon;

  public Player() {
    name = "";
    level = 0;
    xp = 0;
    pb = 0;
    ib = 0;
    str = 0;
    dex = 0;
    con = 0;
    itl = 0;
    wis = 0;
    cha = 0;

    weapon = new ArrayList<>();
  }

  // SETTERS
  public void setName(String name) {
    this.name = name;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setXP(int xp) {
    this.xp = xp;
  }

  public void addXP(int xpToAdd) {
    this.xp += xpToAdd;
  }

  public void setPb(int pb) {
    this.pb = pb;
  }

  public void setIB(int ib) {
    this.ib = ib;
  }

  public void setStr(int str) {
    this.str = str;
  }

  public void setDex(int dex) {
    this.dex = dex;
  }

  public void setCon(int con) {
    this.con = con;
  }

  public void setItl(int itl) {
    this.itl = itl;
  }

  public void setWis(int wis) {
    this.wis = wis;
  }

  public void setCha(int cha) {
    this.cha = cha;
  }

  public void setWeapon(Weapon weapon) {
    this.weapon.add(weapon);
  }

  // GETTERS
  public String getName() {
    return name;
  }

  public int getLevel() {
    return level;
  }

  public int getXP() {
    return xp;
  }

  public int getPb() {
    return pb;
  }

  public int getIB() {
    return ib;
  }

  public int getStr() {
    return str;
  }

  public int getStrMod() {
    return Math.floorDiv(str - 10, 2);
  }

  public int getDex() {
    return dex;
  }

  public int getDexMod() {
    return Math.floorDiv(dex - 10, 2);
  }

  public int getCon() {
    return con;
  }

  public int getConMod() {
    return Math.floorDiv(con - 10, 2);
  }

  public int getItl() {
    return itl;
  }

  public int getItlMod() {
    return Math.floorDiv(itl - 10, 2);
  }

  public int getWis() {
    return wis;
  }

  public int getWisMod() {
    return Math.floorDiv(wis - 10, 2);
  }

  public int getCha() {
    return cha;
  }

  public int getChaMod() {
    return Math.floorDiv(cha - 10, 2);
  }

  public ArrayList<Weapon> getWeapon() {
    return this.weapon;
  }

  // CHARACTER CREATION
  public void rollNewChar(Scanner scnr) {
    System.out.println("Creating a new character...");

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      System.err.println("Interruption: " + e.getMessage());
    }

    Dice roll = new Dice();

    System.out.println("\nPlease enter character name and press enter: ");
    name = scnr.nextLine();

    level = 1;

    System.out.println("\nRolling for Strength!");
    str = roll.rollCharacterStat();
    System.out.println("\nRolling for Dexterity!");
    dex = roll.rollCharacterStat();
    System.out.println("\nRolling for Constitution!");
    con = roll.rollCharacterStat();
    System.out.println("\nRolling for Intelligence!");
    itl = roll.rollCharacterStat();
    System.out.println("\nRolling for Wisdom!");
    wis = roll.rollCharacterStat();
    System.out.println("\nRolling for Charisma!");
    cha = roll.rollCharacterStat();

    determinePB();
    determineIB();
  }

  private void determinePB() {
    if (getLevel() <= 4) {
      setPb(2);
    } else if (getLevel() <= 8) {
      setPb(3);
    } else if (getLevel() <= 12) {
      setPb(4);
    } else if (getLevel() <= 16) {
      setPb(5);
    } else if (getLevel() <= 20) {
      setPb(6);
    } else {
      setPb(0);
      System.out.println("ERROR: Level over 20. PB set to 0...");
    }
  }

  private void determineIB() {
    ib = getDexMod();
  }

  // COMBAT
  public void rollInitiative() {
    Dice i = new Dice(1, 20);
    int initiative = i.roll() + getDexMod();
    System.out.println("Intiative roll + Initiative Bonus: " + initiative);
    sleep(1500);
  }

  public void rollToHit() {
    System.out.println("Attacking with...");
    System.out.println(weapon.get(0).toString());

    Dice attRoll = new Dice(1, 20);
    int bonus = getStrMod();

    if (checkFinesseProperty()) {
      bonus = Math.max(bonus, getDexMod());
    }

    int total = attRoll.roll() + bonus + getPb();
    System.out.println("Roll to hit (Roll + ability mod + prof. bonus): " + total);
    sleep(1500);
  }

  public void attack() {
    int baseDmg = 0;
    int bonusDmg = 0;
    int totalDmg = 0;

    if (checkFinesseProperty()) {
      bonusDmg += Math.max(getStrMod(), getDexMod());
    } else {
      bonusDmg += getStrMod();
    }

    Dice attack = weapon.get(0).getDamageDice();
    baseDmg = attack.roll();
    totalDmg = baseDmg + bonusDmg;

    System.out.println("Total Damage: " + baseDmg + " + " + bonusDmg + " = " + totalDmg);
    sleep(1500);
  }

  // UTILITY
  public void equipWeapon(int n) {
    Weapon w = weapon.remove(n);
    weapon.add(0, w);
    System.out.println(weapon.get(0).toString() + " successfully equiped.");
  }

  private boolean checkFinesseProperty() {
    return weapon.get(0).hasProperty("finesse");
  }

  // SLEEP
  private void sleep(int n) {
    try {
      Thread.sleep(n);
    } catch (InterruptedException e) {
      System.err.println("Error -> " + e.getMessage());
    }
  }

  // TO-STRING
  @Override
  public String toString() {
    String string = "\n\nName: " + name;
    string += "\nLevel: " + level + " |PB: " + pb + " |Init. Bonus " + ib;
    string += "\nExperience Points: " + xp;
    string += "\n|Str\t|Dex\t|Con\t|Int\t|Wis\t|Cha";
    string += "\n|" + str + "\t|" + dex + "\t|" + con + "\t|" + itl + "\t|" + wis + "\t|" + cha;
    string += "\n|" + getStrMod() + "\t|" + getDexMod() + "\t|" + getConMod() + "\t|" + getItlMod() + "\t|"
        + getWisMod() + "\t|" + getChaMod();
    string += "\nWeapons:";
    for (Weapon w : weapon) {
      if (w == weapon.get(0)) {
        string += "\n-> " + w;
      } else {
        string += "\n" + w;
      }
    }
    return string;
  }
}
