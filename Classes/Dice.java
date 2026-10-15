package Classes;

import java.util.Random;

public class Dice {
  Random rand = new Random();

  private int sidesOfDice;
  private int numOfDice;

  // CONSTRUCTORS
  public Dice() {
    numOfDice = 0;
    sidesOfDice = 0;
  }

  public Dice(int n, int s) {
    numOfDice = n;
    sidesOfDice = s;
  }

  // SETTERS
  public void setSides(int s) {
    sidesOfDice = s;
  }

  public void setNumOfDice(int n) {
    numOfDice = n;
  }

  // GETTERS
  public int getSides() {
    return sidesOfDice;
  }

  public int getNumOfDice() {
    return numOfDice;
  }

  // UTILITY
  public int roll() {
    int total = 0;

    System.out.printf("Rolling %dd%d...%n", numOfDice, sidesOfDice);

    sleep(1500);

    for (int i = 0; i < numOfDice; i++) {
      int currRoll = rand.nextInt(sidesOfDice) + 1;
      total += currRoll;
      System.out.printf("Roll #%d is %d.%n", i + 1, currRoll);
      sleep(1500);
    }

    System.out.println("Total is: " + total);
    sleep(1500);
    return total;
  }

  public int rollWithAdv() {
    int a = rand.nextInt(sidesOfDice) + 1;
    int b = rand.nextInt(sidesOfDice) + 1;

    System.out.printf("Roll 1: %d.%nRoll 2: %d.%n", a, b);

    return Math.max(a, b);
  }

  public int rollWithDisAdv() {
    int a = rand.nextInt(sidesOfDice) + 1;
    int b = rand.nextInt(sidesOfDice) + 1;

    System.out.printf("Roll 1: %d.%nRoll 2: %d.%n", a, b);

    return Math.min(a, b);
  }

  public int rollCharacterStat() {
    int a = rand.nextInt(6) + 1;
    int b = rand.nextInt(6) + 1;
    int c = rand.nextInt(6) + 1;
    int d = rand.nextInt(6) + 1;

    int tot = a + b + c + d;

    System.out.printf("\nRoll 1: %d.%nRoll 2: %d.%nRoll 3: %d.%nRoll 4: %d.%n", a, b, c, d);

    // sleep(3000);

    int min = Math.min(Math.min(a, b), Math.min(c, d));
    tot -= min;

    System.out.printf("\nDropping %d.%nTotal is %d.%n", min, tot);

    sleep(1500);

    return tot;
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
    return numOfDice + "d" + sidesOfDice;
  }
}
