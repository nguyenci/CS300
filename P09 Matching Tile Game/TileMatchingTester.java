import java.util.EmptyStackException;
import java.util.NoSuchElementException;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 TileMatchingGame, Tester Class
// Course: CS 300 Fall 2021
//
// Author: Cinthya Nguyen
// Email: cnguyen37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: N/A
// Partner Email: N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: N/A
// Online Sources: N/A
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Tester class for P09 Tile Matching Game.
 * 
 * @author Cinthya Nguyen
 */
public class TileMatchingTester {

  /**
   * Test method for Tile.equals().
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean tileEqualsTester() {
    try {
      Tile blackOne = new Tile(Color.BLACK);
      Tile blackTwo = new Tile(Color.BLACK);
      Tile blue = new Tile(Color.BLUE);
      String string = "hi";

      // Test 1: compare to object that is not an instance of Tile
      if (blackOne.equals(string)) {
        System.out.println(
            "Error: comparing tile to an object that is not an instance of Tile returned true.");
        return false;
      }

      // Test 2: tiles of same color
      if (!blackOne.equals(blackTwo)) {
        System.out.println("Error: comparing two tiles of the same color returned false.");
        return false;
      }

      // Test 3: tiles of different color
      if (blackOne.equals(blue)) {
        System.out.println("Error: comparing two tiles of different colors returned true.");
        return false;
      }

      // Test 4: same tile
      if (!blackOne.equals(blackOne)) {
        System.out.println("Error: comparing the same tiles returned false.");
        return false;
      }

      // Test 5: null
      if (blackOne.equals(null)) {
        System.out.println("Error: comparing a tile with null returned true.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Unexpected exception was thrown.");
      return false;
    }

    return true;
  }

  /**
   * Test method for TileListIterator.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean tileListIteratorTester() {
    try {

      Node one = new Node(new Tile(Color.BLACK));
      Node two = new Node(new Tile(Color.BLUE));
      Node three = new Node(new Tile(Color.ORANGE));

      one.setNext(two);
      two.setNext(three);

      // Test 1: linked list
      TileListIterator itr = new TileListIterator(one);

      if (!itr.hasNext()) {
        System.out.println("hasNext() returned false instead of true.");
        return false;
      }

      if (itr.next() != one.getTile()) {
        System.out.println("next() did not return one.");
        return false;
      }

      if (!itr.hasNext()) {
        System.out.println("hasNext() returned false instead of true.");
        return false;
      }

      if (itr.next() != two.getTile()) {
        System.out.println("next() did not return two.");
        return false;
      }

      if (!itr.hasNext()) {
        System.out.println("hasNext() returned false instead of true.");
        return false;
      }

      if (itr.next() != three.getTile()) {
        System.out.println("next() did not return three.");
        return false;
      }

      if (itr.hasNext()) {
        System.out.println("hasNext() returned true instead of false.");
        return false;
      }

      // Test 2: empty list
      try {
        itr = new TileListIterator(null);

        if (itr.hasNext()) {
          System.out.println("hasNext() returned true instead of false.");
          return false;
        }
        
        itr.next();
        
      } catch (NoSuchElementException e1) {
        // expected behavior
      }

    } catch (Exception e2) {
      System.out.println("Unexpected exception thrown.");
      e2.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tester method for TileStack.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean tileStackTester() {
    try {
      TileStack stack = new TileStack();
      Tile blue = new Tile(Color.BLUE);

      // Test 1: push tile
      stack.push(blue);

      if (stack.peek() != blue || stack.size() != 1 || stack.isEmpty()) {
        System.out.println("Blue tile incorrectly added.");
        return false;
      }

      // Test 2: push another tile
      Tile yellow = new Tile(Color.YELLOW);
      stack.push(yellow);

      if (stack.peek() != yellow || stack.size() != 2 || stack.isEmpty()) {
        System.out.println("Yellow tile incorrectly added.");
        return false;
      }

      // Test 3: pop second tile
      if (stack.pop() != yellow || stack.size() != 1 || stack.isEmpty()) {
        System.out.println("Yellow tile incorrectly removed.");
        return false;
      }

      stack = new TileStack(); // empty stack

      // Test 4: pop() on empty stack
      try {
        stack.pop();
      } catch (EmptyStackException e1) {
        // expected behavior
      }

      // Test 5: peek() on empty stack
      try {
        stack.peek();
      } catch (EmptyStackException e1) {
        // expected behavior
      }

    } catch (Exception e2) {
      System.out.println("Unexpected exception thrown.");
      e2.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tester method for TileMatchingGame.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean tileMatchingGameTester() {
    try {

      Tile blue = new Tile(Color.BLUE);
      Tile yellow = new Tile(Color.YELLOW);
      Tile orange = new Tile(Color.ORANGE);

      TileMatchingGame game = new TileMatchingGame(2);

      // Test 1: TileMatchingGame constructor
      if (game.getColumnsNumber() != 2) {
        System.out.println("Did not initialize columsn to 2.");
        return false;
      }


      // Test 2: dropping two tiles of different colors
      game.dropTile​(blue, 0);
      game.dropTile​(yellow, 0);

      if (!game.column(0).equals("YELLOW BLUE")) {
        System.out.println("Incorrect string.");
        return false;
      }

      // Test 3: dropping tile onto of a tile of the same color
      game.dropTile​(yellow, 0);

      if (!game.column(0).equals("BLUE")) {
        System.out.println("Incorrect string.");
        return false;
      }

      // Test 4: dropping tile into another index
      game.dropTile​(orange, 1);

      if (!game.column(1).equals("ORANGE")) {
        System.out.println("Incorrect string.");
        return false;
      }

      game.dropTile​(orange, 0); // adding another tile to 0th column

      // Test 5: clearing column
      game.clearColumn​(0);

      if (!game.column(0).equals("")) {
        System.out.println("Column was not cleared correctly.");
        return false;
      }

      // Test 6: toString();
      game.dropTile​(yellow, 0);
      game.dropTile​(blue, 0);

      if (!game.toString().equals("GAME COLUMNS:\n0: BLUE YELLOW\n1: ORANGE")) {
        System.out.println("toString() method returned incorrect string.");
        return false;
      }

      // Test 7: restarting game
      game.restartGame();
      if (!game.column(0).equals("") || !game.column(1).equals("")) {
        System.out.println("Game was not restarted properly.");
        return false;
      }

      // Test 8: dropTile() - invalid index
      try {
        game.dropTile​(blue, 2);
      } catch (IndexOutOfBoundsException e1) {
        // expected behavior
      }

      // Test 9: clearColumn() - invalid index
      try {
        game.clearColumn​(-1);
      } catch (IndexOutOfBoundsException e1) {
        // expected behavior
      }

      // Test 10: constructor - invalid int
      try {
        game = new TileMatchingGame(-1);
      } catch (IllegalArgumentException e1) {
        // expected behavior
      }

    } catch (Exception e2) {
      System.out.println("Unexpected exception thrown.");
      e2.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Runs all tester methods.
   * 
   * @return true if all tests pass, false otherwise
   */
  private static boolean runAllTests() {
    if (!tileEqualsTester() || !tileListIteratorTester() || !tileStackTester()
        || !tileMatchingGameTester()) {
      System.out.println("Test(s) failed.");
      return false;
    }

    return true;
  }

  /**
   * Calls runAllTests().
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    runAllTests();

  }

}
