package algorithm;

import java.util.Arrays;

// import java.util.ArrayList;
// import java.util.Scanner;

public class Main {
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void main(String[] args) throws Exception {
    int[][] tab = { { 4, 2, 6, 4, 1, 6, 6, 4, 5 }, { 5, 5, 3, 2, 2, 5, 6, 3, 2 }, { 2, 5, 3, 1, 3, 1, 2, 3, 1 },
        { 4, 6, 3, 4, 4, 5, 1, 4, 5 }, { 1, 1, 4, 6, 5, 3, 4, 2, 1 }, { 2, 3, 6, 2, 6, 1, 5, 6, 3 } };

    int[][] tab2 = { { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 2, 2, 2, 2, 2, 2, 2, 2, 2 }, { 3, 3, 3, 3, 3, 3, 3, 3, 3 },
        { 4, 4, 4, 4, 4, 4, 4, 4, 4 }, { 5, 5, 5, 5, 5, 5, 5, 5, 5 }, { 6, 6, 6, 6, 6, 6, 6, 6, 6 } };

    int[][] tabCache = tab2;

    Alg alg = new Alg();
    // System.out.println("START");
    // System.out.println(alg.toString(tabCache));

    // String[] moves = {"R", "L'", "D'", "F", "D2", "L", "R2", "D", "U", "L", "B2",
    // "F", "L'", "B2", "U2", "D2", "L", "B", "R2", "L"};
    // tabCache = alg.moveSet(moves, tabCache);

    // tabCache = alg.moveSet(new
    // String[]{"F","F","F","U","L","F","F","U","B","R","U","R","B","L","B","F","R","D","R","R"},
    // tabCache);

    // System.out.println("\nDEFAULT SORT");
    // System.out.println(alg.toString(tabCache));

    // Scanner sc = new Scanner(System.in);

    // int cnt = 0;
    // System.out.println("How many scrambles do you wish to test? ");
    // int it = sc.nextInt();
    // sc.close();

    int it = 10000;

    int cntFirstCrown = 0;

    int[] sidesToDisplay = new int[] { 0, 1, 2, 3, 4, 5 };

    for (int i = 0; i < it; i++) {
      // System.out.println("\n");
      System.out.print("Moves: ");
      tabCache = alg.scramble(tabCache, 20);
      // System.out.println(alg.toString(tabCache));

      tabCache = alg.whiteCross(tabCache);
      // System.out.println("\n");
      // System.out.print("WHITE CROSS ");
      // for (int side : sidesToDisplay) {
      // System.out.println(alg.sideToString(tabCache[side]));
      // }
      // System.out.println("------------------------------------------------");

      tabCache = alg.firstCrown(tabCache);
      System.out.println("\n");
      System.out.print("FIRST CROWN ");
      for (int side : sidesToDisplay) {
        System.out.println(alg.sideToString(tabCache[side]));
      }
      System.out.println("------------------------------------------------");

      // if (alg.isWhiteCrossDone(tabCache)) cnt++;
      if (alg.isThereAnyBottomWhiteCorners(tabCache))
        throw new Exception();
      if (alg.isFirstCrownDone(tabCache))
        cntFirstCrown++;
      System.out.println();
      tabCache = tab2;
      // clearScreen();
    }

    System.out.println((cntFirstCrown));

    // System.out.print("SCRAMBLE: ");
    // System.out.println(alg.scrambleMoves.toString());
    // alg.scrambleMoves = alg.refactorMoves(alg.scrambleMoves);
    // System.out.print("REFACTORED SCRAMBLE (" + alg.scrambleMoves.size() + "
    // moves): ");
    // System.out.println(alg.scrambleMoves.toString());

    // // System.out.print("WHITE CROSS: ");
    // // System.out.println(alg.wcMoves.toString());
    // alg.wcMoves = alg.refactorMoves(alg.wcMoves);
    // System.out.print("REFACTORED WHITE CROSS (" + alg.wcMoves.size() + " moves):
    // ");
    // System.out.println(alg.wcMoves.toString());

    // alg.allMoves = alg.refactorMoves(alg.allMoves);

    // String widgetLink = alg.widgetLink(alg.allMoves);
    // System.out.println(widgetLink);

    // System.out.println(it + " scrambles");
    // System.out.println(((double) cnt/it)*100 + "% white cross solved");
    // System.out.println("Step 1 used in " + ((double)alg.cntStep1/it)*100 + "%
    // cases");
    // System.out.println("Step 2 used in " + ((double)alg.cntStep2/it)*100 + "%
    // cases");
    // System.out.println("Step 3 used in " + ((double)alg.cntStep3/it)*100 + "%
    // cases");
    // System.out.println("Step 4 used in " + ((double)alg.cntStep4/it)*100 + "%
    // cases");
    // System.out.println("Step 5 used in " + ((double)alg.cntStep5/it)*100 + "%
    // cases");
  }
}
