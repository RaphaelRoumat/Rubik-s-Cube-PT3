package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SuppressWarnings("unchecked")

public class Alg {
  public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
  public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
  public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
  public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
  public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
  public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
  public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
  public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
  public static final String ANSI_RESET = "\u001B[0m";

  private List<String> customSets = Arrays.asList(new String[] { "U2", "D2", "R2", "L2", "F2", "B2", "U", "U'", "D",
      "D'", "R", "R'", "L", "L'", "F", "F'", "B", "B'" });

  public ArrayList<String> cacheMoves = new ArrayList<>();
  public ArrayList<String> scrambleMoves = new ArrayList<>();
  public ArrayList<String> wcMoves = new ArrayList<>();
  public ArrayList<String> allMoves = new ArrayList<>();

  /**
   * Method used to apply a rotation to the cube
   *
   * @param tab  a bidimensional integer array used to represent a rubiks cube
   *             (cube pattern)
   * @param move a string representing what move to apply to the array
   * @return a bidimenstional integer array that has been modified through a
   *         defined move/rotation
   */

  public int[][] rotate(String move, int[][] tab) {
    int[][] cache = Arrays.stream(tab).map(int[]::clone).toArray(int[][]::new);

    if (move == "up") {
      System.out.print("U");
      cacheMoves.add("U");

      cache[0][0] = tab[3][0];
      cache[0][1] = tab[3][1];
      cache[0][2] = tab[3][2];

      cache[1][0] = tab[0][0];
      cache[1][1] = tab[0][1];
      cache[1][2] = tab[0][2];

      cache[2][0] = tab[1][0];
      cache[2][1] = tab[1][1];
      cache[2][2] = tab[1][2];

      cache[3][0] = tab[2][0];
      cache[3][1] = tab[2][1];
      cache[3][2] = tab[2][2];

      cache[5][0] = tab[5][6];
      cache[5][1] = tab[5][3];
      cache[5][2] = tab[5][0];
      cache[5][3] = tab[5][7];
      cache[5][5] = tab[5][1];
      cache[5][6] = tab[5][8];
      cache[5][7] = tab[5][5];
      cache[5][8] = tab[5][2];
    } else if (move == "upR") {
      System.out.print("U'");
      cacheMoves.add("U'");

      cache[0][0] = tab[1][0];
      cache[0][1] = tab[1][1];
      cache[0][2] = tab[1][2];

      cache[1][0] = tab[2][0];
      cache[1][1] = tab[2][1];
      cache[1][2] = tab[2][2];

      cache[2][0] = tab[3][0];
      cache[2][1] = tab[3][1];
      cache[2][2] = tab[3][2];

      cache[3][0] = tab[0][0];
      cache[3][1] = tab[0][1];
      cache[3][2] = tab[0][2];

      cache[5][0] = tab[5][2];
      cache[5][1] = tab[5][5];
      cache[5][2] = tab[5][8];
      cache[5][3] = tab[5][1];
      cache[5][5] = tab[5][7];
      cache[5][6] = tab[5][0];
      cache[5][7] = tab[5][3];
      cache[5][8] = tab[5][6];

    } else if (move == "down") {
      System.out.print("D");
      cacheMoves.add("D");

      cache[0][6] = tab[1][6];
      cache[0][7] = tab[1][7];
      cache[0][8] = tab[1][8];

      cache[1][6] = tab[2][6];
      cache[1][7] = tab[2][7];
      cache[1][8] = tab[2][8];

      cache[2][6] = tab[3][6];
      cache[2][7] = tab[3][7];
      cache[2][8] = tab[3][8];

      cache[3][6] = tab[0][6];
      cache[3][7] = tab[0][7];
      cache[3][8] = tab[0][8];

      cache[4][0] = tab[4][6];
      cache[4][1] = tab[4][3];
      cache[4][2] = tab[4][0];
      cache[4][3] = tab[4][7];
      cache[4][5] = tab[4][1];
      cache[4][6] = tab[4][8];
      cache[4][7] = tab[4][5];
      cache[4][8] = tab[4][2];
    } else if (move == "downR") {
      System.out.print("D'");
      cacheMoves.add("D'");

      cache[0][6] = tab[3][6];
      cache[0][7] = tab[3][7];
      cache[0][8] = tab[3][8];

      cache[1][6] = tab[0][6];
      cache[1][7] = tab[0][7];
      cache[1][8] = tab[0][8];

      cache[2][6] = tab[1][6];
      cache[2][7] = tab[1][7];
      cache[2][8] = tab[1][8];

      cache[3][6] = tab[2][6];
      cache[3][7] = tab[2][7];
      cache[3][8] = tab[2][8];

      cache[4][0] = tab[4][2];
      cache[4][1] = tab[4][5];
      cache[4][2] = tab[4][8];
      cache[4][3] = tab[4][1];
      cache[4][5] = tab[4][7];
      cache[4][6] = tab[4][0];
      cache[4][7] = tab[4][3];
      cache[4][8] = tab[4][6];
    } else if (move == "right") {
      System.out.print("R");
      cacheMoves.add("R");

      cache[0][2] = tab[4][2];
      cache[0][5] = tab[4][5];
      cache[0][8] = tab[4][8];

      cache[2][0] = tab[5][8];
      cache[2][3] = tab[5][5];
      cache[2][6] = tab[5][2];

      cache[3][0] = tab[3][6];
      cache[3][1] = tab[3][3];
      cache[3][2] = tab[3][0];
      cache[3][3] = tab[3][7];
      cache[3][5] = tab[3][1];
      cache[3][6] = tab[3][8];
      cache[3][7] = tab[3][5];
      cache[3][8] = tab[3][2];

      cache[4][2] = tab[2][6];
      cache[4][5] = tab[2][3];
      cache[4][8] = tab[2][0];

      cache[5][2] = tab[0][2];
      cache[5][5] = tab[0][5];
      cache[5][8] = tab[0][8];
    } else if (move == "rightR") {
      System.out.print("R'");
      cacheMoves.add("R'");

      cache[0][2] = tab[5][2];
      cache[0][5] = tab[5][5];
      cache[0][8] = tab[5][8];

      cache[2][0] = tab[4][8];
      cache[2][3] = tab[4][5];
      cache[2][6] = tab[4][2];

      cache[3][0] = tab[3][2];
      cache[3][1] = tab[3][5];
      cache[3][2] = tab[3][8];
      cache[3][3] = tab[3][1];
      cache[3][5] = tab[3][7];
      cache[3][6] = tab[3][0];
      cache[3][7] = tab[3][3];
      cache[3][8] = tab[3][6];

      cache[4][2] = tab[0][2];
      cache[4][5] = tab[0][5];
      cache[4][8] = tab[0][8];

      cache[5][2] = tab[2][6];
      cache[5][5] = tab[2][3];
      cache[5][8] = tab[2][0];
    } else if (move == "left") {
      System.out.print("L");
      cacheMoves.add("L");

      cache[0][0] = tab[5][0];
      cache[0][3] = tab[5][3];
      cache[0][6] = tab[5][6];

      cache[1][0] = tab[1][6];
      cache[1][1] = tab[1][3];
      cache[1][2] = tab[1][0];
      cache[1][3] = tab[1][7];
      cache[1][5] = tab[1][1];
      cache[1][6] = tab[1][8];
      cache[1][7] = tab[1][5];
      cache[1][8] = tab[1][2];

      cache[2][2] = tab[4][6];
      cache[2][5] = tab[4][3];
      cache[2][8] = tab[4][0];

      cache[4][0] = tab[0][0];
      cache[4][3] = tab[0][3];
      cache[4][6] = tab[0][6];

      cache[5][0] = tab[2][8];
      cache[5][3] = tab[2][5];
      cache[5][6] = tab[2][2];
    } else if (move == "leftR") {
      System.out.print("L'");
      cacheMoves.add("L'");

      cache[0][0] = tab[4][0];
      cache[0][3] = tab[4][3];
      cache[0][6] = tab[4][6];

      cache[1][0] = tab[1][2];
      cache[1][1] = tab[1][5];
      cache[1][2] = tab[1][8];
      cache[1][3] = tab[1][1];
      cache[1][5] = tab[1][7];
      cache[1][6] = tab[1][0];
      cache[1][7] = tab[1][3];
      cache[1][8] = tab[1][6];

      cache[2][2] = tab[5][6];
      cache[2][5] = tab[5][3];
      cache[2][8] = tab[5][0];

      cache[4][0] = tab[2][8];
      cache[4][3] = tab[2][5];
      cache[4][6] = tab[2][2];

      cache[5][0] = tab[0][0];
      cache[5][3] = tab[0][3];
      cache[5][6] = tab[0][6];
    } else if (move == "front") {
      System.out.print("F");
      cacheMoves.add("F");

      cache[0][0] = tab[0][6];
      cache[0][1] = tab[0][3];
      cache[0][2] = tab[0][0];
      cache[0][3] = tab[0][7];
      cache[0][5] = tab[0][1];
      cache[0][6] = tab[0][8];
      cache[0][7] = tab[0][5];
      cache[0][8] = tab[0][2];

      cache[1][2] = tab[4][0];
      cache[1][5] = tab[4][1];
      cache[1][8] = tab[4][2];

      cache[3][0] = tab[5][6];
      cache[3][3] = tab[5][7];
      cache[3][6] = tab[5][8];

      cache[4][0] = tab[3][6];
      cache[4][1] = tab[3][3];
      cache[4][2] = tab[3][0];

      cache[5][6] = tab[1][8];
      cache[5][7] = tab[1][5];
      cache[5][8] = tab[1][2];
    } else if (move == "frontR") {
      System.out.print("F'");
      cacheMoves.add("F'");

      cache[0][0] = tab[0][2];
      cache[0][1] = tab[0][5];
      cache[0][2] = tab[0][8];
      cache[0][3] = tab[0][1];
      cache[0][5] = tab[0][7];
      cache[0][6] = tab[0][0];
      cache[0][7] = tab[0][3];
      cache[0][8] = tab[0][6];

      cache[1][2] = tab[5][8];
      cache[1][5] = tab[5][7];
      cache[1][8] = tab[5][6];

      cache[3][0] = tab[4][2];
      cache[3][3] = tab[4][1];
      cache[3][6] = tab[4][0];

      cache[4][0] = tab[1][2];
      cache[4][1] = tab[1][5];
      cache[4][2] = tab[1][8];

      cache[5][6] = tab[3][0];
      cache[5][7] = tab[3][3];
      cache[5][8] = tab[3][6];
    } else if (move == "back") {
      System.out.print("B");
      cacheMoves.add("B");

      cache[1][0] = tab[5][2];
      cache[1][3] = tab[5][1];
      cache[1][6] = tab[5][0];

      cache[2][0] = tab[2][6];
      cache[2][1] = tab[2][3];
      cache[2][2] = tab[2][0];
      cache[2][3] = tab[2][7];
      cache[2][5] = tab[2][1];
      cache[2][6] = tab[2][8];
      cache[2][7] = tab[2][5];
      cache[2][8] = tab[2][2];

      cache[3][2] = tab[4][8];
      cache[3][5] = tab[4][7];
      cache[3][8] = tab[4][6];

      cache[4][6] = tab[1][0];
      cache[4][7] = tab[1][3];
      cache[4][8] = tab[1][6];

      cache[5][0] = tab[3][2];
      cache[5][1] = tab[3][5];
      cache[5][2] = tab[3][8];
    } else if (move == "backR") {
      System.out.print("B'");
      cacheMoves.add("B'");

      cache[1][0] = tab[4][6];
      cache[1][3] = tab[4][7];
      cache[1][6] = tab[4][8];

      cache[2][0] = tab[2][2];
      cache[2][1] = tab[2][5];
      cache[2][2] = tab[2][8];
      cache[2][3] = tab[2][1];
      cache[2][5] = tab[2][7];
      cache[2][6] = tab[2][0];
      cache[2][7] = tab[2][3];
      cache[2][8] = tab[2][6];

      cache[3][2] = tab[5][0];
      cache[3][5] = tab[5][1];
      cache[3][8] = tab[5][2];

      cache[4][6] = tab[3][8];
      cache[4][7] = tab[3][5];
      cache[4][8] = tab[3][2];

      cache[5][0] = tab[1][6];
      cache[5][1] = tab[1][3];
      cache[5][2] = tab[1][0];
    }

    return cache;
  }

  /**
   * Method used to apply a set of moves to the cube
   *
   * @param moves a set of moves to apply, stored using a string array
   * @param tab   the cube pattern that will be modified
   * @return a cube pattern that's been modified through multiple moves
   */

  public int[][] moveSet(String[] moves, int[][] tab) {
    int[][] tabCache = tab;
    for (String move : moves) {
      if (customSets.contains(move)) {
        tabCache = customSet(move, tabCache);
      } else {
        tabCache = rotate(move, tabCache);
      }
    }
    return tabCache;
  }

  /**
   * Method used to translate a custom name to a moveset and apply it
   *
   * @param name a string representing a custom name to translate
   * @param tab  a cube pattern that will be modified through a moveset
   * @return a cube pattern that's been modified through a moveset
   */

  public int[][] customSet(String name, int[][] tab) {
    int[][] tabCache = tab;
    if (name == "U2") {
      String[] set = { "up", "up" };
      tabCache = moveSet(set, tabCache);
    }
    if (name == "D2") {
      String[] set = { "down", "down" };
      tabCache = moveSet(set, tabCache);
    }
    if (name == "R2") {
      String[] set = { "right", "right" };
      tabCache = moveSet(set, tabCache);
    }
    if (name == "L2") {
      String[] set = { "left", "left" };
      tabCache = moveSet(set, tabCache);
    }
    if (name == "F2") {
      String[] set = { "front", "front" };
      tabCache = moveSet(set, tabCache);
    }
    if (name == "B2") {
      String[] set = { "back", "back" };
      tabCache = moveSet(set, tabCache);
    }
    if (name == "U") {
      tabCache = rotate("up", tabCache);
    }
    if (name == "U'") {
      tabCache = rotate("upR", tabCache);
    }
    if (name == "D") {
      tabCache = rotate("down", tabCache);
    }
    if (name == "D'") {
      tabCache = rotate("downR", tabCache);
    }
    if (name == "R") {
      tabCache = rotate("right", tabCache);
    }
    if (name == "R'") {
      tabCache = rotate("rightR", tabCache);
    }
    if (name == "L") {
      tabCache = rotate("left", tabCache);
    }
    if (name == "L'") {
      tabCache = rotate("leftR", tabCache);
    }
    if (name == "F") {
      tabCache = rotate("front", tabCache);
    }
    if (name == "F'") {
      tabCache = rotate("frontR", tabCache);
    }
    if (name == "B") {
      tabCache = rotate("back", tabCache);
    }
    if (name == "B'") {
      tabCache = rotate("backR", tabCache);
    }
    if (name == "E") {
      tabCache = moveSet(new String[] { "up", "downR" }, tabCache);
    }
    if (name == "E'") {
      tabCache = moveSet(new String[] { "upR", "down" }, tabCache);
    }
    return tabCache;
  }

  /**
   * Method used to refactor a string representing a set of moves, reducing it's
   * length when it's possible
   *
   * @param moves a list of strings, each string representing a move
   * @return a list of strings that's been refactored and shortened
   */

  public ArrayList<String> refactorMoves(ArrayList<String> moves) {
    String cache = moves.toString();
    cache = cache.replace("[", "");
    cache = cache.replace("]", "");
    cache = cache.replace(", ", " ");
    cache = cache.replace("F F F F ", "");
    cache = cache.replace("F' F' F' F' ", "");
    cache = cache.replace("B B B B ", "");
    cache = cache.replace("B' B' B' B' ", "");
    cache = cache.replace("U U U U ", "");
    cache = cache.replace("U' U' U' U' ", "");
    cache = cache.replace("D D D D ", "");
    cache = cache.replace("D' D' D' D' ", "");
    cache = cache.replace("R R R R ", "");
    cache = cache.replace("R' R' R' R' ", "");
    cache = cache.replace("L L L L ", "");
    cache = cache.replace("L' L' L' L' ", "");

    cache = cache.replace("U U U ", "U' ");
    cache = cache.replace("D D D ", "D' ");
    cache = cache.replace("F F F ", "F' ");
    cache = cache.replace("B B B ", "B' ");
    cache = cache.replace("R R R ", "R' ");
    cache = cache.replace("L L L ", "L' ");

    cache = cache.replace("L L' ", "");
    cache = cache.replace("R R' ", "");
    cache = cache.replace("U U' ", "");
    cache = cache.replace("F F' ", "");
    cache = cache.replace("B B' ", "");
    cache = cache.replace("D D' ", "");

    cache = cache.replace("L' L ", "");
    cache = cache.replace("R' R ", "");
    cache = cache.replace("U' U ", "");
    cache = cache.replace("F' F ", "");
    cache = cache.replace("B' B ", "");
    cache = cache.replace("D' D ", "");
    String[] inter = new String[] {};
    inter = cache.split(" ");
    ArrayList<String> res = new ArrayList<>();
    for (int i = 0; i < inter.length; i++) {
      res.add(inter[i]);
    }
    return res;
  }

  /**
   * Method used to return a random move
   *
   * @return a string containing a single random move
   */

  public String randomMove() {
    String[] moves = { "U", "D", "R", "L", "F", "B" };
    int rnd = new Random().nextInt(moves.length);
    return moves[rnd];
  }

  /**
   * Method used to create a new scramble for the cube
   *
   * @param tab   the cube pattern that will be scrambled
   * @param depth the number of random moves to apply
   * @return the scrambled cube
   */

  public int[][] scramble(int[][] tab, int depth) {
    int[][] tabCache = tab;
    for (int i = 0; i < depth; i++) {
      tabCache = customSet(randomMove(), tabCache);
    }
    scrambleMoves = (ArrayList<String>) cacheMoves.clone();
    cacheMoves.clear();
    allMoves.addAll(scrambleMoves);

    return tabCache;
  }

  /**
   * Method used to find trivial white squares (White cross Step 1)
   *
   * @see whiteCrossStep1
   * @param tabCache the cube pattern that will be searched
   * @return a boolean representing the existence of a trivial white square
   */

  public boolean isThereAnyTrivialSquares(int[][] tabCache) {
    boolean res = false;
    for (int clr = 1; clr < 9; clr = clr + 2) {
      if (tabCache[4][clr] == 6)
        res = true;
    }
    return res;
  }

  /**
   * Method used to find non trivial white squares in the first ring (White cross
   * Step 2)
   *
   * @see whiteCrossStep2
   * @param tabCache the cube pattern that will be searched
   * @return a boolean representing the existence of a non trivial white square in
   *         the first ring
   */

  public boolean isThereAnyNonTrivialSquaresRing1(int[][] tabCache) {
    boolean res = false;
    for (int face = 0; face < 4; face++) {
      if (tabCache[face][7] == 6)
        res = true;
    }
    return res;
  }

  /**
   * Method used to find non trivial white squares in the second ring (White cross
   * Step 3)
   *
   * @see whiteCrossStep3
   * @param tabCache the cube pattern that will be searched
   * @return a boolean representing the existence of a non trivial white square in
   *         the second ring
   */

  public boolean isThereAnyNonTrivialSquaresRing2(int[][] tabCache) {
    boolean res = false;
    for (int face = 0; face < 4; face++) {
      if (tabCache[face][3] == 6 || tabCache[face][5] == 6)
        res = true;
    }
    return res;
  }

  /**
   * Method used to find non trivial white squares in the third ring (White cross
   * Step 4)
   *
   * @see whiteCrossStep4
   * @param tabCache the cube pattern that will be searched
   * @return a boolean representing the existence of a non trivial white square in
   *         the third ring
   */

  public boolean isThereAnyNonTrivialSquaresRing3(int[][] tabCache) {
    boolean res = false;
    for (int face = 0; face < 4; face++) {
      if (tabCache[face][1] == 6)
        res = true;
    }
    return res;
  }

  /**
   * Method used to find parity issues in the finished cross (White cross
   * complementary step / step 5)
   *
   * @see whiteCrossStep5
   * @param tabCache the cube pattern that will be searched
   * @return a boolean representing the existence of parity issues in second/third
   *         ring
   */

  public boolean isThereAnyParityIssue(int[][] tabCache) {
    boolean res = false;
    for (int face = 0; face < 3; face++) {
      if (tabCache[face][1] != tabCache[face][4])
        res = true;
    }
    return res;
  }

  /**
   * Method used to verify if a moveset leading to a complete white cross has been
   * correctly found
   *
   * @param tabCache the cube pattern that will be checked
   * @return a boolean representing the state of the white cross in the cube
   */

  public boolean isWhiteCrossDone(int[][] tabCache) {
    boolean res = tabCache[5][1] == 6 && tabCache[5][3] == 6 && tabCache[5][5] == 6 && tabCache[5][7] == 6;
    res = res && ((getMiddleRidge(tabCache, 5, 1) == 3) && (getMiddleRidge(tabCache, 5, 3) == 2)
        && (getMiddleRidge(tabCache, 5, 5) == 4 && (getMiddleRidge(tabCache, 5, 7)) == 1));
    return res;
  }

  /**
   * Method used to retrieve ridge colors for middle squares
   *
   * @param tabCache the cube pattern that will be checked
   * @param face     a side coordinate
   * @param square   a square coordinate
   * @return a color code (integer)
   */

  public int getMiddleRidge(int[][] tabCache, int face, int square) {
    int res = 6;
    if (face == 0) {
      if (square == 1) {
        res = tabCache[5][7];
      } else if (square == 3) {
        res = tabCache[1][5];
      } else if (square == 5) {
        res = tabCache[3][3];
      } else if (square == 7) {
        res = tabCache[4][1];
      }
    } else if (face == 1) {
      if (square == 1) {
        res = tabCache[5][3];
      } else if (square == 3) {
        res = tabCache[2][5];
      } else if (square == 5) {
        res = tabCache[0][3];
      } else if (square == 7) {
        res = tabCache[4][3];
      }
    } else if (face == 2) {
      if (square == 1) {
        res = tabCache[5][1];
      } else if (square == 3) {
        res = tabCache[3][5];
      } else if (square == 5) {
        res = tabCache[1][3];
      } else if (square == 7) {
        res = tabCache[4][7];
      }
    } else if (face == 3) {
      if (square == 1) {
        res = tabCache[5][5];
      } else if (square == 3) {
        res = tabCache[0][5];
      } else if (square == 5) {
        res = tabCache[2][3];
      } else if (square == 7) {
        res = tabCache[4][5];
      }
    } else if (face == 4) {
      if (square == 1) {
        res = tabCache[0][7];
      } else if (square == 3) {
        res = tabCache[1][7];
      } else if (square == 5) {
        res = tabCache[3][7];
      } else if (square == 7) {
        res = tabCache[2][7];
      }
    } else if (face == 5) {
      if (square == 1) {
        res = tabCache[2][1];
      } else if (square == 3) {
        res = tabCache[1][1];
      } else if (square == 5) {
        res = tabCache[3][1];
      } else if (square == 7) {
        res = tabCache[0][1];
      }
    }
    return res;
  }

  /**
   * Method used to retrieve ridge colors for corner squares
   *
   * @param tabCache the cube pattern that will be checked
   * @param face     a side coordinate
   * @param square   a square coordinate
   * @return two color codes (integer array)
   */

  public int[] getCornerRidge(int[][] tabCache, int face, int square) { // TO DO
    int[] res = new int[] { face, square };
    if (face == 0) {
      if (square == 0) {
        res[0] = tabCache[5][6];
        res[1] = tabCache[1][2];
      } else if (square == 2) {
        res[0] = tabCache[5][8];
        res[1] = tabCache[3][0];
      } else if (square == 6) {
        res[0] = tabCache[4][0];
        res[1] = tabCache[1][8];
      } else if (square == 8) {
        res[0] = tabCache[4][2];
        res[1] = tabCache[3][6];
      }
    } else if (face == 1) {
      if (square == 0) {
        res[0] = tabCache[5][0];
        res[1] = tabCache[2][2];
      } else if (square == 2) {
        res[0] = tabCache[5][6];
        res[1] = tabCache[0][0];
      } else if (square == 6) {
        res[0] = tabCache[4][6];
        res[1] = tabCache[2][8];
      } else if (square == 8) {
        res[0] = tabCache[4][0];
        res[1] = tabCache[0][6];
      }
    } else if (face == 2) {
      if (square == 0) {
        res[0] = tabCache[5][2];
        res[1] = tabCache[3][2];
      } else if (square == 2) {
        res[0] = tabCache[5][0];
        res[1] = tabCache[1][0];
      } else if (square == 6) {
        res[0] = tabCache[4][8];
        res[1] = tabCache[3][8];
      } else if (square == 8) {
        res[0] = tabCache[4][6];
        res[1] = tabCache[1][6];
      }
    } else if (face == 3) {
      if (square == 0) {
        res[0] = tabCache[5][8];
        res[1] = tabCache[0][2];
      } else if (square == 2) {
        res[0] = tabCache[5][2];
        res[1] = tabCache[2][0];
      } else if (square == 6) {
        res[0] = tabCache[4][2];
        res[1] = tabCache[0][8];
      } else if (square == 8) {
        res[0] = tabCache[4][8];
        res[1] = tabCache[2][6];
      }
    } else if (face == 4) {
      if (square == 0) {
        res[0] = tabCache[0][6];
        res[1] = tabCache[1][8];
      } else if (square == 2) {
        res[0] = tabCache[0][8];
        res[1] = tabCache[3][6];
      } else if (square == 6) {
        res[0] = tabCache[2][8];
        res[1] = tabCache[1][6];
      } else if (square == 8) {
        res[0] = tabCache[2][6];
        res[1] = tabCache[3][8];
      }
    } else if (face == 5) {
      if (square == 0) {
        res[0] = tabCache[2][2];
        res[1] = tabCache[1][0];
      } else if (square == 2) {
        res[0] = tabCache[2][0];
        res[1] = tabCache[3][2];
      } else if (square == 6) {
        res[0] = tabCache[0][0];
        res[1] = tabCache[1][2];
      } else if (square == 8) {
        res[0] = tabCache[0][2];
        res[1] = tabCache[3][0];
      }
    }
    return res;
  }

  /**
   * First step of solving the white cross, we start by checking trivial white
   * squares in the first ring (squares that are already correclty oriented) and
   * inserting them on the white side
   *
   * @param tabCache the cube pattern that will be modified
   * @return the modified cube pattern
   */

  public int[][] whiteCrossStep1(int[][] tabCache) {
    if (isThereAnyTrivialSquares(tabCache)) {
      boolean stepDone = false; // boolean used to control step completion
      int foundColor = 7; // color search variable represented through an integer

      // I) step 1: trivial white borders (note: used by ~73% of scrambles, solves ~1%
      // white crosses)
      while (!stepDone) {
        for (int clr = 1; clr < 9; clr = clr + 2) {
          // Checks yellow side colors on odd locations of the yellow side(1,3,5,7)
          if (tabCache[4][clr] == 6) {
            // found a white color > searches for its location and the attached color
            if (clr == 1) {
              // found white on square 1
              foundColor = getMiddleRidge(tabCache, 4, 1);
              /*
               * checks the color attached to the white square this step allows for more
               * optimized moves / 2nd ring parity: First: aligns both correct colors Second:
               * inserts the white square correctly thanks to the first step
               */
              if (foundColor == 1) {
                // if green
                tabCache = customSet("F2", tabCache);
                // do F2
              } else if (foundColor == 2) {
                // if orange
                tabCache = moveSet(new String[] { "D'", "L2" }, tabCache);
                // do D' L2
              } else if (foundColor == 3) {
                // if blue
                tabCache = moveSet(new String[] { "D2", "B2" }, tabCache);
                // do D2 B2
              } else if (foundColor == 4) {
                // if red
                tabCache = moveSet(new String[] { "D", "R2" }, tabCache);
                // do D R2
              }
            } else if (clr == 3) {
              foundColor = getMiddleRidge(tabCache, 4, 3);
              if (foundColor == 1) {
                tabCache = moveSet(new String[] { "D", "F2" }, tabCache);
              } else if (foundColor == 2) {
                tabCache = customSet("L2", tabCache);
              } else if (foundColor == 3) {
                tabCache = moveSet(new String[] { "D'", "B2" }, tabCache);
              } else if (foundColor == 4) {
                tabCache = moveSet(new String[] { "D2", "R2" }, tabCache);
              }
            } else if (clr == 5) {
              foundColor = getMiddleRidge(tabCache, 4, 5);
              if (foundColor == 1) {
                tabCache = moveSet(new String[] { "D'", "F2" }, tabCache);
              } else if (foundColor == 2) {
                tabCache = moveSet(new String[] { "D2", "L2" }, tabCache);
              } else if (foundColor == 3) {
                tabCache = moveSet(new String[] { "D", "B2" }, tabCache);
              } else if (foundColor == 4) {
                tabCache = customSet("R2", tabCache);
              }
            } else if (clr == 7) {
              foundColor = getMiddleRidge(tabCache, 4, 7);
              if (foundColor == 1) {
                tabCache = moveSet(new String[] { "D2", "F2" }, tabCache);
              } else if (foundColor == 2) {
                tabCache = moveSet(new String[] { "D", "L2" }, tabCache);
              } else if (foundColor == 3) {
                tabCache = customSet("B2", tabCache);
              } else if (foundColor == 4) {
                tabCache = moveSet(new String[] { "D'", "R2" }, tabCache);
              }
            } else
              stepDone = true;
          }
          stepDone = true;
        }
      }
      stepDone = false;
    }
    return tabCache;
  }

  /**
   * Second step of solving the white cross, checking white squares in the first
   * ring (on sides green, orange, blue and red) and inserting them on the white
   * side
   *
   * @param tabCache the cube pattern that will be modified
   * @return the modified cube pattern
   */

  public int[][] whiteCrossStep2(int[][] tabCache) {
    if (isThereAnyNonTrivialSquaresRing1(tabCache)) {
      boolean stepDone = false; // boolean used to control step completion
      int foundColor = 7;

      // II) step 2: non trivial white borders 1st ring (note: used by ~67% of
      // scrambles, solves ~9% white crosses combined with previous step)
      while (!stepDone) {
        for (int face = 0; face < 4; face++) {
          if (tabCache[0][7] == 6) {
            foundColor = getMiddleRidge(tabCache, 0, 7);
            if (foundColor == 1) {
              tabCache = moveSet(new String[] { "D", "R", "F'", "R'" }, tabCache);
            } else if (foundColor == 2) {
              tabCache = moveSet(new String[] { "F", "L'", "F'" }, tabCache);
            } else if (foundColor == 3) {
              tabCache = moveSet(new String[] { "D'", "L", "B'", "L'" }, tabCache);
            } else if (foundColor == 4) {
              tabCache = moveSet(new String[] { "F'", "R", "F" }, tabCache);
            }

          } else {
            tabCache = customSet("D", tabCache);
          }
        }
        stepDone = true;
      }
    }
    return tabCache;
  }

  /**
   * Third step of solving the white cross, checking white squares in the middle
   * ring (on sides green, orange, blue and red) and inserting them on the white
   * side
   *
   * @param tabCache the cube pattern that will be modified
   * @return the modified cube pattern
   */

  public int[][] whiteCrossStep3(int[][] tabCache) {
    int foundColor = 7;

    // III) step 3: non trivial white borders 2nd ring (note: used by ~90% of
    // scrambles, solves ~75% white crosses combined with previous step)
    if (isThereAnyNonTrivialSquaresRing2(tabCache)) {
      for (int face = 0; face < 4; face++) {
        if (face == 0) {
          if (tabCache[face][3] == 6) {
            foundColor = getMiddleRidge(tabCache, face, 3);
            if (foundColor == 1) {
              tabCache = moveSet(new String[] { "L", "D", "L'", "F2" }, tabCache);
            } else if (foundColor == 2) {
              tabCache = customSet("L'", tabCache);
            } else if (foundColor == 3) {
              tabCache = moveSet(new String[] { "L", "D", "L'", "B2" }, tabCache);
            } else if (foundColor == 4) {
              tabCache = moveSet(new String[] { "L", "D", "L'", "D", "R2" }, tabCache);
            }
          } else if (tabCache[face][5] == 6) {
            foundColor = getMiddleRidge(tabCache, face, 5);
            if (foundColor == 1) {
              tabCache = moveSet(new String[] { "R'", "D'", "R", "F2" }, tabCache);
            } else if (foundColor == 2) {
              tabCache = moveSet(new String[] { "F2", "L'", "F2" }, tabCache);
            } else if (foundColor == 3) {
              tabCache = moveSet(new String[] { "R'", "D", "R", "B2" }, tabCache);
            } else if (foundColor == 4) {
              tabCache = customSet("R", tabCache);
            }
          }
        } else if (face == 1) {
          if (tabCache[face][3] == 6) {
            foundColor = getMiddleRidge(tabCache, face, 3);
            if (foundColor == 1) {
              tabCache = moveSet(new String[] { "B", "D2", "B'", "F2" }, tabCache);
            } else if (foundColor == 2) {
              tabCache = moveSet(new String[] { "B", "D", "B'", "L2" }, tabCache);
            } else if (foundColor == 3) {
              tabCache = customSet("B'", tabCache);
            } else if (foundColor == 4) {
              tabCache = moveSet(new String[] { "L'", "D2", "L", "R2" }, tabCache);
            }
          } else if (tabCache[face][5] == 6) {
            foundColor = getMiddleRidge(tabCache, face, 5);
            if (foundColor == 1) {
              tabCache = customSet("F", tabCache);
            } else if (foundColor == 2) {
              tabCache = moveSet(new String[] { "L", "D", "B'", "L'", "B" }, tabCache);
            } else if (foundColor == 3) {
              tabCache = moveSet(new String[] { "L2", "B'", "L2" }, tabCache);
            } else if (foundColor == 4) {
              tabCache = moveSet(new String[] { "L", "D", "F'", "R", "F", "L'" }, tabCache);
            }
          }
        } else if (face == 2) {
          if (tabCache[face][3] == 6) {
            foundColor = getMiddleRidge(tabCache, face, 3);
            if (foundColor == 1) {
              tabCache = moveSet(new String[] { "B'", "D", "L'", "F", "L" }, tabCache);
            } else if (foundColor == 2) {
              tabCache = moveSet(new String[] { "B2", "L", "B2" }, tabCache);
            } else if (foundColor == 3) {
              tabCache = moveSet(new String[] { "B'", "D", "L", "B'", "L'" }, tabCache);
            } else if (foundColor == 4) {
              tabCache = customSet("R'", tabCache);
            }
          } else if (tabCache[face][5] == 6) {
            foundColor = getMiddleRidge(tabCache, face, 5);
            if (foundColor == 1) {
              tabCache = moveSet(new String[] { "L'", "D", "L'", "F2" }, tabCache);
            } else if (foundColor == 2) {
              tabCache = customSet("L", tabCache);
            } else if (foundColor == 3) {
              tabCache = moveSet(new String[] { "R", "D", "R'", "B2" }, tabCache);
            } else if (foundColor == 4) {
              tabCache = moveSet(new String[] { "L'", "D2", "L", "R2" }, tabCache);
            }
          }
        } else if (face == 3) {
          if (tabCache[face][3] == 6) {
            foundColor = getMiddleRidge(tabCache, face, 3);
            if (foundColor == 1) {
              tabCache = customSet("F'", tabCache);
            } else if (foundColor == 2) {
              tabCache = moveSet(new String[] { "R'", "D'", "R", "F", "L'", "F'" }, tabCache);
            } else if (foundColor == 3) {
              tabCache = moveSet(new String[] { "R2", "B", "R2" }, tabCache);
            } else if (foundColor == 4) {
              tabCache = moveSet(new String[] { "R'", "D'", "F'", "R", "F" }, tabCache);
            }
          } else if (tabCache[face][5] == 6) {
            foundColor = getMiddleRidge(tabCache, face, 5);
            if (foundColor == 1) {
              tabCache = moveSet(new String[] { "B'", "D2", "B", "F2" }, tabCache);
            } else if (foundColor == 2) {
              tabCache = moveSet(new String[] { "B'", "D", "B", "L2" }, tabCache);
            } else if (foundColor == 3) {
              tabCache = customSet("B", tabCache);
            } else if (foundColor == 4) {
              tabCache = moveSet(new String[] { "B'", "D'", "B", "R2" }, tabCache);
            }
          }
        }
      }
    }
    return tabCache;
  }

  /**
   * Fourth step of solving the white cross, checking white squares in the third
   * ring (white side) and correclty insert them (change orientation)
   *
   * @param tabCache the cube pattern that will be modified
   * @return the modified cube pattern
   */

  public int[][] whiteCrossStep4(int[][] tabCache) {
    if (isThereAnyNonTrivialSquaresRing3(tabCache)) {
      boolean stepDone = false; // boolean used to control step completion
      int foundColor = 7;

      // IV) step 4: non trivial white borders 3rd ring (note: used by ~31.8% of
      // scrambles, solves ~98.7% white crosses combined with previous step)
      while (!stepDone) {
        for (int face = 0; face < 4; face++) {
          if (face == 0) {
            if (tabCache[face][1] == 6) {
              foundColor = getMiddleRidge(tabCache, face, 1);
              if (foundColor == 1) {
                tabCache = moveSet(new String[] { "F", "R'", "D'", "F2" }, tabCache);
              } else if (foundColor == 2) {
                tabCache = moveSet(new String[] { "F'", "L'" }, tabCache);
              } else if (foundColor == 3) {
                tabCache = moveSet(new String[] { "F", "R'", "D", "R", "B2" }, tabCache);
              } else if (foundColor == 4) {
                tabCache = moveSet(new String[] { "F", "R" }, tabCache);
              }
            }
          } else if (face == 1) {
            if (tabCache[face][1] == 6) {
              foundColor = getMiddleRidge(tabCache, face, 1);
              if (foundColor == 1) {
                tabCache = moveSet(new String[] { "L", "F" }, tabCache);
              } else if (foundColor == 2) {
                tabCache = moveSet(new String[] { "L'", "B", "D", "B'", "L2" }, tabCache);
              } else if (foundColor == 3) {
                tabCache = moveSet(new String[] { "L'", "B'" }, tabCache);
              } else if (foundColor == 4) {
                tabCache = moveSet(new String[] { "L", "F'", "D", "F", "R2" }, tabCache);
              }
            }
          } else if (face == 2) {
            if (tabCache[face][1] == 6) {
              foundColor = getMiddleRidge(tabCache, face, 1);
              if (foundColor == 1) {
                tabCache = moveSet(new String[] { "B", "L'", "D", "L", "F2" }, tabCache);
              } else if (foundColor == 2) {
                tabCache = moveSet(new String[] { "B", "L" }, tabCache);
              } else if (foundColor == 3) {
                tabCache = moveSet(new String[] { "B", "L'", "D'", "L", "B2" }, tabCache);
              } else if (foundColor == 4) {
                tabCache = moveSet(new String[] { "B'", "R'" }, tabCache);
              }
            }
          } else if (face == 3) {
            if (tabCache[face][1] == 6) {
              foundColor = getMiddleRidge(tabCache, face, 1);
              if (foundColor == 1) {
                tabCache = moveSet(new String[] { "R'", "F'" }, tabCache);
              } else if (foundColor == 2) {
                tabCache = moveSet(new String[] { "R", "B'", "D", "B", "L2" }, tabCache);
              } else if (foundColor == 3) {
                tabCache = moveSet(new String[] { "R", "B" }, tabCache);
              } else if (foundColor == 4) {
                tabCache = moveSet(new String[] { "R", "B'", "D'", "B", "R2" }, tabCache);
              }
            }
          }
        }
        stepDone = true;
      }
    }
    return tabCache;
  }

  /**
   * Fifth step of solving the white cross, last and complementary, this step
   * swaps wrongly placed white squares that were already placed well-oriented
   * after the scramble
   *
   * @param tabCache the cube pattern that will be modified
   * @return the modified cube pattern
   */

  public int[][] whiteCrossStep5(int[][] tabCache) {
    int foundColor = 7;

    // V) step 5: fixing rare parity errors (note: used by ~1.31% of scrambles,
    // solves 100% white crosses combined with previous step)
    for (int i = 0; i < 3; i++) {
      foundColor = tabCache[i][1];
      if (foundColor != tabCache[i][4]) {
        if (i == 0) {
          if (foundColor == 2) {
            tabCache = moveSet(new String[] { "F2", "D'", "L2", "D", "F2" }, tabCache);
          } else if (foundColor == 3) {
            tabCache = moveSet(new String[] { "F2", "D2", "B2", "D2", "F2" }, tabCache);
          } else if (foundColor == 4) {
            tabCache = moveSet(new String[] { "F2", "D", "R2", "D'", "F2" }, tabCache);
          }

        } else if (i == 1) {
          if (foundColor == 1) {
            tabCache = moveSet(new String[] { "L2", "D", "F2", "D'", "L2" }, tabCache);
          } else if (foundColor == 3) {
            tabCache = moveSet(new String[] { "L2", "D'", "B2", "D", "L2" }, tabCache);
          } else if (foundColor == 4) {
            tabCache = moveSet(new String[] { "L2", "D2", "R2", "D2", "L2" }, tabCache);
          }
        } else if (i == 2) {
          if (foundColor == 4) {
            tabCache = moveSet(new String[] { "B2", "D'", "R2", "D", "B2" }, tabCache);
          }
        }
      }
    }
    return tabCache;
  }

  public int cnt = 0;
  public int cntStep1 = 0;
  public int cntStep2 = 0;
  public int cntStep3 = 0;
  public int cntStep4 = 0;
  public int cntStep5 = 0;

  /**
   * Method that incorporates every white cross completion method and solves 100%
   * of white crosses
   *
   * @param tab the cube pattern that will be modified
   * @return a cube pattern with a correct white cross pattern
   */

  public int[][] whiteCross(int[][] tab) {
    int[][] tabCache = tab; // the cube pattern that will be returned is passed through a cache for more
                            // flexibility
    boolean isDone = false;
    boolean step1Used = false;
    boolean step2Used = false;
    boolean step3Used = false;
    boolean step4Used = false;
    boolean step5Used = false;

    /*
     * Methods: customSet executes moves using a library of premade moves (common
     * ones) moveSet executes multiple moves listed in an array (can either be from
     * the library or native ones from the rotate method)
     */

    /*
     * Notations: Colors: 1 = green 2 = orange 3 = blue 4 = red 5 = yellow 6 = white
     *
     * Cube pattern: 2 dimensional array of height 6 and width 9 height: sides
     * width: squares
     */

    while (!isDone) {
      if (!isThereAnyNonTrivialSquaresRing1(tabCache) && !isThereAnyNonTrivialSquaresRing2(tabCache)
          && !isThereAnyNonTrivialSquaresRing3(tabCache) && !isThereAnyTrivialSquares(tabCache))
        isDone = true;
      if (isThereAnyTrivialSquares(tabCache)) {
        step1Used = true;
        tabCache = whiteCrossStep1(tabCache);
      }
      if (isThereAnyNonTrivialSquaresRing1(tabCache)) {
        step2Used = true;
        tabCache = whiteCrossStep2(tabCache);
      }
      if (isThereAnyNonTrivialSquaresRing2(tabCache)) {
        step3Used = true;
        tabCache = whiteCrossStep3(tabCache);
      }
      if (isThereAnyNonTrivialSquaresRing3(tabCache)) {
        step4Used = true;
        tabCache = whiteCrossStep4(tabCache);
      }
    }

    while (!isWhiteCrossDone(tabCache)) {
      if (isThereAnyParityIssue(tabCache)) {
        step5Used = true;
        tabCache = whiteCrossStep5(tabCache);
      }
    }

    wcMoves = (ArrayList<String>) cacheMoves.clone();
    cacheMoves.clear();
    allMoves.addAll(wcMoves);

    if (step1Used)
      cntStep1++;
    if (step2Used)
      cntStep2++;
    if (step3Used)
      cntStep3++;
    if (step4Used)
      cntStep4++;
    if (step5Used)
      cntStep5++;
    return tabCache;
  }

  public boolean isFirstCrownDone(int[][] tabCache) {
    boolean res = getCornerRidge(tabCache, 0, 0)[0] == 6 && getCornerRidge(tabCache, 0, 0)[1] == 2
        && getCornerRidge(tabCache, 0, 2)[0] == 6 && getCornerRidge(tabCache, 0, 2)[1] == 4
        && getCornerRidge(tabCache, 2, 0)[0] == 6 && getCornerRidge(tabCache, 2, 0)[1] == 4
        && getCornerRidge(tabCache, 2, 2)[0] == 6 && getCornerRidge(tabCache, 2, 2)[1] == 2;
    return res && isWhiteCrossDone(tabCache);
  }

  public boolean isCornerContainingColor(int[][] tabCache, int face, int square, int color) {
    boolean res = false;
    int[] c = getCornerRidge(tabCache, face, square);
    for (int i : c) {
      res = res || i == color || tabCache[face][square] == color;
    }
    return res;
  }

  public boolean isThereAnyBottomWhiteCorners(int[][] tabCache) {
    boolean res = false;
    boolean c1 = isCornerContainingColor(tabCache, 0, 6, 6);
    boolean c2 = isCornerContainingColor(tabCache, 0, 8, 6);
    boolean c3 = isCornerContainingColor(tabCache, 2, 6, 6);
    boolean c4 = isCornerContainingColor(tabCache, 2, 8, 6);
    res = c1 || c2 || c3 || c4;
    return res;
  }

  public boolean isThereAnyUpperWhiteCorners(int[][] tabCache) {
    boolean res = false;
    boolean c1 = isCornerContainingColor(tabCache, 0, 0, 6);
    boolean c2 = isCornerContainingColor(tabCache, 1, 0, 6);
    boolean c3 = isCornerContainingColor(tabCache, 2, 0, 6);
    boolean c4 = isCornerContainingColor(tabCache, 3, 0, 6);
    res = c1 || c2 || c3 || c4;
    return res;
  }

  public int[][] firstCrownStep1(int[][] tabCache) { // 62% first crown solves
    if (isThereAnyBottomWhiteCorners(tabCache)) {
      for (int face = 0; face < 4; face++) {
        if (isCornerContainingColor(tabCache, face, 8, 6)) {
          switch (face) {
          case 0:
            if (isCornerContainingColor(tabCache, face, 8, 1) && isCornerContainingColor(tabCache, face, 8, 4)) {
              while (!(tabCache[5][8] == 6 && tabCache[0][2] == 1 && tabCache[3][0] == 4)) {
                tabCache = moveSet(new String[] { "R'", "D'", "R", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 8, 1) && isCornerContainingColor(tabCache, face, 8, 2)) {
              tabCache = moveSet(new String[] { "D'" }, tabCache);
              while (!(tabCache[5][6] == 6 && tabCache[1][2] == 2 && tabCache[0][0] == 1)) {
                tabCache = moveSet(new String[] { "F'", "D'", "F", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 8, 2) && isCornerContainingColor(tabCache, face, 8, 3)) {
              tabCache = moveSet(new String[] { "D2" }, tabCache);
              while (!(tabCache[5][0] == 6 && tabCache[2][2] == 3 && tabCache[1][0] == 2)) {
                tabCache = moveSet(new String[] { "L'", "D'", "L", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 8, 3) && isCornerContainingColor(tabCache, face, 8, 4)) {
              tabCache = moveSet(new String[] { "D" }, tabCache);
              while (!(tabCache[5][2] == 6 && tabCache[3][2] == 4 && tabCache[2][0] == 3)) {
                tabCache = moveSet(new String[] { "B'", "D'", "B", "D" }, tabCache);
              }
            }
            break;
          case 1:
            if (isCornerContainingColor(tabCache, face, 8, 1) && isCornerContainingColor(tabCache, face, 8, 4)) {
              tabCache = moveSet(new String[] { "D" }, tabCache);
              while (!(tabCache[5][8] == 6 && tabCache[0][2] == 1 && tabCache[3][0] == 4)) {
                tabCache = moveSet(new String[] { "R'", "D'", "R", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 8, 1) && isCornerContainingColor(tabCache, face, 8, 2)) {
              while (!(tabCache[5][6] == 6 && tabCache[1][2] == 2 && tabCache[0][0] == 1)) {
                tabCache = moveSet(new String[] { "F'", "D'", "F", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 8, 2) && isCornerContainingColor(tabCache, face, 8, 3)) {
              tabCache = moveSet(new String[] { "D'" }, tabCache);
              while (!(tabCache[5][0] == 6 && tabCache[2][2] == 3 && tabCache[1][0] == 2)) {
                tabCache = moveSet(new String[] { "L'", "D'", "L", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 8, 3) && isCornerContainingColor(tabCache, face, 8, 4)) {
              tabCache = moveSet(new String[] { "D2" }, tabCache);
              while (!(tabCache[5][2] == 6 && tabCache[3][2] == 4 && tabCache[2][0] == 3)) {
                tabCache = moveSet(new String[] { "B'", "D'", "B", "D" }, tabCache);
              }
            }
            break;
          case 2:
            if (isCornerContainingColor(tabCache, face, 8, 1) && isCornerContainingColor(tabCache, face, 8, 4)) {
              tabCache = moveSet(new String[] { "D2" }, tabCache);
              while (!(tabCache[5][8] == 6 && tabCache[0][2] == 1 && tabCache[3][0] == 4)) {
                tabCache = moveSet(new String[] { "R'", "D'", "R", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 8, 1) && isCornerContainingColor(tabCache, face, 8, 2)) {
              tabCache = moveSet(new String[] { "D" }, tabCache);
              while (!(tabCache[5][6] == 6 && tabCache[1][2] == 2 && tabCache[0][0] == 1)) {
                tabCache = moveSet(new String[] { "F'", "D'", "F", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 8, 2) && isCornerContainingColor(tabCache, face, 8, 3)) {
              while (!(tabCache[5][0] == 6 && tabCache[2][2] == 3 && tabCache[1][0] == 2)) {
                tabCache = moveSet(new String[] { "L'", "D'", "L", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 8, 3) && isCornerContainingColor(tabCache, face, 8, 4)) {
              tabCache = moveSet(new String[] { "D'" }, tabCache);
              while (!(tabCache[5][2] == 6 && tabCache[3][2] == 4 && tabCache[2][0] == 3)) {
                tabCache = moveSet(new String[] { "B'", "D'", "B", "D" }, tabCache);
              }
            }
            break;
          case 3:
            if (isCornerContainingColor(tabCache, face, 8, 1) && isCornerContainingColor(tabCache, face, 8, 4)) {
              tabCache = moveSet(new String[] { "D'" }, tabCache);
              while (!(tabCache[5][8] == 6 && tabCache[0][2] == 1 && tabCache[3][0] == 4)) {
                tabCache = moveSet(new String[] { "R'", "D'", "R", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 8, 1) && isCornerContainingColor(tabCache, face, 8, 2)) {
              tabCache = moveSet(new String[] { "D2" }, tabCache);
              while (!(tabCache[5][6] == 6 && tabCache[1][2] == 2 && tabCache[0][0] == 1)) {
                tabCache = moveSet(new String[] { "F'", "D'", "F", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 8, 2) && isCornerContainingColor(tabCache, face, 8, 3)) {
              tabCache = moveSet(new String[] { "D" }, tabCache);
              while (!(tabCache[5][0] == 6 && tabCache[2][2] == 3 && tabCache[1][0] == 2)) {
                tabCache = moveSet(new String[] { "L'", "D'", "L", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 8, 3) && isCornerContainingColor(tabCache, face, 8, 4)) {
              while (!(tabCache[5][2] == 6 && tabCache[3][2] == 4 && tabCache[2][0] == 3)) {
                tabCache = moveSet(new String[] { "B'", "D'", "B", "D" }, tabCache);
              }
            }
            break;
          default:
            break;
          }
        }
      }
    }
    return tabCache;
  }

  public int[][] firstCrownStep2(int[][] tabCache) {
    if (isThereAnyUpperWhiteCorners(tabCache)) {
      for (int face = 0; face < 4; face++) {
        if (isCornerContainingColor(tabCache, face, 2, 6)) {
          switch (face) {
          case 0:
            if (isCornerContainingColor(tabCache, face, 2, 1) && isCornerContainingColor(tabCache, face, 2, 4)) {
              while (!(tabCache[5][8] == 6 && tabCache[0][2] == 1 && tabCache[3][0] == 4)) {
                tabCache = moveSet(new String[] { "R'", "D'", "R", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 2, 1) && isCornerContainingColor(tabCache, face, 2, 2)) {
              tabCache = moveSet(new String[] { "R'", "D'", "R" }, tabCache);
              while (!(tabCache[5][6] == 6 && tabCache[1][2] == 2 && tabCache[0][0] == 1)) {
                tabCache = moveSet(new String[] { "F'", "D'", "F", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 2, 2) && isCornerContainingColor(tabCache, face, 2, 3)) {
              tabCache = moveSet(new String[] { "R'", "D'", "R", "D'" }, tabCache);
              while (!(tabCache[5][0] == 6 && tabCache[2][2] == 3 && tabCache[1][0] == 2)) {
                tabCache = moveSet(new String[] { "L'", "D'", "L", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 2, 3) && isCornerContainingColor(tabCache, face, 2, 4)) {
              tabCache = moveSet(new String[] { "R'", "D'", "R", "D2" }, tabCache);
              while (!(tabCache[5][2] == 6 && tabCache[3][2] == 4 && tabCache[2][0] == 3)) {
                tabCache = moveSet(new String[] { "B'", "D'", "B", "D" }, tabCache);
              }
            }
            break;
          case 1:
            if (isCornerContainingColor(tabCache, face, 2, 1) && isCornerContainingColor(tabCache, face, 2, 4)) {
              tabCache = moveSet(new String[] { "F'", "D'", "F", "D2" }, tabCache);
              while (!(tabCache[5][8] == 6 && tabCache[0][2] == 1 && tabCache[3][0] == 4)) {
                tabCache = moveSet(new String[] { "R'", "D'", "R", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 2, 1) && isCornerContainingColor(tabCache, face, 2, 2)) {
              while (!(tabCache[5][6] == 6 && tabCache[1][2] == 2 && tabCache[0][0] == 1)) {
                tabCache = moveSet(new String[] { "F'", "D'", "F", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 2, 2) && isCornerContainingColor(tabCache, face, 2, 3)) {
              tabCache = moveSet(new String[] { "F'", "D'", "F" }, tabCache);
              while (!(tabCache[5][0] == 6 && tabCache[2][2] == 3 && tabCache[1][0] == 2)) {
                tabCache = moveSet(new String[] { "L'", "D'", "L", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 2, 3) && isCornerContainingColor(tabCache, face, 2, 4)) {
              tabCache = moveSet(new String[] { "F'", "D'", "F", "D'" }, tabCache);
              while (!(tabCache[5][2] == 6 && tabCache[3][2] == 4 && tabCache[2][0] == 3)) {
                tabCache = moveSet(new String[] { "B'", "D'", "B", "D" }, tabCache);
              }
            }
            break;
          case 2:
            if (isCornerContainingColor(tabCache, face, 2, 1) && isCornerContainingColor(tabCache, face, 2, 4)) {
              tabCache = moveSet(new String[] { "L'", "D'", "L", "D'" }, tabCache);
              while (!(tabCache[5][8] == 6 && tabCache[0][2] == 1 && tabCache[3][0] == 4)) {
                tabCache = moveSet(new String[] { "R'", "D'", "R", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 2, 1) && isCornerContainingColor(tabCache, face, 2, 2)) {
              tabCache = moveSet(new String[] { "L'", "D'", "L", "D2" }, tabCache);
              while (!(tabCache[5][6] == 6 && tabCache[1][2] == 2 && tabCache[0][0] == 1)) {
                tabCache = moveSet(new String[] { "F'", "D'", "F", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 2, 2) && isCornerContainingColor(tabCache, face, 2, 3)) {
              while (!(tabCache[5][0] == 6 && tabCache[2][2] == 3 && tabCache[1][0] == 2)) {
                tabCache = moveSet(new String[] { "L'", "D'", "L", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 2, 3) && isCornerContainingColor(tabCache, face, 2, 4)) {
              tabCache = moveSet(new String[] { "L'", "D'", "L" }, tabCache);
              while (!(tabCache[5][2] == 6 && tabCache[3][2] == 4 && tabCache[2][0] == 3)) {
                tabCache = moveSet(new String[] { "B'", "D'", "B", "D" }, tabCache);
              }
            }
            break;
          case 3:
            if (isCornerContainingColor(tabCache, face, 2, 1) && isCornerContainingColor(tabCache, face, 2, 4)) {
              tabCache = moveSet(new String[] { "B'", "D'", "B" }, tabCache);
              while (!(tabCache[5][8] == 6 && tabCache[0][2] == 1 && tabCache[3][0] == 4)) {
                tabCache = moveSet(new String[] { "R'", "D'", "R", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 2, 1) && isCornerContainingColor(tabCache, face, 2, 2)) {
              tabCache = moveSet(new String[] { "B'", "D'", "B", "D'" }, tabCache);
              while (!(tabCache[5][6] == 6 && tabCache[1][2] == 2 && tabCache[0][0] == 1)) {
                tabCache = moveSet(new String[] { "F'", "D'", "F", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 2, 2) && isCornerContainingColor(tabCache, face, 2, 3)) {
              tabCache = moveSet(new String[] { "B'", "D'", "B", "D2" }, tabCache);
              while (!(tabCache[5][0] == 6 && tabCache[2][2] == 3 && tabCache[1][0] == 2)) {
                tabCache = moveSet(new String[] { "L'", "D'", "L", "D" }, tabCache);
              }
            } else if (isCornerContainingColor(tabCache, face, 2, 3) && isCornerContainingColor(tabCache, face, 2, 4)) {
              while (!(tabCache[5][2] == 6 && tabCache[3][2] == 4 && tabCache[2][0] == 3)) {
                tabCache = moveSet(new String[] { "B'", "D'", "B", "D" }, tabCache);
              }
            }
            break;
          default:
            break;
          }
        }
      }
    }

    return tabCache;
  }

  public int[][] firstCrown(int[][] tab) {
    int[][] tabCache = tab;

    while (!isFirstCrownDone(tabCache)) {
      tabCache = firstCrownStep1(tabCache);
      tabCache = firstCrownStep2(tabCache);
    }

    return tabCache;
  }

  /**
   * A simple colorizer that takes an integer and translates it in a colored
   * background string (used for cube pattern displaying)
   *
   * @param input the color code to colorize
   * @return a colored string
   */

  public String colorizeThat(int input) {
    String res = "";
    switch (input) {
    case 1:
      res += ANSI_GREEN_BACKGROUND;
      res += "1";
      res += ANSI_RESET;
      break;
    case 2:
      res += ANSI_PURPLE_BACKGROUND;
      res += "2";
      res += ANSI_RESET;
      break;
    case 3:
      res += ANSI_BLUE_BACKGROUND;
      res += "3";
      res += ANSI_RESET;
      break;
    case 4:
      res += ANSI_RED_BACKGROUND;
      res += "4";
      res += ANSI_RESET;
      break;
    case 5:
      res += ANSI_YELLOW_BACKGROUND;
      res += "5";
      res += ANSI_RESET;
      break;
    case 6:
      res += ANSI_WHITE_BACKGROUND;
      res += "5";
      res += ANSI_RESET;
      break;
    default:
      res += ANSI_BLACK_BACKGROUND;
      res += "?";
      res += ANSI_RESET;
      break;
    }
    return res;
  }

  /**
   * Translates a cube pattern "side" to a colorized string
   *
   * @param tab the cube pattern to colorize
   * @return a colored side string
   */

  public String sideToString(int[] tab) {
    String res = "";
    for (int i = 0; i < 9; i++) {
      if (i % 3 == 0)
        res += "\n";
      res += colorizeThat(tab[i]);
    }
    return res;
  }

  public String widgetLink(ArrayList<String> allMoves) {
    String widgetLink = "";
    String s = "";
    for (int j = 0; j < allMoves.size(); j++) {
      String move = allMoves.get(j);
      move = move.replace("'", "%27");
      s += move;
      s += "%20";
    }
    widgetLink = "https://ruwix.com/widget/3d/?alg=" + s + "&setupmoves=" + s
        + "&speed=150&flags=showalg&colors=U:w%20L:o%20F:g%20R:r%20B:b%20U:w%20D:y&pov=Ufr";
    return widgetLink;
  }

  /**
   * Displays the whole cube pattern (colorized using sideToString)
   *
   * @see sideToString
   * @param tab the cube pattern to colorize
   * @return a colored cube pattern string
   */

  public String toString(int[][] tab) {
    String res = "";
    for (int[] is : tab) {
      res += sideToString(is);
    }
    return res;
  }
}
