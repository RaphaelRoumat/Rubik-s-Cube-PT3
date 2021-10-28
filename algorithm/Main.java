package algorithm;

import java.util.Arrays;

// import java.util.ArrayList;
// import java.util.Scanner;

public class Main {
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public int[][] solve(int[][] tab) throws Exception {
    Alg algorithm = new Alg();
    tab = algorithm.whiteCross(tab);
    tab = algorithm.firstCrown(tab);
    if (!algorithm.isFirstCrownDone(tab))
      throw new Exception();
    return tab;
  }

  public int[][] mainScramble(int[][] tab) {
    Alg algorithm = new Alg();
    tab = algorithm.scramble(tab, 20);
    return tab;
  }

  public static void main(String[] args) throws Exception {
    int[][] tab = { { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 2, 2, 2, 2, 2, 2, 2, 2, 2 }, { 3, 3, 3, 3, 3, 3, 3, 3, 3 },
        { 4, 4, 4, 4, 4, 4, 4, 4, 4 }, { 5, 5, 5, 5, 5, 5, 5, 5, 5 }, { 6, 6, 6, 6, 6, 6, 6, 6, 6 } };
    int[][] tabCache = tab;
    int it = 1000000;

    Main m = new Main();

    for (int i = 0; i < it; i++) {
      tabCache = m.mainScramble(tab);
      tabCache = m.solve(tabCache);
    }

    System.out.println("Done!");
  }
}
