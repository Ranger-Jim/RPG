package Classes;

import java.util.*;

public class Weapon {
  private String name;
  private Dice damageDice;
  private String damageType;
  private ArrayList<String> properties;

  // CONSTRUCTORS
  public Weapon() {
    name = "";
    damageDice = new Dice();
    damageType = "";
    properties = new ArrayList<>();
  }

  public Weapon(String name, String dmgDieStr, String dmgType, String... properties) {
    this.name = name;

    String[] dString = dmgDieStr.split("d");
    damageDice = new Dice(Integer.parseInt(dString[0]), Integer.parseInt(dString[1]));

    damageType = dmgType;

    this.properties = new ArrayList<>();
    for (String p : properties) {
      this.properties.add(p.trim().toLowerCase());
    }
  }

  // SETTERS
  public void setName(String name) {
    this.name = name;
  }

  public void setDamageDice(String dmgDieStr) {
    String[] p = dmgDieStr.split("d");
    damageDice = new Dice(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
  }

  public void setDamageType(String dmgType) {
    damageType = dmgType;
  }

  public void setProperties(String property) {
    if (!properties.contains(property)) {
      properties.add(property.trim().toLowerCase());
    }
  }

  // GETTERS
  public String getName() {
    return name;
  }

  public Dice getDamageDice() {
    return damageDice;
  }

  public String getDamageType() {
    return damageType;
  }

  public ArrayList<String> getProperties() {
    return properties;
  }

  // UTILITY
  public boolean hasProperty(String property) {
    return properties.contains(property.trim().toLowerCase());
  }

  @Override
  public String toString() {
    return name + ": " + damageDice + " " + damageType;
  }
}
