import java.util.Scanner;

import Classes.*;

public class RPG {
  private static final Scanner scnr = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Welcome to the RPG! A work in progress...\n");

    runGame(scnr);

    scnr.close();
  }

  public static void runGame(Scanner scnr) {
    int input = -1;

    Player p1 = new Player();
    p1.rollNewChar(scnr);

    Weapon shortsword = new Weapon("Shortsword", "1d6", "Piercing", "Finesse", "Light");
    Weapon greatsword = new Weapon("Greatsword", "2d6", "Slashing", "Heavy", "Two-Handed");

    p1.setWeapon(shortsword);
    p1.setWeapon(greatsword);

    System.out.println("\nCOMBAT!");
    p1.rollInitiative();

    // Simulating combat
    while (input != 0) {
      System.out.println(p1);
      System.out.println("\nChoose a number then press enter:\n");

      System.out.println("1: Attack");
      System.out.println("2: Equip different weapon");
      System.out.println("3: View Inventory");
      System.out.println("0: Exit");

      input = scnr.nextInt();

      switch (input) {
        case 1:
          p1.rollToHit();
          p1.attack();
          break;
        case 2:
          System.out.println("\nPlease type the number of weapon you want to equip\nthen press 'enter':");
          int i = 0;
          for (Weapon w : p1.getWeapon()) {
            System.out.println(i + ": " + w);
            i++;
          }

          try {
            p1.equipWeapon(scnr.nextInt());
          } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
          }
          break;
        case 3:
          System.out.println("Inventory: ");

          i = 0;
          for (Weapon w : p1.getWeapon()) {
            System.out.println(i + ": " + w);
            i++;
          }
          break;

        case 0:
          System.out.println("EXITING...");
          break;

        default:
          System.out.println("NOT VALID INPUT...");
          break;
      }
    }
  }
}
